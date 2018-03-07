package com.asyf.springboot;

import com.asyf.springboot.util.ApplicationContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;


@EnableAutoConfiguration
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(IndexController.class, args);
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        System.err.println("redisTemplate:" + redisTemplate);
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }
}