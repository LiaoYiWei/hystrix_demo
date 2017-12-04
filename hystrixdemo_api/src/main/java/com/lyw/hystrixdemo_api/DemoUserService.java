package com.lyw.hystrixdemo_api;



/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public interface DemoUserService extends Config {

    String createUser(String userJson);

    String queryUser(String id);
}
