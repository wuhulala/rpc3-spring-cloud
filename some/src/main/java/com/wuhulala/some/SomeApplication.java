package com.wuhulala.some;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取eureka的实例
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/21
 * @link https://github.com/wuhulala
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SomeApplication {

    @Autowired
    EurekaClient discoveryClient;

    @Value("${my.message}")
    private String message;

    @RequestMapping(value = "/getsome")
    public String getSome(){
        return message;
    }

    @GetMapping("/config-instance")
    public InstanceInfo serviceUrl(){
        return this.discoveryClient.getNextServerFromEureka("CONFIG",false);
    }

    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class,args);
    }


}
