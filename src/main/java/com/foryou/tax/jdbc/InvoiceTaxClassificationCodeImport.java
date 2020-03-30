package com.foryou.tax.jdbc;

import java.sql.*;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/30
 * @description : 税收分类编码通过JDBC导入
 */
public class InvoiceTaxClassificationCodeImport {

    /**
     * 定义连接所需的字符串
     * 127.0.0.1是本机地址(要改成自己的IP地址)，1521端口号，XE是精简版Oracle的默认数据库名
     */
    private static String USERNAMR = "ds_prod";
    private static String PASSWORD = "ds_prod";
    private static String DRVIER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@10.40.128.56:6001/ZLPROD";

    /**
     * 创建一个数据库连接
     */
    Connection connection = null;
    /**
     * 创建预编译语句对象，一般都是用这个而不用Statement
     */
    PreparedStatement pstm = null;
    /**
     * 创建一个结果集对象
     */
    ResultSet rs = null;

    /**
     * 获取Connection对象
     *
     */
    public Connection getConnection() {
        if (connection == null) {
            synchronized (InvoiceTaxClassificationCodeImport.class) {
                if (connection == null) {
                    try {
                        Class.forName(DRVIER);
                        connection = DriverManager.getConnection(URL, USERNAMR, PASSWORD);
                        System.out.println("成功连接数据库");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("class not find !", e);
                    } catch (SQLException e) {
                        throw new RuntimeException("get connection error!", e);
                    }
                }
            }
        }
        return connection;
    }

    /**
     * 释放资源
     */
    public void ReleaseResource() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        InvoiceTaxClassificationCodeImport invoiceTaxClassificationCodeImport = new InvoiceTaxClassificationCodeImport();
        invoiceTaxClassificationCodeImport.selectData();
    }

    /**
     * 向数据库中查询数据
     */
    public void selectData() {
        connection = getConnection();
        String sql = "SELECT *\n" +
                "  FROM (SELECT dt.class_id,\n" +
                "               dt.contract_type,\n" +
                "               (SELECT hbt.description\n" +
                "                  FROM hls_business_type hbt\n" +
                "                 WHERE dt.contract_type = hbt.business_type)\n" +
                "                  AS contract_type_n,\n" +
                "               dt.division,\n" +
                "               (SELECT hd.description\n" +
                "                  FROM hls_division hd\n" +
                "                 WHERE dt.division = hd.division)\n" +
                "                  AS division_n,\n" +
                "               dt.cf_item,\n" +
                "               (SELECT hci.description\n" +
                "                  FROM hls_cashflow_item hci\n" +
                "                 WHERE dt.cf_item = hci.cf_item)\n" +
                "                  AS cf_item_n,\n" +
                "               dt.cf_type,\n" +
                "               (SELECT hct.description\n" +
                "                  FROM hls_cashflow_type hct\n" +
                "                 WHERE dt.cf_type = hct.cf_type)\n" +
                "                  AS cf_type_n,\n" +
                "               tax_class_num,\n" +
                "               goods_version\n" +
                "          FROM DS_TAX_CLASS_NUM dt) t";
        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource();
        }
    }
}
