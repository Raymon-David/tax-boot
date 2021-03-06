CREATE TABLE ALL_INVOICE_INFO (
INVOICE_ID int(20) NOT NULL AUTO_INCREMENT COMMENT '发票表ID/PK',   --INVOICE_HD_ID
COMPANY_ID int(20) NOT NULL COMMENT '公司ID',
INVOICE_NUM varchar(20) NULL COMMENT '发票单据编号  AI + yyyymmdd + 1000001',   --DOCUMENT_NUMBER
INVOICE_KIND varchar(20) NULL COMMENT '发票种类（SPECIAL-专用发票 / ORDINARY-普通发票 / RECEIPT-收据）',
BILLING_METHOD varchar(20) NULL COMMENT '开票方式 LB_INTEREST-回租利息票 / L_RENTAL - 直租本金票 / L_INTEREST-直租利息票 / LB_RENTAL-回租本金票',
BILLING_WAY varchar(20) NULL COMMENT '开票方式 PAPER-纸质发票 / ELE - 电子发票',
MANUAL_INVOICE_CODE varchar(20) NULL COMMENT '区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入',   --MANUAL_INVOICE_FLAG
MANUAL_INVOICE_NAME varchar(20) NULL COMMENT '区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入',
DOCUMENT_CATEGORY varchar(30) NULL COMMENT '单据类别',
COMMODITY_CODE varchar(10) NULL COMMENT '商品或服务code 决定税收分类编码',
COMMODITY_NAME varchar(30) NULL COMMENT '商品或服name 决定税收分类编码',   --DIVISION
OWNER_USER_ID  int(10) COMMENT '单据所有者（用户ID） 日后可以按量收费',
OBJECT_CODE varchar(25) DEFAULT '' COMMENT '开票对象code',    --BP_ID
OBJECT_NAME varchar(255) DEFAULT '' COMMENT '开票对象name',
INVOICE_TITLE varchar(30) NULL COMMENT '发票抬头name',   --BP_NAME
TAX_REGISTRY_NUM varchar(30) NULL COMMENT '纳税人登记号/税收编码',   --BP_TAX_REGISTRY_NUM
INVOICE_OBJECT_ADDRESS_PHONE varchar(100) NULL COMMENT '地址及电话',   --BP_ADDRESS_PHONE_NUM
INVOICE_OBJECT_BANK_ACCOUNT varchar(100) NULL COMMENT '开户行及账号',   --BP_BANK_ACCOUNT
INVOICE_MEMO varchar(100) NULL COMMENT '备注',   --DESCRIPTION
TOTAL_AMOUNT decimal(12,2) NULL COMMENT '含税金额总计',
TAX_AMOUNT decimal(12,2) NULL COMMENT '税额总计',
CURRENCY varchar(10) NULL COMMENT '币种',
EXCHANGE_RATE_TYPE varchar(10) NULL COMMENT '汇率类型',
EXCHANGE_RATE_QUOTATION varchar(10) NULL COMMENT '汇率标价方法',
EXCHANGE_RATE varchar(10) NULL COMMENT '汇率',
PERIOD_CODE varchar(30) NULL COMMENT '期间序号',   --INTERNAL_PERIOD_NUM
PERIOD_NAME varchar(30) NULL COMMENT '期间名',
INVOICE_STATUS_CODE varchar(20) NULL COMMENT '发票状态CODE',
INVOICE_STATUS_NAME varchar(20) NULL COMMENT '发票状态NAME',  --INVOICE_STATUS
ACCOUNT_PAYEE varchar(20) NULL COMMENT '收款员', --SKY
ACCOUNT_TIME timestamp NULL COMMENT '记账日期',   --ACCOUNTING_DATE
ISSUER int(10) NULL COMMENT '开票人',   --CREATED_BY
ISSUED_TIME timestamp NULL COMMENT '开票日期',   --CREATION_DATE
REVIEWER int(10) NULL COMMENT '复核人',   --CONFIRMED_BY
REVIEWED_TIME timestamp NULL COMMENT '复核日期',   --CONFIRMED_DATE
POSTER int(10) NULL COMMENT '过账人',   --POSTED_BY
POSTED_TIME timestamp NULL COMMENT '过账日期',   --POSTED_DATE
CREATED_BY int(10) NULL COMMENT '创建人',
CREATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
UPDATED_BY int(10) NULL COMMENT '更新人',
UPDATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`INVOICE_ID`)
)ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='发票总表';



/*
Navicat MySQL Data Transfer

Source Server         : 10.40.59.129
Source Server Version : 80018
Source Host           : 10.40.59.129:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-18 15:14:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ALL_INVOICE_INFO.sql
-- ----------------------------
DROP TABLE IF EXISTS `ALL_INVOICE_INFO.sql`;
CREATE TABLE `ALL_INVOICE_INFO` (
  `INVOICE_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '发票表ID/PK',
  `COMPANY_ID` int(20) NOT NULL COMMENT '公司ID',
  `INVOICE_NUM` varchar(20) DEFAULT NULL COMMENT '发票单据编号  AI + yyyymmdd + 1000001',
  `INVOICE_KIND` varchar(20) DEFAULT NULL COMMENT '发票种类（SPECIAL-专用发票 / ORDINARY-普通发票 / RECEIPT-收据）',
  `BILLING_METHOD` varchar(20) DEFAULT NULL COMMENT '开票方式 LB_INTEREST-回租利息票 / L_RENTAL - 直租本金票 / L_INTEREST-直租利息票 / LB_RENTAL-回租本金票',
  `BILLING_WAY` varchar(20) DEFAULT NULL COMMENT '开票方式 PAPER-纸质发票 / ELE - 电子发票',
  `MANUAL_INVOICE_CODE` varchar(20) DEFAULT NULL COMMENT '区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入',
  `MANUAL_INVOICE_NAME` varchar(20) DEFAULT NULL COMMENT '区别于根据合同创建的发票 saas开票 / 接口开票 / 线下开票数据导入',
  `DOCUMENT_CATEGORY` varchar(30) DEFAULT NULL COMMENT '单据类别',
  `COMMODITY_CODE` varchar(10) DEFAULT NULL COMMENT '商品或服务code 决定税收分类编码',
  `COMMODITY_NAME` varchar(30) DEFAULT NULL COMMENT '商品或服name 决定税收分类编码',
  `OWNER_USER_ID` int(10) DEFAULT NULL COMMENT '单据所有者（用户ID） 日后可以按量收费',
  `OBJECT_CODE` varchar(25) DEFAULT '' COMMENT '开票对象code',
  `OBJECT_NAME` varchar(255) DEFAULT '' COMMENT '开票对象name',
  `INVOICE_TITLE` varchar(30) DEFAULT NULL COMMENT '发票抬头name',
  `TAX_REGISTRY_NUM` varchar(30) DEFAULT NULL COMMENT '纳税人登记号/税收编码',
  `INVOICE_OBJECT_ADDRESS_PHONE` varchar(100) DEFAULT NULL COMMENT '地址及电话',
  `INVOICE_OBJECT_BANK_ACCOUNT` varchar(100) DEFAULT NULL COMMENT '开户行及账号',
  `INVOICE_MEMO` varchar(100) DEFAULT NULL COMMENT '备注',
  `TOTAL_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '含税金额总计',
  `TAX_AMOUNT` decimal(12,2) DEFAULT NULL COMMENT '税额总计',
  `CURRENCY` varchar(10) DEFAULT NULL COMMENT '币种',
  `EXCHANGE_RATE_TYPE` varchar(10) DEFAULT NULL COMMENT '汇率类型',
  `EXCHANGE_RATE_QUOTATION` varchar(10) DEFAULT NULL COMMENT '汇率标价方法',
  `EXCHANGE_RATE` varchar(10) DEFAULT NULL COMMENT '汇率',
  `PERIOD_CODE` varchar(30) DEFAULT NULL COMMENT '期间序号',
  `PERIOD_NAME` varchar(30) DEFAULT NULL COMMENT '期间名',
  `INVOICE_STATUS_CODE` varchar(20) DEFAULT NULL COMMENT '发票状态CODE',
  `INVOICE_STATUS_NAME` varchar(20) DEFAULT NULL COMMENT '发票状态NAME',
  `ACCOUNT_PAYEE` varchar(20) DEFAULT NULL COMMENT '收款员',
  `ACCOUNT_TIME` timestamp NULL DEFAULT NULL COMMENT '记账日期',
  `ISSUER` int(10) DEFAULT NULL COMMENT '开票人',
  `ISSUED_TIME` timestamp NULL DEFAULT NULL COMMENT '开票日期',
  `REVIEWER` int(10) DEFAULT NULL COMMENT '复核人',
  `REVIEWED_TIME` timestamp NULL DEFAULT NULL COMMENT '复核日期',
  `POSTER` int(10) DEFAULT NULL COMMENT '过账人',
  `POSTED_TIME` timestamp NULL DEFAULT NULL COMMENT '过账日期',
  `CREATED_BY` int(10) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int(10) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`INVOICE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='发票总表';
