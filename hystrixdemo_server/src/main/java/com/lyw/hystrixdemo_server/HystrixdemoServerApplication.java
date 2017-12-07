package com.lyw.hystrixdemo_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("provider.xml")
public class HystrixdemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixdemoServerApplication.class, args);
    }

}
