package com.yx.ssyx.excel.dao;

import com.yx.ssyx.common.jpa.BaseJpaRepository;
import com.yx.ssyx.excel.model.mysql.ErrorRow;

import java.util.List;

public interface ErrorRowDao extends BaseJpaRepository<ErrorRow, Long> {
    List<ErrorRow> findByLogId(Long logId);
}
