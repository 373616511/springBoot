package com.asyf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/*//包扫描
@ComponentScan("com.example")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration*/
//下面注解相当于上面三个
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringbootApplication.class, args);
    }
}