package com.yx.gkyx.common.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JdbcSupportImpl implements JdbcSupport {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public JdbcOperations jdbcTemplate() {
        return namedParameterJdbcTemplate.getJdbcOperations();
    }

    @Override
    public NamedParameterJdbcOperations namedJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

}
