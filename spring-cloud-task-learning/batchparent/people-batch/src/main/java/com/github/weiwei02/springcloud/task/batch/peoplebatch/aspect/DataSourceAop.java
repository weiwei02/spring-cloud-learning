package com.github.weiwei02.springcloud.task.batch.peoplebatch.aspect;

import com.github.weiwei02.common.spring.data.config.DataSourceContextHolder;
import com.github.weiwei02.springcloud.task.batch.peoplebatch.enums.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * desc: 数据源切面配置
 * @author weiwei
 * @since 2017-12-11
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAop {
    private static final String PACKAGE_PRIFIX = "com.github.weiwei02.springcloud.";

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* org.springframework.cloud.task.repository.dao.*.*(..))")
    public void setScoreDataSourceType() throws Throwable{
        DataSourceContextHolder.setDataSourceType(DataSourceType.TASK.getType());
        log.debug("dataSource == >：score");
    }

    @Before("execution(* " + PACKAGE_PRIFIX + "task..*Repository.*(..))")
    public void setBsDataSourceType() throws Throwable{
        DataSourceContextHolder.setDataSourceType(DataSourceType.TASK_DEMO.getType());
        log.debug("dataSource == >：bankserver4");
    }


}
