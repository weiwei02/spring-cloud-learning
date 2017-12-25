package com.github.weiwei02.springcloud.task.batch.peoplebatch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.TaskProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/** 多数据源任务仓库配置 该bean会覆盖默认的任务配置
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/12
 */
@Configuration
@EnableTask
public class ManyDatasourceTaskConfig {


    /**
     * 多数据源任务仓库配置 该bean会覆盖默认的任务配置
     * */
    @Bean
    public DefaultTaskConfigurer taskConfigurer(@Qualifier("cloudTask") DataSource cloudTask, TaskProperties taskProperties, ApplicationContext context) {
        return new DefaultTaskConfigurer(cloudTask, taskProperties.getTablePrefix(), context);
    }
}
