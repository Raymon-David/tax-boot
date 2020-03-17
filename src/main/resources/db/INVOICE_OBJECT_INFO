/*
Navicat MySQL Data Transfer

Source Server         : 10.40.59.129
Source Server Version : 80018
Source Host           : 10.40.59.129:3306
Source Database       : tax

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-03-16 10:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for INVOICE_OBJECT_INFO
-- ----------------------------
DROP TABLE IF EXISTS `INVOICE_OBJECT_INFO`;
CREATE TABLE `INVOICE_OBJECT_INFO` (
                                       `INVOICE_OBJECT_INFO_ID` int(20) NOT NULL AUTO_INCREMENT,
                                       `OBJECT_CODE` varchar(25) DEFAULT '' COMMENT '开票对象code',
                                       `OBJECT_NAME` varchar(255) DEFAULT '' COMMENT '开票对象name',
                                       `INVOICE_TITLE` varchar(255) DEFAULT '' COMMENT '发票抬头',
                                       `SOCIAL_CODE` varchar(30) DEFAULT '' COMMENT '统一信用代码',
                                       `TAX_REGISTRY_NUM` varchar(30) DEFAULT '' COMMENT '税收编码',
                                       `INVOICE_OBJECT_ADDRESS` varchar(100) DEFAULT '' COMMENT '开票对象地址',
                                       `INVOICE_OBJECT_BANK_ACCOUNT_NUM` varchar(30) DEFAULT '' COMMENT '开票对象银行账号',
                                       `INVOICE_OBJECT_BANK` varchar(255) DEFAULT '' COMMENT '开票对象银行',
                                       `INVOICE_OBJECT_PHONE_NUM` varchar(20) DEFAULT '' COMMENT '开票对象手机',
                                       `PHONE` varchar(20) DEFAULT '' COMMENT '手机',
                                       `INVOICE_PHONE` varchar(20) DEFAULT '' COMMENT '发票手机',
                                       `INVOICE_INFO_TYPE` varchar(1) DEFAULT '' COMMENT '发票验证状态',
                                       `CREATED_BY` int(10) DEFAULT NULL,
                                       `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `UPDATED_BY` int(10) DEFAULT NULL,
                                       `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       PRIMARY KEY (`INVOICE_OBJECT_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10783 DEFAULT CHARSET=utf8 COMMENT='开票对象信息表';


Insert into tax.INVOICE_OBJECT_INFO
(OBJECT_CODE, OBJECT_NAME, INVOICE_TITLE, SOCIAL_CODE, TAX_REGISTRY_NUM, INVOICE_OBJECT_ADDRESS,
 INVOICE_OBJECT_BANK_ACCOUNT_NUM, INVOICE_OBJECT_BANK, INVOICE_OBJECT_PHONE_NUM, PHONE, INVOICE_PHONE,
 INVOICE_INFO_TYPE)
Values
('IOI201000001', '鸡西市久运砂石销售有限公司', '鸡西市久运砂石销售有限公司', '91230300MA1ATQ2P46', '91230300MA1ATQ2P46', '黑龙江省鸡西市滴道区新华委A座1号楼0-106',
 '08703101040005163', '中国农业银行股份有限公司鸡西滴道支行', '13945853633', '13945853633', '13945853633',
 'Y');

INSERT INTO tax.INVOICE_OBJECT_INFO (
    OBJECT_CODE,
    OBJECT_NAME,
    INVOICE_TITLE,
    SOCIAL_CODE,
    TAX_REGISTRY_NUM,
    INVOICE_OBJECT_ADDRESS,
    INVOICE_OBJECT_BANK_ACCOUNT_NUM,
    INVOICE_OBJECT_BANK,
    INVOICE_OBJECT_PHONE_NUM,
    PHONE,
    INVOICE_PHONE,
    INVOICE_INFO_TYPE
)
SELECT
    concat(
            'IOI',
            SUBSTR(
                    date_format(now(), '%Y%m%d'),
                    3,
                    2
                ),
            trim(
                        ifnull(
                                max(SUBSTR(t.OBJECT_CODE, 6, 12)),
                                0
                            ) + 1
                )
        ),
    '潍坊望宇建筑工程有限公司',
    '潍坊望宇建筑工程有限公司',
    '91370704MA3M9MH261',
    '91370704MA3M9MH261',
    '山东省潍坊市坊子区双羊街266号金禾·馨苑6号楼2-402',
    '1607020409200088423',
    '中国工商银行潍坊坊子支行',
    '15628712288',
    '13953500470',
    '13953500470',
    'Y'
FROM
    INVOICE_OBJECT_INFO t
;
Insert into tax.INVOICE_OBJECT_INFO
(OBJECT_CODE, OBJECT_NAME, INVOICE_TITLE, SOCIAL_CODE, TAX_REGISTRY_NUM, INVOICE_OBJECT_ADDRESS,
 INVOICE_OBJECT_BANK_ACCOUNT_NUM, INVOICE_OBJECT_BANK, INVOICE_OBJECT_PHONE_NUM, PHONE, INVOICE_INFO_TYPE)
SELECT
    concat(
            'IOI',
            SUBSTR(
                    date_format(now(), '%Y%m%d'),
                    3,
                    2
                ),
            trim(
                        ifnull(
                                max(SUBSTR(t.OBJECT_CODE, 6, 12)),
                                0
                            ) + 1
                )
        ),'扬州盛安渣土运输有限公司', '扬州盛安渣土运输有限公司', '91321002570376069U', '91321002570376069U', '扬州市广陵产业园龙泉路68号',
    '10156901040010465', '中国农业银行京杭支行', '0514-87903933', '13815821209', 'Y'  FROM INVOICE_OBJECT_INFO t;
Insert into tax.INVOICE_OBJECT_INFO
(OBJECT_CODE, OBJECT_NAME, INVOICE_TITLE, SOCIAL_CODE, TAX_REGISTRY_NUM, INVOICE_OBJECT_ADDRESS,
 INVOICE_OBJECT_BANK_ACCOUNT_NUM, INVOICE_OBJECT_BANK, INVOICE_OBJECT_PHONE_NUM, PHONE, INVOICE_PHONE,
 INVOICE_INFO_TYPE)
SELECT
    concat(
            'IOI',
            SUBSTR(
                    date_format(now(), '%Y%m%d'),
                    3,
                    2
                ),
            trim(
                        ifnull(
                                max(SUBSTR(t.OBJECT_CODE, 6, 12)),
                                0
                            ) + 1
                )
        ),'烟台圭悦建筑装饰工程有限公司', '烟台圭悦建筑装饰工程有限公司', '91370600MA3N06WM3B', '91370600MA3N06WM3B', '烟台开发区长江路18号彩云城西区四层4404号',
    '9060106041042058001019', '山东蓬莱农村商业银行股份有限公司潮水支行', '13792518539', '13792518539', '13792518539',
    'Y'  FROM INVOICE_OBJECT_INFO t;
Insert into tax.INVOICE_OBJECT_INFO
(OBJECT_CODE, OBJECT_NAME, INVOICE_TITLE, SOCIAL_CODE, TAX_REGISTRY_NUM, INVOICE_OBJECT_ADDRESS,
 INVOICE_OBJECT_BANK_ACCOUNT_NUM, INVOICE_OBJECT_BANK, INVOICE_OBJECT_PHONE_NUM, PHONE, INVOICE_PHONE,
 INVOICE_INFO_TYPE)
SELECT
    concat(
            'IOI',
            SUBSTR(
                    date_format(now(), '%Y%m%d'),
                    3,
                    2
                ),
            trim(
                        ifnull(
                                max(SUBSTR(t.OBJECT_CODE, 6, 12)),
                                0
                            ) + 1
                )
        ),'内蒙古天耀建设工程有限责任公司', '内蒙古天耀建设工程有限责任公司', '91150602MA0Q559B91', '91150602MA0Q559B91', '内蒙古自治区鄂尔多斯市东胜区鑫隆小区3号楼3单元306室',
    '152460457671', '中国银行股份有限公司鄂尔多斯市分行', '13337076368', '13337076368', '13337076368',
    'Y'  FROM INVOICE_OBJECT_INFO t;
