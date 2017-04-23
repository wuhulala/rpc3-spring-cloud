package com.wuhulala.consumer.service;

import com.wuhulala.consumer.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("person")
public interface PersonService {
    //1.必须使用@RequestMapping
    @RequestMapping(method = RequestMethod.POST, value = "/person/save")
    List<Person> save(@RequestBody String name);

    //2. @PathVariable 必须设置value
    @RequestMapping(method = RequestMethod.GET, value = "/person/{id}")
    Person getById(@PathVariable("id") Long id);

    //3.复杂对象必须使用post 类型的请求 因为这个请求也会被转化为post请求
    @RequestMapping(method = RequestMethod.GET, value = "/person/person")
    Person getPerson(@RequestBody Person person);
}
