package com.yx.ssyx.common.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConditionalOnBean(DataSource.class)
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    @ConditionalOnBean(NamedParameterJdbcTemplate.class)
    public JdbcSupport jdbcSupportImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new JdbcSupportImpl(namedParameterJdbcTemplate);
    }

}
