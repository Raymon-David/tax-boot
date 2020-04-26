package com.foryou.tax.service.weekly.writeoffinfo;

import com.foryou.tax.pojo.weekly.writeoffinfo.WriteOffInfoTemp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核销事务查询临时表 服务类
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
public interface WriteOffInfoTempService{

    int insertData(WriteOffInfoTemp writeOffInfoTemp);

    int backUpData(@Param("newTableName") String newTableName);

    int deleteData();

    List<Map<String, Object>> writeOffInfoQueryWeekly();
}
