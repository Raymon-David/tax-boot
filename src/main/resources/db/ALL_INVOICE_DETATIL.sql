CREATE TABLE ALL_INVOICE_DETAIL (
  INVOICE_DETAIL_ID int(20) NOT NULL AUTO_INCREMENT COMMENT '发票明细表ID/PK',
  INVOICE_ID int(20) NOT NULL COMMENT '发票表ID',
  PRODUCT_ID int(20) NOT NULL COMMENT '商品ID',
  CASHFLOW_ID int(20) NOT NULL COMMENT '现金流ID',
  CASHFLOW_ITEM_CODE varchar(10) DEFAULT '' COMMENT '现金流项目CODE',   --CF_ITEM
  PRODUCT_NAME varchar(100) NOT NULL COMMENT '商品名称',
  SPEC varchar(30) NULL COMMENT '规格型号', --SPECIFICATION
  TAX_QUANTITY int(4) NULL COMMENT '数量', --QUANTITY
  UNIT varchar(5) NULL COMMENT '计量单位',  --UOM
  TAX_PRICE decimal(12,2) NULL COMMENT '含税单价', --PRICE
  TAX_NET_PRICE decimal(12,2) NULL COMMENT '不含税单价', --NET_PRICE
  TAX_TYPE varchar(5) NULL COMMENT '税种',  --TAX_TYPE_ID
  TAX_RATE int(20) COMMENT '税率',   --TAX_TYPE_RATE
  TAX_INCLUDED_FLAG varchar(5) NULL COMMENT '含税标志',
  TOTAL_AMOUNT decimal(12,2) NULL COMMENT '含税金额',
  TAX_AMOUNT decimal(12,2) NULL COMMENT '税额',
  TAX_NET_AMOUNT decimal(12,2) NULL COMMENT '不含税金额', --NET_AMOUNT
  INVOICE_DETAIL_MEMO varchar(100) NULL COMMENT '行备注/行摘要',   --DESCRIPTION
  CREATED_BY int(10) DEFAULT NULL,
  CREATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UPDATED_BY int(10) DEFAULT NULL,
  UPDATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`INVOICE_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='发票明细表';


/*
Navicat MySQL Data Transfer

Source Server         : 10.40.59.129
Source Server Version : 80018
Source Host           : 10.40.59.129:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-24 15:48:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ALL_INVOICE_DETATIL.sql
-- ----------------------------
DROP TABLE IF EXISTS `ALL_INVOICE_DETAIL`;
CREATE TABLE `ALL_INVOICE_DETAIL` (
  `INVOICE_DETAIL_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '发票明细表ID/PK',
  `INVOICE_ID` int(20) NOT NULL COMMENT '发票表ID',
  `CASHFLOW_ID` int(20) NOT NULL COMMENT '现金流ID',
  `CASHFLOW_ITEM_CODE` varchar(10) DEFAULT '' COMMENT '现金流项目CODE',
  `PRODUCT_ID` int(20) NOT NULL COMMENT '商品ID',
  `PRODUCT_NAME` varchar(100) NOT NULL COMMENT '商品名称',
  `SPEC` varchar(30) DEFAULT NULL COMMENT '规格型号',
  `TAX_QUANTITY` int(4) DEFAULT NULL COMMENT '数量',
  `UNIT` varchar(5) DEFAULT NULL COMMENT '计量单位',
  `TAX_PRICE` decimal(12,0) DEFAULT NULL COMMENT '含税单价',
  `TAX_NET_PRICE` decimal(12,0) DEFAULT NULL COMMENT '不含税单价',
  `TAX_TYPE` varchar(5) DEFAULT NULL COMMENT '税种',
  `TAX_RATE` int(20) DEFAULT NULL COMMENT '税率',
  `TAX_INCLUDED_FLAG` varchar(5) DEFAULT NULL COMMENT '含税标志',
  `TOTAL_AMOUNT` decimal(12,0) DEFAULT NULL COMMENT '含税金额',
  `TAX_AMOUNT` decimal(12,0) DEFAULT NULL COMMENT '税额',
  `TAX_NET_AMOUNT` decimal(12,0) DEFAULT NULL COMMENT '不含税金额',
  `INVOICE_DETAIL_MEMO` varchar(100) DEFAULT NULL COMMENT '行备注/行摘要',
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`INVOICE_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='发票明细表';
