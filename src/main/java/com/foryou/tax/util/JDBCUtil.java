package com.foryou.tax.util;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Raymon
 * @date ：Created in 2020/3/31
 * @description: JDBC util
 */
public class JDBCUtil {

    /**
     * 定义连接所需的字符串
     * 127.0.0.1是本机地址(要改成自己的IP地址)，1521端口号，XE是精简版Oracle的默认数据库名
     */
    private static String USERNAMR = "ds_prod";
    private static String PASSWORD = "ds_prod";
    private static String DRVIER = "oracle.jdbc.driver.OracleDriver";
    /**
     * server
     * private static String URL = "jdbc:oracle:thin:@10.40.128.199:1521/ZLPROD";
     */
    private static String URL = "jdbc:oracle:thin:@10.40.128.56:6001/ZLPROD";



    /**
     * 创建预编译语句对象，一般都是用这个而不用Statement
     */
    static PreparedStatement pstm = null;
    /**
     * 创建一个结果集对象
     */
    static ResultSet rs = null;

    /**
     * 获取Connection对象
     *
     */
    public static Connection getConnection() {
        /**
         * 创建一个数据库连接
         */
        Connection connection = null;
        synchronized (JDBCUtil.class) {
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
        return connection;
    }

    /**
     * 释放资源
     */
    public static void ReleaseResource(Connection connection) {
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


    /**
     * 向数据库中查询数据
     */
    public static List<Map<String, String>> selectData(String sql) {
        Connection connection = getConnection();
        List records = new ArrayList();

        try {
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int fieldCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map<String, String> valueMap = new LinkedHashMap<String, String>();
                for (int i = 1; i <= fieldCount; i++)
                {
                    String fieldClassName = rsmd.getColumnClassName(i);
                    String fieldName = rsmd.getColumnName(i);
                    recordMappingToMap(fieldClassName, fieldName, rs, valueMap);
                }
                records.add(valueMap);
            }
            System.out.println(records.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseResource(connection);
            return records;
        }
    }


    /**
     * 将ResultSet结果集中的记录映射到Map对象中.
     *
     * @param fieldClassName 是JDBC API中的类型名称,
     * @param fieldName 是字段名，
     * @param rs 是一个ResultSet查询结果集,
     * @param fieldValue Map对象,用于存贮一条记录.
     * @throws SQLException
     */
    private static void recordMappingToMap(String fieldClassName, String fieldName, ResultSet rs, Map fieldValue)
            throws SQLException {
        fieldName = fieldName.toLowerCase();
        /**
         * 优先规则：常用类型靠前
         */
        if (fieldClassName.equals("java.lang.String")) {
            String s = rs.getString(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Integer")) {
            int s = rs.getInt(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                // 早期jdk需要包装，jdk1.5后不需要包装
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Long")) {
            long s = rs.getLong(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Boolean")) {
            boolean s = rs.getBoolean(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Short")) {
            short s = rs.getShort(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Float")) {
            float s = rs.getFloat(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Double")) {
            double s = rs.getDouble(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Timestamp")) {
            java.sql.Timestamp s = rs.getTimestamp(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Date") || fieldClassName.equals("java.util.Date")) {
            java.util.Date s = rs.getDate(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Time")) {
            java.sql.Time s = rs.getTime(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Byte")) {
            byte s = rs.getByte(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, new Byte(s));
            }
        } else if (fieldClassName.equals("[B") || fieldClassName.equals("byte[]")) {
            // byte[]出现在SQL Server中
            byte[] s = rs.getBytes(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.math.BigDecimal")) {
            BigDecimal s = rs.getBigDecimal(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.lang.Object") || fieldClassName.equals("oracle.sql.STRUCT")) {
            Object s = rs.getObject(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Array") || fieldClassName.equals("oracle.sql.ARRAY")) {
            java.sql.Array s = rs.getArray(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Clob")) {
            java.sql.Clob s = rs.getClob(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else if (fieldClassName.equals("java.sql.Blob")) {
            java.sql.Blob s = rs.getBlob(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        } else {// 对于其它任何未知类型的处理
            Object s = rs.getObject(fieldName);
            if (rs.wasNull()) {
                fieldValue.put(fieldName, null);
            } else {
                fieldValue.put(fieldName, s);
            }
        }
    }
}
