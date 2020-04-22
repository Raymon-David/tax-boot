package com.foryou.tax.service.weekly.writeoffinfo;

import com.foryou.tax.pojo.weekly.writeoffinfo.WriteOffInfoTemp;

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

    void backUpData(String dt);

    int deleteData();
}
