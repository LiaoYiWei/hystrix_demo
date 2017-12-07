package com.lyw.hystrixdemo.command;

import com.lyw.hystrixdemo_api.DemoUserService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class UserCommandCollapser extends HystrixCollapser<List<String>, String, String> {

    private DemoUserService demoUserService;
    private final String userId;

    public UserCommandCollapser(DemoUserService demoUserService, String userId) {
        //初始化的时候，指定在2秒内发生的请求进行合并。
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("UserCommandCollapser"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(2000)));
        this.demoUserService = demoUserService;
        this.userId = userId;
    }

    @Override
    public String getRequestArgument() {
        return userId;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collapsedRequests) {
        List<String> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(demoUserService, userIds);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, String>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<String, String> collapsedRequest : collapsedRequests) {
            String user = batchResponse.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}
