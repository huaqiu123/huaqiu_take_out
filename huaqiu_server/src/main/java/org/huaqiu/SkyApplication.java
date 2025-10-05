package org.huaqiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SkyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SkyApplication.class, args);
        Object jwtTokenAdminInterceptor = context.getBean("jwtTokenAdminInterceptor");
        System.out.println(jwtTokenAdminInterceptor);

    }

}