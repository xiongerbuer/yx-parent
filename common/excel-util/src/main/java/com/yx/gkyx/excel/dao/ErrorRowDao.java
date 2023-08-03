package com.yx.gkyx.excel.dao;

import com.yx.gkyx.common.jpa.BaseJpaRepository;
import com.yx.gkyx.excel.model.mysql.ErrorRow;

import java.util.List;

public interface ErrorRowDao extends BaseJpaRepository<ErrorRow, Long> {
    List<ErrorRow> findByLogId(Long logId);
}
