package com.foryou.tax.service.impl.writeoffinfo;

import com.foryou.tax.dao.writeoffinfo.WriteOffInfoTempMapper;
import com.foryou.tax.pojo.writeoffinfo.WriteOffInfoTemp;
import com.foryou.tax.service.writeoffinfo.WriteOffInfoTempService;
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
    public void backUpData(String dt) {
        writeOffInfoTempMapper.backUpData(dt);
    }
}
