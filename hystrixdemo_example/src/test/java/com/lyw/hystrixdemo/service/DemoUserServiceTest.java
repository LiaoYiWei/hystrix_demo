package com.lyw.hystrixdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.lyw.hystrixdemo.util.TestUtil.infiniteExecute;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoUserServiceTest {

    @Test
    public void createUser() throws InterruptedException {
        infiniteExecute(() -> {
            RestTemplate client = new RestTemplate();
            String forObject = client.postForObject("http://localhost:8080/order", "11312", String.class);
            return forObject;
        });
    }


    @Test
    public void queryUser() throws InterruptedException {
        infiniteExecute(() -> {
            RestTemplate client = new RestTemplate();
            String forObject = client.getForObject("http://localhost:8080/order/1", String.class);
            return forObject;
        });
    }


}