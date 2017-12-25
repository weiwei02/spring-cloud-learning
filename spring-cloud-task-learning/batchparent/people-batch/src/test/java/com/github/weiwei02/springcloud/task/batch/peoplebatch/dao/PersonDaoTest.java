package com.github.weiwei02.springcloud.task.batch.peoplebatch.dao;

import com.github.weiwei02.springcloud.task.batch.peoplebatch.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"spring.cloud.task.closecontext_enable=false"})
public class PersonDaoTest {
    @Autowired PersonDao personDao;
    @Test
    public void findByFirstName() throws Exception {
    }

    @Test
    public void save() throws Exception {
        Person person = new Person();
        person.setFirstName("Hong");
        person.setLastName("Tashan");
        personDao.save(person);
    }

}