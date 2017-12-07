package com.lyw.hystrixdemo_api;


import java.util.List;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public interface DemoUserService extends Config {

    String createUser(String userJson);

    String queryUser(String id);

    List<String> findAll(String join);
}
