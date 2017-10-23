

/*==============================================================*/
/* Table: MARK_COUPON_RECEIVE                                   */
/*==============================================================*/
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE;
CREATE TABLE MARK_COUPON_RECEIVE
(
   ID                   INT  AUTO_INCREMENT COMMENT '主键',
   TITLE                VARCHAR(256) COMMENT '活动名称',
   COUPON_ID            INT COMMENT '批次号',
   COUPON_NAME          VARCHAR(256) COMMENT '优惠券名称',
   END_TIME             DATE COMMENT '结束时间',
   START_TIME           DATE COMMENT '开始时间',
   VALID                VARCHAR(2) COMMENT '是否开启',
   SCOPE                INT COMMENT '范围（主站 1，无线 2 ，全部 3）',
   RECEVICE_NUMBER      INT COMMENT '领取数量',
   OBJECT               INT COMMENT '对象(用户名1，预留 2)',
   CREATE_TIME          TIMESTAMP  COMMENT '创建时间',
   UPDATE_TIME          TIMESTAMP  COMMENT '更新时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='优惠券领取活动表';

CREATE INDEX RECEIVE_LIMIT_COUPON_ID_IDX ON MARK_COUPON_RECEIVE
(
   COUPON_ID
);


/*==============================================================*/
/* Table: MARK_COUPON_RECEIVE_COMMON                            */
/*==============================================================*/
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_COMMON;
CREATE TABLE MARK_COUPON_RECEIVE_COMMON
(
   ID                    INT  AUTO_INCREMENT COMMENT '主键',
   RECEIVE_ID            INT,
   COUPON_ID             INT,
   CREATE_TIME          TIMESTAMP  COMMENT '创建时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='优惠券领取活动关联表';

/*==============================================================*/
/* Index: RECEIVE_ID                                            */
/*==============================================================*/
CREATE INDEX RECEIVE_COMMON_RECEIVE_ID_IDX ON MARK_COUPON_RECEIVE_COMMON
(
   RECEIVE_ID
);

/*==============================================================*/
/* Index: COUPON_ID                                             */
/*==============================================================*/
CREATE INDEX RECEIVE_COMMON_COUPON_ID_IDX ON MARK_COUPON_RECEIVE_COMMON
(
   COUPON_ID
);

DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_CATEGORY;

/*==============================================================*/
/* Table: MARK_COUPON_RECEIVE_CATEGORY                          */
/*==============================================================*/
CREATE TABLE MARK_COUPON_RECEIVE_CATEGORY
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   RECEIVE_ID           INT,
   COUPON_ID            INT,
   CATEGORY_ID          INT,
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='优惠券领取活动品类';

CREATE INDEX IDX_COUPON_RECEIVE_CATEGORY_RECEIVE_ID ON MARK_COUPON_RECEIVE_CATEGORY
(
   RECEIVE_ID
);

CREATE INDEX IDX_COUPON_RECEIVE_CATEGORY_COUPON_ID ON MARK_COUPON_RECEIVE_CATEGORY
(
   COUPON_ID
);




DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_PRODUCT;

/*==============================================================*/
/* Table: MARK_COUPON_RECEIVE_PRODUCT                           */
/*==============================================================*/
CREATE TABLE MARK_COUPON_RECEIVE_PRODUCT
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   RECEIVE_ID           INT,
   COUPON_ID            INT,
   PRODUCT_ID           BIGINT,
   PRODUCT_TYPE         VARCHAR(30) COMMENT'PRODUCT 产品    GOODS  商品',
   CREATE_TIME          TIMESTAMP  COMMENT '创建时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='优惠券领取活动产品绑定表';




DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_0;

/*==============================================================*/
/* Table: MARK_COUPON_RECEIVE_LIMIT_0                          */
/*==============================================================*/
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_0
(
   ID                   BIGINT COMMENT '主键',
   USER_ID              BIGINT COMMENT '用户id',
   RECEIVE_ID           BIGINT COMMENT '领取活动id',
   COUNT                INT COMMENT '领取数量',
   OBJECT               INT COMMENT '领取对象（主站 1，无线 2 ，全部 3）',
   CREATE_TIME          TIMESTAMP  COMMENT '创建时间',
   UPDATE_TIME          TIMESTAMP  COMMENT '创建时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='记录用户领取表';

/*==============================================================*/
/* Index: RECEIVE_ID         USER_ID                                   */
/*==============================================================*/
CREATE UNIQUE INDEX RECEIVE_LIMIT_RECEIVE_ID_IDX ON MARK_COUPON_RECEIVE_LIMIT_0
(
   USER_ID,RECEIVE_ID
);
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_1;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_1 LIKE MARK_COUPON_RECEIVE_LIMIT_0;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_2;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_2 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_3;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_3 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_4;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_4 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_5;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_5 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_6;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_6 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_7;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_7 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_8;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_8 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_9;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_9 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_10;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_10 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_11;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_11 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_12;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_12 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_13;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_13 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_14;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_14 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_15;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_15 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_16;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_16 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_17;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_17 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_18;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_18 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_19;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_19 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_20;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_20 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_21;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_21 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_22;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_22 LIKE MARK_COUPON_RECEIVE_LIMIT_1;
DROP TABLE IF EXISTS MARK_COUPON_RECEIVE_LIMIT_23;
CREATE TABLE MARK_COUPON_RECEIVE_LIMIT_23 LIKE MARK_COUPON_RECEIVE_LIMIT_1;

