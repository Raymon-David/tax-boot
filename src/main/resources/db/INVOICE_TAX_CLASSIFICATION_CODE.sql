CREATE TABLE INVOICE_TAX_CLASSIFICATION_CODE (
  INVOICE_TAX_CLASSIFICATION_ID int(20) NOT NULL AUTO_INCREMENT,
  TAX_CLASSIFICATION_CODE varchar(25) DEFAULT '' COMMENT '税收分类编码',  --TAX_CLASS_NUM
  GOODS_CODE varchar(25) DEFAULT '' COMMENT '商品或服务code  LEASE-融资租赁 / LEASEBACK-售后回租',--CONTRACT_TYPE
  GOODS_NAME varchar(50) DEFAULT '' COMMENT '商品或服务name  LEASE-融资租赁 / LEASEBACK-售后回租',
  GOODS_DESC varchar(200) DEFAULT '' COMMENT '商品或服务说明  ',
  PRODUCT_CODE varchar(25) DEFAULT '' COMMENT '产品线CODE',   --DIVISION
  PRODUCT_NAME varchar(25) DEFAULT '' COMMENT '产品线NAME',
  CASHFLOW_ITEM_CODE varchar(10) DEFAULT '' COMMENT '现金流项目CODE',   --CF_ITEM
  CASHFLOW_ITEM_NAME varchar(20) DEFAULT '' COMMENT '现金流项目NAME',
  GOODS_VERSION varchar(10) DEFAULT '' COMMENT '版本',
  CREATED_BY int(10) DEFAULT NULL,
  CREATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UPDATED_BY int(10) DEFAULT NULL,
  UPDATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`INVOICE_TAX_CLASSIFICATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='税收分类编码';



/* Formatted on 2020/3/20 14:39:21 (QP5 v5.256.13226.35510) */
SELECT *
  FROM (SELECT dt.class_id,
               dt.contract_type,
               (SELECT hbt.description
                  FROM hls_business_type hbt
                 WHERE dt.contract_type = hbt.business_type)
                  AS contract_type_n,
               dt.division,
               (SELECT hd.description
                  FROM hls_division hd
                 WHERE dt.division = hd.division)
                  AS division_n,
               dt.cf_item,
               (SELECT hci.description
                  FROM hls_cashflow_item hci
                 WHERE dt.cf_item = hci.cf_item)
                  AS cf_item_n,
               dt.cf_type,
               (SELECT hct.description
                  FROM hls_cashflow_type hct
                 WHERE dt.cf_type = hct.cf_type)
                  AS cf_type_n,
               tax_class_num,
               goods_version
          FROM DS_TAX_CLASS_NUM dt) t;

SELECT dt.division,
       (SELECT hd.description
          FROM hls_division hd
         WHERE dt.division = hd.division)
          AS division_n
  FROM DS_TAX_CLASS_NUM dt


/*
Navicat MySQL Data Transfer

Source Server         : 10.40.59.129
Source Server Version : 80018
Source Host           : 10.40.59.129:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-24 15:44:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for INVOICE_TAX_CLASSIFICATION_CODE.sql
-- ----------------------------
DROP TABLE IF EXISTS `INVOICE_TAX_CLASSIFICATION_CODE.sql`;
CREATE TABLE `INVOICE_TAX_CLASSIFICATION_CODE` (
  `INVOICE_TAX_CLASSIFICATION_ID` int(20) NOT NULL AUTO_INCREMENT,
  `TAX_CLASSIFICATION_CODE` varchar(25) DEFAULT '' COMMENT '税收分类编码',
  `GOODS_CODE` varchar(25) DEFAULT '' COMMENT '商品或服务code',
  `GOODS_NAME` varchar(50) DEFAULT '' COMMENT '商品或服务name LEASE-融资租赁 / LEASEBACK-售后回租',
  `GOODS_DESC` varchar(200) DEFAULT '' COMMENT '商品或服务说明',
  `PRODUCT_CODE` varchar(25) DEFAULT '' COMMENT '产品线CODE',
  `PRODUCT_NAME` varchar(25) DEFAULT '' COMMENT '产品线NAME',
  `CASHFLOW_ITEM_CODE` varchar(10) DEFAULT '' COMMENT '现金流项目CODE',
  `CASHFLOW_ITEM_NAME` varchar(20) DEFAULT '' COMMENT '现金流项目NAME',
  `GOODS_VERSION` varchar(10) DEFAULT '' COMMENT '版本',
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`INVOICE_TAX_CLASSIFICATION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='税收分类编码';


CREATE TABLE `INVOICE_TAX_CLASSIFICATION_CODE_TEMP` (
  `CLASS_ID` int(20) NOT NULL ,
  `CONTRACT_TYPE` varchar(25) DEFAULT '' COMMENT '合同类型',
  `CONTRACT_TYPE_N` varchar(25) DEFAULT '' ,
  `DIVISION` varchar(25) DEFAULT '' COMMENT '设备名称',
  `DIVISION_N` varchar(25) DEFAULT '' ,
  `CF_ITEM` varchar(25) DEFAULT '' COMMENT '现金流项目',
  `CF_ITEM_N` varchar(25) DEFAULT '',
  `CF_TYPE` varchar(25) DEFAULT '' COMMENT '现金流类型',
  `CF_TYPE_N` varchar(25) DEFAULT '' ,
  `TAX_CLASS_NUMB` varchar(25) DEFAULT '' COMMENT '税收分类编码',
	`GOODS_VERSION` varchar(25) DEFAULT '' COMMENT '版本',
  `CREATED_BY` int(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` int(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`CLASS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='税收分类编码临时表';