package com.wuhulala.ribbon;

import com.wuhulala.ribbon.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/consumer/{id}")
    public Person getConsumer(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://person/person/" + id, Person.class);
    }
}
