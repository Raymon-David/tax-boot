package com.foryou.tax.init;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

/**
 * @author ：raymon
 * @date ：Created in 2019/11/6 1:44 下午
 * @description：activiti 初始化表
 * @modified By：
 * @version: 1.0$
 */
public class App {
    public static void main( String[] args ) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        engine.close();
    }
}
