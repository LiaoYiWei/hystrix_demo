package com.lyw.hystrixdemo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.lyw.hystrixdemo.command.DubboHystrixCommand;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        DubboHystrixCommand command = new DubboHystrixCommand(invoker, invocation);
        return command.execute();
    }
}
