package com.foryou.tax.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ：Raymon
 * @date ：Created in 2020/9/25
 * @description: 获取配置文件util
 */
public class PropertyUtil {

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        LoggerUtils.debug(PropertyUtil.class, "start to load properties.......");
        props = new Properties();
        InputStream in = null;
        try {

            in = PropertyUtil.class.getClassLoader().
                    getResourceAsStream("application.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            LoggerUtils.error(PropertyUtil.class,"properties not found!");
        } catch (IOException e) {
            LoggerUtils.error(PropertyUtil.class,"IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                LoggerUtils.error(PropertyUtil.class,"properties close Exception!");
            }
        }
        // logger.info(props);
        LoggerUtils.debug(PropertyUtil.class,"load properties over...........");
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

}
