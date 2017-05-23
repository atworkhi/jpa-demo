package com.zhangbo.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * Created by zhangbo on 2017/5/8.
 */
@Configuration
@ComponentScan(basePackages = {"com.zhangbo"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {EnableWebMvc.class})})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = {"com.zhangbo.dao"})
public class RootConfig {

    private static Logger log = LoggerFactory.getLogger(RootConfig.class);

    @Autowired
    private Environment environment;


    @Bean
    public DataSource dataSource() {
        log.info("..................init dataSource........................");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        log.info("..................init LocalContainerEntityManagerFactoryBean........................");
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.zhangbo.model");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        log.info("..................init JpaVendorAdapter........................");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        log.info("..................init JpaTransactionManager........................");
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory);
        return manager;
    }
}
