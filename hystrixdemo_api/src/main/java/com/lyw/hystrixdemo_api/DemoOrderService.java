package com.lyw.hystrixdemo_api;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public interface DemoOrderService extends Config {

    String createOrder(String orderJson);

    String queryOrder(String id);
}
