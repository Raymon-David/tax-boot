package com.foryou.tax.dao.writeoffinfo;

import com.foryou.tax.pojo.writeoffinfo.WriteOffInfoTemp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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

    void backUpData(String dt);
}
