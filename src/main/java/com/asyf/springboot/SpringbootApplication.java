package com.asyf.springboot;

import com.asyf.springboot.util.ApplicationContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;


/*//包扫描
@ComponentScan("com.example")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration*/
//下面注解相当于上面三个
@SpringBootApplication(scanBasePackages = "com.asyf")
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(IndexController.class, args);
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        System.err.println("redisTemplate:" + redisTemplate);
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }
}