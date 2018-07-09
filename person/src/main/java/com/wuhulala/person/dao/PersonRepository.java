package com.wuhulala.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wuhulala.person.domain.Person;

/**
 * @author wuhulala
 */
public interface PersonRepository extends JpaRepository<Person, Long>{

}
