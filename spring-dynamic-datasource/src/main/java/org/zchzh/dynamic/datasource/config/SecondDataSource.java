package org.zchzh.dynamic.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/9/27
 */

@Configuration
@EnableJpaRepositories(
        basePackages = "org.zchzh.dynamic.datasource.repository.secondds",
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerSecondary")
@EnableTransactionManagement
public class SecondDataSource {

    @Autowired
    @Qualifier("secondDatasource")
    private DataSource secondaryDataSource;

    @Autowired
    private Environment env;

    @Autowired
    private JpaProperties jpaProperties;

    /**
     * 以下配置可以根据 application 配置文件中的配置设置，HibernateProperties
     * @return 返回 jpa 配置
     */
    private Map<String, String> getVendorProperties() {
        Map<String, String> jpaProperties = new HashMap<>(16);
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
//        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.Second-dialect"));
//        jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
        jpaProperties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        jpaProperties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        return jpaProperties;
//        return jpaProperties.getProperties();
    }

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(entityManagerFactorySecondary(builder).getObject()).createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                //设置实体类所在位置
                .packages("org.zchzh.dynamic.datasource.entity.second")
                .persistenceUnit("secondaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactorySecondary(builder).getObject()));
    }
}
