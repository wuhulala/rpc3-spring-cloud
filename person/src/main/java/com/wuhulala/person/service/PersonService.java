package com.wuhulala.person.service;

import com.wuhulala.person.dao.PersonRepository;
import com.wuhulala.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/9/27<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    ///////////////////////////// 方法区 ////////////////////////////////////

    public Person add(Person person){
        return personRepository.save(person);
    }
}
