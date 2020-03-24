package com.foryou.tax.service.impl.companies;

import com.foryou.tax.dao.companies.FyCompaniesMapper;
import com.foryou.tax.pojo.companies.FyCompanies;
import com.foryou.tax.service.companies.FyCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司信息表 服务实现类
 * </p>
 *
 * @author raymon
 * @since 2020-03-24
 */
@Service
public class FyCompaniesServiceImpl implements FyCompaniesService {

    @Autowired
    FyCompaniesMapper fyCompaniesMapper;

    @Override
    public int updateCompanyEleMargin(FyCompanies fyCompanies) {
        return fyCompaniesMapper.updateCompanyEleMargin(fyCompanies);
    }
}
