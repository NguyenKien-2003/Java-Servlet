package vn.edu.t3h.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;


@Configuration
@PropertySource("classpath:application.properties")
public class ConfigDataSource {
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.database-platform}")
    private String databasePlatform;

    @Value("${hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${hibernate.show-sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;


    /*
    @Bean: custom lại cách tạo ra bean của 1 class,
    thông thường nếu không config thì class DataSource có
    thể không được tạo ra bean, hoặc sẽ được tự tạo mặc định,
    nhưng vì muốn config theo project, lên phải sử dụng
    @Bean để custom lại cách bean DataSource được tạo ra
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("vn.edu.t3h.entity"); // chỉ định package chứa các entity, để hibernate biết các entity được đặt ở đâu
        em.setJpaProperties(hibernateProperties());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    /*
    config các thuộc tính thêm của hibernate/
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", databasePlatform);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
