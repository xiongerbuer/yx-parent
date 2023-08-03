package com.yx.gkyx.excel.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.mysql")
    public DataSource mysqlDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.clickhouse")
    public DataSource clickhouseDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @SneakyThrows
    public SqlSessionFactoryBean sqlSessionFactory(ResourcePatternResolver resolver) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clickhouseDataSource());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**.xml"));
        return sqlSessionFactoryBean;
    }

}
