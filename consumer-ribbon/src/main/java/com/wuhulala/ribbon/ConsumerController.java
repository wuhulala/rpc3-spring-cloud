package com.wuhulala.ribbon;

import com.wuhulala.ribbon.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/22
 * @link https://github.com/wuhulala
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("/consumer/{id}")
    public Person getConsumer(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://person/person/" + id, Person.class);
    }

    @GetMapping("/test")
    public String test(){

        ServiceInstance instance1 = loadBalancerClient.choose("person");
        System.out.println(instance1.getServiceId()+"【"+instance1.getHost()+":"+instance1.getPort()+"】");

        ServiceInstance instance2 = loadBalancerClient.choose("some");
        System.out.println(instance2.getServiceId()+"【"+instance2.getHost()+":"+instance2.getPort()+"】");

        return "1";


        //1. 在TestConfiguration 扫描不到的地方ComponentScan 现在放在com.wuhulala.config包里面
        //person 是随机策略  some是轮询策略
        // person【MSI.hs.handsome.com.cn:8080】
        // some【MSI.hs.handsome.com.cn:9081】
        // person【MSI.hs.handsome.com.cn:8081】
        // some【MSI.hs.handsome.com.cn:9082】
        // person【MSI.hs.handsome.com.cn:8081】
        // some【MSI.hs.handsome.com.cn:9081】
        // person【MSI.hs.handsome.com.cn:8080】
        // some【MSI.hs.handsome.com.cn:9082】
        // person【MSI.hs.handsome.com.cn:8082】
        // some【MSI.hs.handsome.com.cn:9081】
        // person【MSI.hs.handsome.com.cn:8081】
        // some【MSI.hs.handsome.com.cn:9082】


        //2. 在TestConfiguration 扫描不到的地方ComponentScan 现在放在com.wuhulala.ribbon.config包里面
        //person、some 都是随机策略
        //some【MSI.hs.handsome.com.cn:9082】
       //person【MSI.hs.handsome.com.cn:8082】
       //some【MSI.hs.handsome.com.cn:9081】
       //person【MSI.hs.handsome.com.cn:8082】
       //some【MSI.hs.handsome.com.cn:9081】
       //person【MSI.hs.handsome.com.cn:8080】
       //some【MSI.hs.handsome.com.cn:9081】
       //person【MSI.hs.handsome.com.cn:8082】
       //some【MSI.hs.handsome.com.cn:9082】
       //person【MSI.hs.handsome.com.cn:8082】
       //some【MSI.hs.handsome.com.cn:9082】
       //person【MSI.hs.handsome.com.cn:8080】
       //some【MSI.hs.handsome.com.cn:9081】
       //person【MSI.hs.handsome.com.cn:8080】
       //some【MSI.hs.handsome.com.cn:9082】
       //person【MSI.hs.handsome.com.cn:8081】
       //some【MSI.hs.handsome.com.cn:9081】

    }


}
