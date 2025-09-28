package com.tc;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName Application
 * @Author 大马鲛鱼丸Gaga
 * @Description TODO
 * @Date 2025/9/28 17:25
 */
@Configurable
@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
