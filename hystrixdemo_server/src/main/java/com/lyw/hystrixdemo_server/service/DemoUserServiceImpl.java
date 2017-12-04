package com.lyw.hystrixdemo_server.service;

import com.lyw.hystrixdemo_api.DemoUserService;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class DemoUserServiceImpl extends AbstractDemoService implements DemoUserService {

    @Override
    public String createUser(String userJson) {
        randomThrowException();
        return "create user:" + userJson;
    }

    @Override
    public String queryUser(String id) {
        sleep();
        return "the query user";
    }

}
