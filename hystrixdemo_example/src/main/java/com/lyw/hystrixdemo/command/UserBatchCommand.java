package com.lyw.hystrixdemo.command;

import com.google.common.base.Joiner;
import com.lyw.hystrixdemo_api.DemoUserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class UserBatchCommand extends HystrixCommand<List<String>> {

    private DemoUserService demoUserService;
    private List<String> ids;

    public UserBatchCommand(DemoUserService demoUserService, List<String> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserBatchCommand"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("findAll")));
        this.demoUserService = demoUserService;
        this.ids = userIds;
    }

    @Override
    protected List<String> run() throws Exception {
        if (ids != null && !ids.isEmpty()) {
            return demoUserService.findAll(Joiner.on(",").join(ids));
        }
        return new ArrayList<>();
    }
}
