package com.github.weiwei02.springcloud.task.batch.peoplebatch.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/11
 */
@Entity
@Table(name = "sample_person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
}
