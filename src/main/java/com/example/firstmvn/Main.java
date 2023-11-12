package com.example.firstmvn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableJpaRepositories("com.example.firstmvn.repositories")
@EntityScan("com.example.firstmvn.entities")
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.firstmvn.entities"); // Package containing your JPA entities
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learning");
        dataSource.setUsername("root");
        dataSource.setPassword("Sbcd1234#");
        return dataSource;
    }

    public static void main(String[] args) {
        // Start server
        ConfigurableApplicationContext cac = SpringApplication.run(Main.class, args);
        // Print out the host:port
        String port = cac.getEnvironment().getProperty("server.port");
        LOGGER.info("Spring Boot server started on localhost:" + port);
    }
}
