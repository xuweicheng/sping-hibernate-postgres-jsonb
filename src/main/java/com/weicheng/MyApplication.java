package com.weicheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Weicheng on 9/10/2018.
 */
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
