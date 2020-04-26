package com.foryou.tax.dao.weekly.writeoffinfo;

import com.foryou.tax.pojo.weekly.writeoffinfo.WriteOffInfoTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核销事务查询临时表 Mapper 接口
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
@Component
@Mapper
public interface WriteOffInfoTempMapper{

    int insertData(WriteOffInfoTemp writeOffInfoTemp);

    int backUpData(@Param("newTableName") String newTableName);

    int deleteData();

    List<Map<String, Object>> writeOffInfoQueryWeekly();
}
