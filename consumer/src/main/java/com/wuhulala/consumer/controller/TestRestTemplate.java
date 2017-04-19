package com.wuhulala.consumer.controller;

import com.wuhulala.consumer.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/19
 * @link https://github.com/wuhulala
 */
@RestController
public class TestRestTemplate {
    private static final Logger logger = LoggerFactory.getLogger(TestRestTemplate.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${person.serviceUrl}")
    private String serviceUrl;


    @GetMapping("/consumer/{id}")
    public Person getConsumer(@PathVariable("id") Long id) {
        logger.debug("调用：【" + serviceUrl + "/person/" + id + "】");
        return restTemplate.getForObject(serviceUrl + "/person/" + id, Person.class);
    }
}
