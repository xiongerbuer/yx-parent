package com.atguigu.ssyx.common.jpa;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public interface JdbcSupport {

    JdbcOperations jdbcTemplate();

    NamedParameterJdbcOperations namedJdbcTemplate();

}
