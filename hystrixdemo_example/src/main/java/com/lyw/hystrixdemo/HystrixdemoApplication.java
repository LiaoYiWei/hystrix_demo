package com.lyw.hystrixdemo;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:/customer.xml")
public class HystrixdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixdemoApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean axisServletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
		return registration;
	}

}
