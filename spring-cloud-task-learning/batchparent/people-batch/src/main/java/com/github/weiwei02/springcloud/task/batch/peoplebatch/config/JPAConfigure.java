package com.github.weiwei02.springcloud.task.batch.peoplebatch.config;

import com.github.weiwei02.common.spring.data.config.MyAbstractRoutingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**JPA生成器配置
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/12
 */
@Configuration
public class JPAConfigure {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(MyAbstractRoutingDataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.github.weiwei02.springcloud.task");
        factory.setDataSource(dataSource);

        return factory;
    }
}
