package com.lyw.hystrixdemo_server.service;

import com.lyw.hystrixdemo_api.DemoUserService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> findAll(String join) {
        System.out.println("execute find all method :" + join);
        List<String> values = new ArrayList<String>();
        String[] split = join.split(",");
        for (int i = 0; i < split.length; i++) {
            values.add("User:" + i);
        }
        return values;
    }

}
