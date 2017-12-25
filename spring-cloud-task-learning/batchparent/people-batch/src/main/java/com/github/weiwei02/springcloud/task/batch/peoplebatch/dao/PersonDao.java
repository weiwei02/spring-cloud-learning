package com.github.weiwei02.springcloud.task.batch.peoplebatch.dao;

import com.github.weiwei02.springcloud.task.batch.peoplebatch.entity.Person;
import com.github.weiwei02.springcloud.task.batch.peoplebatch.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/** 持久层
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/12
 */
@Repository
@Transactional
public class PersonDao {
    @Autowired
    PersonRepository personRepository;

    public Person findByFirstName(String firstName){
        return personRepository.findByFirstName(firstName);
    }

    public Person save(Person person){
        return personRepository.save(person);
    }
}
