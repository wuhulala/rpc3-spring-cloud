package com.wuhulala.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerRibbonApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class,args);
    }
}
