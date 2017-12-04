package com.lyw.hystrixdemo_server.service;

import com.lyw.hystrixdemo_api.DemoOrderService;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class DemoOrderServiceImpl extends AbstractDemoService implements DemoOrderService {

    @Override
    public String createOrder(String orderJson) {
        randomThrowException();
        return "create order:" + orderJson;
    }

    @Override
    public String queryOrder(String id) {
        sleep();
        return "the query order";
    }

}
