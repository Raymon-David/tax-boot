CREATE TABLE DCFL_QUERY_INVOICE_TEMP (
  DOCUMENT_NUMBER varchar(100) COMMENT '单据编号',
  INVOICE_CODE varchar(20) NULL COMMENT '发票代码',
  INVOICE_NUMBER varchar(20) NULL COMMENT '发票号',
  INVOICE_TITLE varchar(1000) DEFAULT '' COMMENT '购方名称',
  TAX_REGISTRY_NUM varchar(100) NULL COMMENT '购方税号',
  INVOICE_OBJECT_ADDRESS_PHONE varchar(2000) NULL COMMENT '购方地址电话',
  INVOICE_OBJECT_BANK_ACCOUNT varchar(2000) NULL COMMENT '开户行+账号',
  ISSUED_TIME timestamp NULL COMMENT '开票日期',
  ISSUED_MONTH varchar(10) COMMENT '所属月份',
  TOTAL_AMOUNT decimal(12,2) NULL COMMENT '含税金额 订单总价合计',
  TAX_NET_AMOUNT decimal(12,2) NULL COMMENT '不含税金额 合计金额',
  TAX_RATE decimal(12,2) COMMENT '税率',
  TAX_AMOUNT decimal(12,2) NULL COMMENT '税额 合计税额',
  INVOICE_MEMO varchar(2000) NULL COMMENT '备注',
  ISSUER varchar(20) NULL COMMENT '开票人',
  ACCOUNT_PAYEE varchar(20) NULL COMMENT '收款员',
  INVOICE_INVALID_FLAG varchar(10) NULL COMMENT '发票作废标志',
  REVIEWER varchar(20) NULL COMMENT '复核人',
  CF_ITEM varchar(25) DEFAULT '' ,
  CF_ITEM_DESC varchar(25) DEFAULT '' ,
  CREATED_BY int(10) DEFAULT NULL,
  CREATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UPDATED_BY int(10) DEFAULT NULL,
  UPDATE_TIME timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='从DCFL销项发票查询导入数据 临时表';


SELECT ah.document_number,                                              --单据编号
       ah.bp_name,                                                      --发票抬头
       ah.bp_tax_registry_num,                                          --纳税人识别号
       ah.bp_address_phone_num,
       ah.bp_bank_account,
       (DECODE (ah.contract_id, NULL, NULL, ah.description)) AS description,  --备注
       NVL (ah.total_amount_old, ah.total_amount) AS total_amount,  --总金额
       ah.tax_amount,   --税额
       (select max(al.tax_type_rate)
        from acr_invoice_ln al
        where al.invoice_hd_id = ah.invoice_hd_id ) as tax_type_rate,
       (select sum(al.net_amount)
        from acr_invoice_ln al
        where al.invoice_hd_id = ah.invoice_hd_id ) as net_amount,
       ah.internal_period_num,   --区间
       (NVL ( (SELECT he.bill_date
                 FROM acr_ele_invoice_hd he
                WHERE he.invoice_hd_id = ah.invoice_hd_id),
             ah.invoice_date))
          AS invoice_date,  --开票日期
       (NVL (ah.invoice_number,
             (SELECT he.yfphm
                FROM acr_ele_invoice_hd he
               WHERE he.invoice_hd_id = ah.invoice_hd_id)))
          AS invoice_number,   --发票号码
       ah.invalid_flag ,   --作废标志
       ah.confirmed_date,                                               --复核日期
       (SELECT u.description
          FROM sys_user u
         WHERE u.user_id = ah.confirmed_by)
          AS confirmed_by_n,                                             --复核人
       (SELECT u.description
          FROM sys_user u
         WHERE u.user_id = ah.created_by)
          AS created_by_n,                                               --开票人
       ah.creation_date,     --创建日期
       (NVL (ah.vat_invoice_code,
             (SELECT he.yfpdm
                FROM acr_ele_invoice_hd he
               WHERE he.invoice_hd_id = ah.invoice_hd_id)))
          vat_invoice_code                                            -- 发票代码
  FROM acr_invoice_hd ah
  where ah.accounting_date between sysdate - 7 and sysdate


/* Formatted on 2020/6/10 15:09:59 (QP5 v5.256.13226.35510) */
  SELECT a.document_number,
         MAX (a.bp_name) bp_name,
         MAX (a.bp_tax_registry_num) bp_tax_registry_num,
         MAX (a.bp_address_phone_num) bp_address_phone_num,
         MAX (a.bp_bank_account) bp_bank_account,
         MAX (a.description) description,
         MAX (a.total_amount) total_amount,
         SUM (a.tax_amount) tax_amount,
         MAX (a.tax_type_rate) tax_type_rate,
         SUM (a.net_amount) net_amount,
         MAX (a.internal_period_num) internal_period_num,
         MAX (a.invoice_date) invoice_date,
         MAX (a.invoice_number) invoice_number,
         MAX (a.invalid_flag) invalid_flag,
         MAX (a.confirmed_date) confirmed_date,
         MAX (a.confirmed_by_n) confirmed_by_n,
         MAX (a.created_by_n) created_by_n,
         MAX (a.creation_date) creation_date,
         MAX (a.vat_invoice_code) vat_invoice_code,
         CASE WHEN MAX (a.cf_item) = 101 THEN 1 ELSE MAX (a.cf_item) END
            cf_item,
         CASE
            WHEN MAX (a.cf_item_n) = '租息' THEN '租金'
            ELSE MAX (a.cf_item_n)
         END
            cf_item_desc
    FROM ACR_INVOICE_HD_DETAIL_LV a
   WHERE a.accounting_date BETWEEN SYSDATE - 7 AND SYSDATE
GROUP BY a.document_number




CREATE VIEW DCFL_QUERY_INVOICE_V
AS
SELECT
A.DOCUMENT_NUMBER,
concat(A.INVOICE_CODE, A.INVOICE_NUMBER) AS KEYWORDS,
A.INVOICE_CODE,
A.INVOICE_NUMBER,
A.INVOICE_TITLE,
A.TAX_REGISTRY_NUM,
A.INVOICE_OBJECT_ADDRESS_PHONE,
A.INVOICE_OBJECT_BANK_ACCOUNT,
A.ISSUED_TIME,
A.ISSUED_MONTH,
A.TOTAL_AMOUNT,
A.TAX_NET_AMOUNT,
A.TAX_RATE,
A.TAX_AMOUNT,
A.INVOICE_MEMO,
A.ISSUER,
A.REVIEWER,
A.CF_ITEM,
A.CF_ITEM_DESC,
(SELECT WEEKLY_NAME
	FROM DCFL_WEEKLY_CLASSIFICATION B
	WHERE B.CASHFLOW_NAME = A.CF_ITEM_DESC) AS CASHFLOW_NAME,
CASE WHEN A.INVOICE_INVALID_FLAG = 'Y' THEN '作废'
WHEN A.INVOICE_INVALID_FLAG = 'N' THEN '未作废' END AS INVOICE_INVALID_FLAG
FROM DCFL_QUERY_INVOICE_TEMP A


#HISTORY
CREATE TABLE DCFL_QUERY_INVOICE_TEMP_HISTORY
AS
SELECT * FROM DCFL_QUERY_INVOICE_TEMP
WHERE 1 = 2


SELECT A.CASHFLOW_NAME AS 现金流项目,
       SUM(A.TOTAL_AMOUNT) AS 开票金额
FROM DCFL_QUERY_INVOICE_V A
GROUP BY A.CASHFLOW_NAME