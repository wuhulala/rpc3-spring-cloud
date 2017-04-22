package com.wuhulala.ribbon;

import com.wuhulala.ribbon.annotation.ExcludeFromComponentScan;
import com.wuhulala.ribbon.config.PersonConfiguration;
import com.wuhulala.ribbon.config.SomeConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
//如果这种负载均衡的Configuration在SpringApplication可以被扫描到，会覆盖所有ribbonClient的负载均衡策略 下面这些是无效果的
//如果扫描到多个策略 最后注册的策略bean会覆盖掉原来的策略
//但是非要放到可以扫描到的位置，那么需要在扫描包的路径时候 添加扫描filter 防止扫描到相应的位置 比如加个注解filter


//设置不同的服务不同的负载均衡效果
//1. some服务 轮询策略
//2. person服务 随机策略
@RibbonClients({
        @RibbonClient(name = "person", configuration = PersonConfiguration.class),
        @RibbonClient(name = "some", configuration = SomeConfiguration.class)
})
public class ConsumerRibbonApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}
