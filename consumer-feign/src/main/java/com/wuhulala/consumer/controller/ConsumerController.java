package com.wuhulala.consumer.controller;

import com.wuhulala.consumer.domain.Person;
import com.wuhulala.consumer.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/19
 * @link https://github.com/wuhulala
 */
@RestController
public class ConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private PersonService personService;


    @GetMapping("/consumer/{id}")
    public Person getConsumer(@PathVariable("id") Long id) {
        logger.debug("person服务调用：【" + "/person/" + id + "】");
        return personService.getById(id);
    }

    //不可能成功 因为feign 在面对参数是对象的情况下 只以post方式发送请求
    //{"timestamp":1492924045147,"status":405,"error":"Method Not Allowed","exception":"org.springframework.web.HttpRequestMethodNotSupportedException","message":"Request method 'POST' not supported","path":"/person/person"}
    @GetMapping("/test")
    public Person getConsumer() {
        Person person = new Person();
        person.setId(1L);
        logger.debug("person服务调用：【" + person + "】");
        return personService.getPerson(person);
    }
}
