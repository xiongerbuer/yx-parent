package com.yx.ssyx.excel.dao.clickhouse;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ImportExcelDataDao {

    @Select("select * from ${table} where tenantId=#{tenantId} " +
            "and createAt>#{start} " +
            "and createAt<#{end} " +
            "limit #{limit} offset #{offset}")
    List<Map<String, String>> importExcelData(
            @Param("tenantId") Long tenantId,
            @Param("table") String table,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("limit") int limit,
            @Param("offset") long offset
    );

    @Select("select count() from ${table} " +
            "where  tenantId=#{tenantId} " +
            "and createAt>#{start} and createAt<#{end}")
    long countByRange(@Param("tenantId") Long tenantId,
                      @Param("table") String table,
                      @Param("start") LocalDateTime start,
                      @Param("end") LocalDateTime end);
}
