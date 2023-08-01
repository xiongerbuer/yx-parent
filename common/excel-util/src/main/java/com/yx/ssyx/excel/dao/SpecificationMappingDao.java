package com.yx.ssyx.excel.dao;


import com.atguigu.ssyx.common.jpa.BaseJpaRepository;
import com.yx.ssyx.excel.model.mysql.SpecificationMapping;

import java.util.List;

public interface SpecificationMappingDao extends BaseJpaRepository<SpecificationMapping, Long> {


    List<SpecificationMapping> findSpecificationMappingByTenantId(Long tenantId);

}
