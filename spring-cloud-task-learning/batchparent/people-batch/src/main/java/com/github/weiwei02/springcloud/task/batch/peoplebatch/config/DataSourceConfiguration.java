package com.github.weiwei02.springcloud.task.batch.peoplebatch.config;

import com.github.weiwei02.common.spring.data.config.MyAbstractRoutingDataSource;
import com.github.weiwei02.springcloud.task.batch.peoplebatch.enums.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.*;

/**
 * 动态数据源配置
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration implements TransactionManagementConfigurer {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${datasource.pool.config.default.type}")
    private Class<? extends DataSource> poolClassName;


    @Bean(name = "cloudTask", destroyMethod = "close", initMethod="init")
    @Primary
    @ConfigurationProperties(prefix = "datasource.cloudTask")
    public DataSource scoreDataSourceOne() {
        log.info("-------------------- scoreDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(poolClassName).build();
    }

    @Bean(name = "cloudTaskDemo", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix = "datasource.cloudTaskDemo")
    public DataSource bsDataSourceOne() {
        log.info("-------------------- bsDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(poolClassName).build();
    }

    @Bean("dataSourceMap")
    public Map<String,DataSource> dataSourceMap(){
        Map<String,DataSource> dataSources = new HashMap<>();
        dataSources.put(DataSourceType.TASK.getType(),scoreDataSourceOne());
        dataSources.put(DataSourceType.TASK_DEMO.getType(),bsDataSourceOne());
        return dataSources;
    }

    /**
     * 有多少个数据源就要配置多少个bean
     *
     * @return
     */
    @Bean(name = "routingDataSource")
    public MyAbstractRoutingDataSource roundRobinDataSouceProxy() {
        int size = Integer.parseInt("1");
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceType.TASK.getType(), scoreDataSourceOne());
        for (String type : dataSourceMap().keySet()) {
            targetDataSources.put(type, dataSourceMap().get(type));
        }
        proxy.setDefaultTargetDataSource(scoreDataSourceOne());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    /***/
    @Bean
    public Collection<DataSource> dataSources(DataSource cloudTask, DataSource cloudTaskDemo){
        Set<DataSource> dataSources = new HashSet<>(2);
        dataSources.add(cloudTask);
        dataSources.add(cloudTaskDemo);
        return dataSources;
    }

    /**
     * 事务配置,考虑多数据源情况下
     * @return
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }


}
