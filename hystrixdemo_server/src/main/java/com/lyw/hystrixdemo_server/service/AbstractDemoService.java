package com.lyw.hystrixdemo_server.service;

import lombok.Setter;

import java.util.Random;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public abstract class AbstractDemoService {

    @Setter
    protected int percent = 0;
    @Setter
    protected long sleepTime = 0;

    public void sleep() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void randomThrowException() {
        Random random = new Random();
        if (random.nextInt(10) < percent) {
            throw new RuntimeException("randomThrowException");
        }
    }

}
