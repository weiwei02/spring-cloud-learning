package com.github.weiwei02.springcloud.task.batch.peoplebatch.respository;

import com.github.weiwei02.springcloud.task.batch.peoplebatch.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/12
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    Person findByFirstName(String firstName);
}
