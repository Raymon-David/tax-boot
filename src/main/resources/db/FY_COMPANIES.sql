/*
Navicat MySQL Data Transfer

Source Server         : 10.40.59.129
Source Server Version : 80018
Source Host           : 10.40.59.129:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-23 16:49:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for FY_COMPANIES.sql
-- ----------------------------
DROP TABLE IF EXISTS `FY_COMPANIES.sql`;
CREATE TABLE `FY_COMPANIES` (
  `COMPANY_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `COMPANY_CODE` varchar(25) DEFAULT '' COMMENT '公司code',
  `COMPANY_TYPE` int(1) DEFAULT '1' COMMENT '公司状态，默认 1 为存续',
  `COMPANY_ADDRESS` varchar(500) DEFAULT NULL COMMENT '公司地址',
  `COMPANY_PHONE` varchar(20) DEFAULT NULL COMMENT '公司电话',
  `ACTIVE_START_DATE` timestamp NULL DEFAULT NULL COMMENT '存续开始时间',
  `ACTIVE_END_DATE` timestamp NULL DEFAULT NULL COMMENT '存续结束时间',
  `INVOICE_TITLE` varchar(200) DEFAULT NULL COMMENT '公司发票抬头',
  `TAX_REGISTRY_NUM` varchar(30) DEFAULT NULL COMMENT '公司税收编码',
  `INVOICE_COMPANY_ADDRESS_PHONE_NUM` varchar(200) DEFAULT NULL COMMENT '公司发票地址电话',
  `INVOICE_COMPANY_BANK_ACCOUNT` varchar(200) DEFAULT NULL COMMENT '公司账户所属银行',
  `COMPANY_FULL_NAME` varchar(200) DEFAULT NULL COMMENT '公司全称',
  `COMPANY_SHORT_NAME` varchar(200) DEFAULT NULL COMMENT '公司简称',
  `COMPANY_BANK_ACCOUNT_ID` varchar(200) DEFAULT NULL COMMENT '公司账号',
  `COMPANY_ELE_MARGIN` int(20) DEFAULT NULL COMMENT '公司电子发票余量',
  `COMPANY_CUSTOMER_ID` varchar(50) DEFAULT NULL COMMENT '公司电子合同id',
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10784 DEFAULT CHARSET=utf8 COMMENT='公司信息表';

-- ----------------------------
-- Records of FY_COMPANIES.sql
-- ----------------------------
INSERT INTO `FY_COMPANIES` VALUES ('1', '10001', '1', '北京市朝阳区佳程广场B座20层CD单元', '010-84547200', '1997-01-01 16:45:19', '2030-12-31 16:45:37', '斗山(中国)融资租赁有限公司', '91110000717868202R', '北京市朝阳区佳程广场B座20层CD单元', '1', '斗山（中国）融资租赁有限公司', '斗山融资租赁', '1', '99999', '6CC4352B52B36490456108B6C22158E0', null, '2020-03-23 08:48:31', null, '2020-03-23 08:48:31');
