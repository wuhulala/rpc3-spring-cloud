package com.wuhulala.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/21
 * @link https://github.com/wuhulala
 */
@SpringBootApplication
@EnableEurekaServer
@RestController
public class DiscoverApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoverApplication.class, args);
    }
}
