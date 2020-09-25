package com.foryou.tax.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author ：raymon
 * @date ：Created in 2019/12/9 16:29
 * @description：代码生成器
 * @modified By：
 * @version: 1.0$
 */
public class CodeGeneration {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //输出文件路径
        gc.setOutputDir("/Users/raymon/Desktop");
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // 作者
        gc.setAuthor("raymon");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        /**
         * 数据源配置
         * server
         * dsc.setUrl("jdbc:mysql://192.168.0.119:3306/tax");
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://10.40.59.129:3306/tax");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表前缀
        // strategy.setTablePrefix(new String[] { "sys_" });
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude("DCFL_MERGE_INVOICE_RESULT.sql");

        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.foryou.tax");
        pc.setController("controller.weekly.mergeinvoice");
        pc.setService("service.weekly.mergeinvoice");
        pc.setServiceImpl("service.impl.weekly.mergeinvoice");
        pc.setMapper("dao.weekly.mergeinvoice");
        pc.setEntity("pojo.weekly.mergeinvoice");
        pc.setXml("dao.weekly.mergeinvoice");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }
}
