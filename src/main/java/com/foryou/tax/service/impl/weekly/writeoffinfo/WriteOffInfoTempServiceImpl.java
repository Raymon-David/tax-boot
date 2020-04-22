package com.foryou.tax.service.impl.weekly.writeoffinfo;

import com.foryou.tax.dao.weekly.writeoffinfo.WriteOffInfoTempMapper;
import com.foryou.tax.pojo.weekly.writeoffinfo.WriteOffInfoTemp;
import com.foryou.tax.service.weekly.writeoffinfo.WriteOffInfoTempService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 核销事务查询临时表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-04-13
 */
@Service
public class WriteOffInfoTempServiceImpl implements WriteOffInfoTempService {

    @Autowired
    WriteOffInfoTempMapper writeOffInfoTempMapper;

    @Override
    public int insertData(WriteOffInfoTemp writeOffInfoTemp) {
        return writeOffInfoTempMapper.insertData(writeOffInfoTemp);
    }

    @Override
    public int backUpData(@Param("newTableName") String newTableName) {
        return writeOffInfoTempMapper.backUpData(newTableName);
    }

    @Override
    public int deleteData() {
        return writeOffInfoTempMapper.deleteData();
    }
}
