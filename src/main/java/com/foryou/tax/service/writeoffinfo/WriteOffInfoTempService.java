package com.foryou.tax.service.writeoffinfo;

import com.foryou.tax.pojo.writeoffinfo.WriteOffInfoTemp;
import com.baomidou.mybatisplus.service.IService;

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
}
