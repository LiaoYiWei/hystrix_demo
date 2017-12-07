package com.lyw.hystrixdemo.controller;

import com.google.common.base.Throwables;
import com.lyw.hystrixdemo.command.UserCommandCollapser;
import com.lyw.hystrixdemo_api.Config;
import com.lyw.hystrixdemo_api.DemoUserService;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */

@Controller
@RequestMapping("/user")
public class UserDemoController extends BaseDemoController {

    @Autowired
    DemoUserService demoUserServiceClient;
//    @Autowired
//    HystrixRequestContext hystrixRequestContext;

    @PostMapping
    public @ResponseBody
    String createUser(@RequestBody String userJson) {
        return demoUserServiceClient.createUser(userJson);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String queryUser(@PathVariable("id") String id) {
        return demoUserServiceClient.queryUser(id);
    }

    @GetMapping("/find/{id}")
    public @ResponseBody
    String findAllUser(@PathVariable("id") String id) {
        HystrixRequestContext.initializeContext();
        try {
            Future<String> queue1 = new UserCommandCollapser(demoUserServiceClient, id).queue();
            Future<String> queue2 = new UserCommandCollapser(demoUserServiceClient, id).queue();
            Future<String> queue3 = new UserCommandCollapser(demoUserServiceClient, id).queue();
            Future<String> queue4 = new UserCommandCollapser(demoUserServiceClient, id).queue();
            String value = queue1.get() + queue2.get() + queue3.get() + queue4.get();
            System.out.println("size：" + HystrixRequestLog.getCurrentRequest().getExecutedCommands().size());
            return value;
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    protected Config getConfig() {
        return demoUserServiceClient;
    }
}
