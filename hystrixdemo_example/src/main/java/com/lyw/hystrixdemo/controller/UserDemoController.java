package com.lyw.hystrixdemo.controller;

import com.lyw.hystrixdemo_api.Config;
import com.lyw.hystrixdemo_api.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public @ResponseBody
    String createUser(@RequestBody String userJson) {
        return demoUserServiceClient.createUser(userJson);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String queryOrder(@PathVariable("id") String id) {
        return demoUserServiceClient.queryUser(id);
    }

    @Override
    protected Config getConfig() {
        return demoUserServiceClient;
    }
}
