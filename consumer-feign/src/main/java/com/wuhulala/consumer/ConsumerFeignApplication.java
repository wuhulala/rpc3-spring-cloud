package com.wuhulala.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Rest 服务消费者
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/19
 * @link https://github.com/wuhulala
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApplication.class,args);
    }
}
