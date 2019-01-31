package com.wuhulala.person.service;

import com.wuhulala.person.dao.PersonRepository;
import com.wuhulala.person.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/9/27<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @MockBean
    PersonRepository personRepository;

    @Test
    public void add() throws Exception {
        given(personRepository.save(personRepository.save(BDDMockito.any(Person.class)))).willReturn(null);
        Assert.assertEquals(null, personService.add(null));
    }

}