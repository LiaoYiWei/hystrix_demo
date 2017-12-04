package com.lyw.hystrixdemo.command;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class DubboHystrixCommand extends HystrixCommand<Result> {

    private static Logger logger = LoggerFactory.getLogger(DubboHystrixCommand.class);
    private static final int DEFAULT_THREADPOOL_CORE_SIZE = 10;
    private Invoker invoker;
    private Invocation invocation;

    public DubboHystrixCommand(Invoker invoker, Invocation invocation) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(String.format("%s_%d", invocation.getMethodName(),
                        invocation.getArguments() == null ? 0 : invocation.getArguments().length)))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerRequestVolumeThreshold(5)//10秒钟内至少4此请求失败，熔断器才发挥起作用
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerErrorThresholdPercentage(5)//错误率达到5开启熔断保护
                        .withExecutionTimeoutEnabled(false))//使用dubbo的超时，禁用这里的超时
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(getThreadPoolCoreSize(invoker.getUrl()))));//线程池为10
        this.invoker = invoker;
        this.invocation = invocation;
    }

    /**
     * 获取线程池大小
     *
     * @param url
     * @return
     */
    private static int getThreadPoolCoreSize(URL url) {
        if (url != null) {
            int size = url.getParameter("ThreadPoolCoreSize", DEFAULT_THREADPOOL_CORE_SIZE);
            if (logger.isDebugEnabled()) {
                logger.debug("ThreadPoolCoreSize:" + size);
            }
            return size;
        }
        return DEFAULT_THREADPOOL_CORE_SIZE;
    }

    @Override
    protected Result run() {
        return invoker.invoke(invocation);
    }

//    @Override
//    protected Result getFallback() {
//        System.out.println("----exec fall back logic----");
//        return null;
//    }
}
