package com.lyw.hystrixdemo.controller;

import com.lyw.hystrixdemo_api.Config;
import com.lyw.hystrixdemo_api.DemoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */

@Controller
@RequestMapping("/order")
public class OrderDemoController extends BaseDemoController {

    @Autowired
    DemoOrderService demoOrderServiceClient;

    @PostMapping
    public @ResponseBody
    String createOrder(@RequestBody String orderJson) {
        return demoOrderServiceClient.createOrder(orderJson);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    String queryOrder(@PathVariable("id") String id) {
        return demoOrderServiceClient.queryOrder(id);
    }


    @Override
    protected Config getConfig() {
        return demoOrderServiceClient;
    }
}
