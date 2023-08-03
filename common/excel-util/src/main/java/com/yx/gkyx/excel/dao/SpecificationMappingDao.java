package com.yx.gkyx.excel.dao;


import com.yx.gkyx.common.jpa.BaseJpaRepository;
import com.yx.gkyx.excel.model.mysql.SpecificationMapping;

import java.util.List;

public interface SpecificationMappingDao extends BaseJpaRepository<SpecificationMapping, Long> {


    List<SpecificationMapping> findSpecificationMappingByTenantId(Long tenantId);

}
