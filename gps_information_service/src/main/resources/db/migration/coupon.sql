/*==============================================================*/
/* DBMS name:      MARK 优惠券表新系统                                                                             */
/* Created on:     2017/2/23 19:09:48                           */
/* author    :     wufahang                                     */
/*==============================================================*/

-- ----------------------------
-- Table structure for mark_coupon
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon`;
CREATE TABLE `mark_coupon` (
  `coupon_id`           BIGINT not null auto_increment comment '优惠ID',
  `channel_id`          BIGINT  comment '优惠券渠道ID',
  `coupon_name`         varchar(150) comment '优惠名称',
  `coupon_target`       varchar(30) comment '优惠目标(PRODUCT、ORDER)',
  `coupon_type`         varchar(30) comment 'A：无限次使用 B：只能使用一次B类一次是有 （有效期类）',
  `with_code`           varchar(10) comment '有无号码(true/false)',
  `begin_time`          datetime comment '有效开始时间',
  `first_code`          varchar(10) comment '开头号码',
  `valid`                 varchar(10) comment '是否有效',
  `create_time`         datetime comment '创建时间',
  `description`         text comment '描述',
  `payment_channel`     varchar(30) comment '支付渠道',
  `memo`                    varchar(200) comment '备注',
  `valid_type`          varchar(30) comment '日期有效期类型',
  `term_of_validity`    int comment '有效期',
  `favor_type`          varchar(150) comment '优惠策略类型',
  `argument_x`          int comment '优惠X参数',
  `argument_y`          int comment '优惠Y参数',
  `argument_z`          int comment '优惠Z参数',
  `max_coupon`          int comment '-1:表示没有最大限制的优惠金额;其他数字表示最大限度的优惠金额',
  `used_coupon`         int comment '已经使用过的优惠金额',
  `scope`                 text comment '使用范围',
  `scope_type`          varchar(20) comment '使用范围类型',
  `day_after`           int comment 'X天后',
  `user_type`           varchar(10) comment '用户类型，是否新用户true/false',
  `platform`             varchar(200)  comment '使用平台（VST/MOBILE） 主站/无线',
  `bindtarget`          varchar(20) comment '',
  `bindplatform`        varchar(20) comment '跟PLATFORM重复',
  `bindlimitno`         bigint comment '主站/无线总领取条数',
  `update_time`         datetime DEFAULT CURRENT_TIMESTAMP  comment '更新时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠活动表';

ALTER TABLE mark_coupon ADD INDEX `IDX_MARK_COUPON_CHANNEL_ID` (`channel_id`);

-- ----------------------------
-- Table structure for mark_coupon_code
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_code_0`;
CREATE TABLE `mark_coupon_code_0` (
  `coupon_code_id`      BIGINT not null comment '号码ID',
  `coupon_id`           BIGINT not null  comment '优惠活动ID',
  `coupon_code`         varchar(30) comment '号码CODE',
  `used`                varchar(10) comment '是否已经使用(true/false)',
  `begin_time`          datetime comment '优惠券开始日期',
  `end_time`            datetime comment '优惠券结束日期',
  `create_time`         datetime comment '优惠券创建日期',
  `update_time`         datetime comment '跟新时间',
  `got_time`            datetime comment '领取时间' default NULL ,
  `task_file_id`        int comment '优惠券任务文件id',
  PRIMARY KEY (`coupon_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券表';

ALTER TABLE mark_coupon_code_0 ADD INDEX `IDX_MARK_COUPON_CODE2` (`coupon_id`);
ALTER TABLE mark_coupon_code_0 ADD INDEX `IDX_MARK_COUPON_CODE1` (`coupon_code`);
ALTER TABLE mark_coupon_code_0 ADD INDEX `IDX_MARK_COUPON_TIME` (`update_time`);

DROP TABLE IF EXISTS mark_coupon_code_1;
create table mark_coupon_code_1 like mark_coupon_code_0;
DROP TABLE IF EXISTS mark_coupon_code_2;
create table mark_coupon_code_2 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_3;
create table mark_coupon_code_3 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_4;
create table mark_coupon_code_4 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_5;
create table mark_coupon_code_5 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_6;
create table mark_coupon_code_6 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_7;
create table mark_coupon_code_7 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_8;
create table mark_coupon_code_8 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_9;
create table mark_coupon_code_9 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_10;
create table mark_coupon_code_10 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_11;
create table mark_coupon_code_11 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_12;
create table mark_coupon_code_12 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_13;
create table mark_coupon_code_13 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_14;
create table mark_coupon_code_14 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_15;
create table mark_coupon_code_15 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_16;
create table mark_coupon_code_16 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_17;
create table mark_coupon_code_17 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_18;
create table mark_coupon_code_18 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_19;
create table mark_coupon_code_19 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_20;
create table mark_coupon_code_20 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_21;
create table mark_coupon_code_21 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_22;
create table mark_coupon_code_22 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_23;
create table mark_coupon_code_23 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_24;
create table mark_coupon_code_24 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_25;
create table mark_coupon_code_25 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_26;
create table mark_coupon_code_26 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_27;
create table mark_coupon_code_27 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_28;
create table mark_coupon_code_28 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_29;
create table mark_coupon_code_29 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_30;
create table mark_coupon_code_30 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_31;
create table mark_coupon_code_31 like mark_coupon_code_1;
DROP TABLE IF EXISTS mark_coupon_code_32;
-- ----------------------------
-- Table structure for mark_coupon_code_history
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_code_history_0`;
CREATE TABLE `mark_coupon_code_history_0` (
  `coupon_id`             BIGINT not null  comment '优惠活动ID',
  `coupon_code_id`         BIGINT not null comment '号码ID',
  `coupon_code`           varchar(30) comment '号码CODE',
  `used`                       varchar(10) comment '是否已经使用(true/false)',
  `begin_time`           datetime comment '优惠券开始日期',
  `end_time`               datetime comment '优惠券结束日期',
  `create_time`         datetime comment '优惠券创建日期',
  `update_time`         datetime comment '跟新时间',
  `got_time`              datetime comment '领取时间',
  `task_file_id`        BIGINT comment '优惠券任务文件id',
  PRIMARY KEY (`coupon_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE mark_coupon_code_history_0 ADD INDEX `IDX_MARK_COUPON_CODE_HISTORY_2` (`coupon_id`);
ALTER TABLE mark_coupon_code_history_0 ADD INDEX `IDX_MARK_COUPON_CODE_HISTORY_1` (`coupon_code`);
ALTER TABLE mark_coupon_code_history_0 ADD INDEX `IDX_MARK_COUPON_CODE_HISTORY` (`update_time`);

DROP TABLE IF EXISTS mark_coupon_code_history_1;
CREATE TABLE mark_coupon_code_history_1 LIKE mark_coupon_code_history_0;
DROP TABLE IF EXISTS mark_coupon_code_history_2;
CREATE TABLE mark_coupon_code_history_2 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_3;
CREATE TABLE mark_coupon_code_history_3 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_4;
CREATE TABLE mark_coupon_code_history_4 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_5;
CREATE TABLE mark_coupon_code_history_5 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_6;
CREATE TABLE mark_coupon_code_history_6 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_7;
CREATE TABLE mark_coupon_code_history_7 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_8;
CREATE TABLE mark_coupon_code_history_8 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_9;
CREATE TABLE mark_coupon_code_history_9 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_10;
CREATE TABLE mark_coupon_code_history_10 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_11;
CREATE TABLE mark_coupon_code_history_11 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_12;
CREATE TABLE mark_coupon_code_history_12 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_13;
CREATE TABLE mark_coupon_code_history_13 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_14;
CREATE TABLE mark_coupon_code_history_14 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_15;
CREATE TABLE mark_coupon_code_history_15 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_16;
CREATE TABLE mark_coupon_code_history_16 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_17;
CREATE TABLE mark_coupon_code_history_17 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_18;
CREATE TABLE mark_coupon_code_history_18 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_19;
CREATE TABLE mark_coupon_code_history_19 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_20;
CREATE TABLE mark_coupon_code_history_20 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_21;
CREATE TABLE mark_coupon_code_history_21 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_22;
CREATE TABLE mark_coupon_code_history_22 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_23;
CREATE TABLE mark_coupon_code_history_23 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_24;
CREATE TABLE mark_coupon_code_history_24 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_25;
CREATE TABLE mark_coupon_code_history_25 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_26;
CREATE TABLE mark_coupon_code_history_26 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_27;
CREATE TABLE mark_coupon_code_history_27 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_28;
CREATE TABLE mark_coupon_code_history_28 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_29;
CREATE TABLE mark_coupon_code_history_29 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_30;
CREATE TABLE mark_coupon_code_history_30 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_31;
CREATE TABLE mark_coupon_code_history_31 like mark_coupon_code_history_1;
DROP TABLE IF EXISTS mark_coupon_code_history_32;


-- ----------------------------
-- Table structure for mark_coupon_code_task
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_code_task`;
CREATE TABLE `mark_coupon_code_task` (
  `id`                          BIGINT not null auto_increment comment '任务ID',
  `mark_coupon_id`      BIGINT comment '优惠活动ID',
  `file_num`                int comment '优惠券生成总数量',
  `task_name`           varchar(100) comment '生成优惠券的任务名称',
  `create_time`         datetime comment '创建时间',
  `over_time`           datetime comment '结束时间',
  `task_status`         varchar(20) comment '状态',
  `operator`                varchar(60) comment '操作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '优惠券码生成任务表';

ALTER TABLE mark_coupon_code_task ADD INDEX `IDX_TASK_MARK_COUPON_ID` (`mark_coupon_id`);

-- ----------------------------
-- Table structure for mark_coupon_code_task_file
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_code_task_file`;
CREATE TABLE `mark_coupon_code_task_file` (
  `id`                                  BIGINT not null auto_increment  comment '优惠券码生成文件记录ID',
  `task_id`                         BIGINT not null comment '任务ID',
  `num`                                 int comment '优惠券生成数量',
  `file_name`                   varchar(60) comment '文件名',
  `path`                                varchar(60) comment '文件路径',
  `create_time`          datetime comment '创建时间',
  `generate_time`        datetime comment '生成时间',
  `task_file_status`     varchar(20) comment '状态，生成/失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '优惠券码生成文件表';

ALTER TABLE mark_coupon_code_task_file ADD INDEX `CODE_TASK_FILE_INDEX` (TASK_ID);

-- ----------------------------
-- Table structure for mark_coupon_point_change
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_point_change`;
CREATE TABLE `mark_coupon_point_change` (
  `mark_points_change_coupon_id`    BIGINT NOT NULL auto_increment comment '积分兑换优惠券ID',
  `point`                                     int comment '兑换积分',
  `sub_product_type`                      varchar(30) comment '产品子类型',
  `coupon_id`                               BIGINT comment '优惠批次ID',
  `create_time`                     datetime comment '创建时间',
  PRIMARY KEY (`mark_points_change_coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '积分兑换优惠券';

-- ----------------------------
-- Table structure for mark_coupon_product
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_product`;
CREATE TABLE `mark_coupon_product` (
  `coupon_product_id`           BIGINT NOT NULL auto_increment comment 'ID',
  `product_id`                          BIGINT comment '产品ID，如果为产品类型则为空',
  `coupon_id`                           BIGINT comment '优惠活动ID',
  `amount`                                  int comment '优惠金额，如果此处配置，已当前配置为准',
  `sub_product_type`                varchar(30) comment '产品子类型，对应绑定产品类型页面的子类型',
  `coupon_product_type`         tinyint  comment '1为产品ID 2为产品类型',
  `system`                                  varchar(50) comment '所属系统，空或SUPER均表示SUPER系统',
  `branch_type`                         varchar(20) comment '类型：PROD产品|BRANCH商品',
  `coupon_flag`                 varchar(50) comment 'true或null表示可以使用优惠券,false表示不可以使用优惠券',
  PRIMARY KEY (`coupon_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '优惠活动绑定的产品ID,类型,及黑名单';
        
ALTER TABLE mark_coupon_product ADD INDEX `IDX_MARK_COUPON_PRODUCT1` (`coupon_id`);
ALTER TABLE mark_coupon_product ADD INDEX `IDX_MARK_COUPON_PRODUCT2` (`sub_product_type`);
ALTER TABLE mark_coupon_product ADD INDEX `IDX_MCP_PID` (`product_id`);


-- ----------------------------
-- Table structure for mark_coupon_relate_user
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_relate_user_0`;
CREATE TABLE `mark_coupon_relate_user_0` (
  `coupon_relate_user_id`                   BIGINT NOT NULL  comment 'ID',
  `coupon_code_id`                                  BIGINT comment '优惠券ID',
  `coupon_id`                      BIGINT comment '优惠券活动D',
  `user_id`                                                 BIGINT comment '用户ID',
  `send_flag`                                           varchar(50) comment '会给用户发信息，此为标志位',
  `deviceno`                                                varchar(50) comment '',
  `create_time`                             datetime comment '创建日期',
  `update_time`                             datetime comment '更新日期',
  `red_envelope`                            tinyint  comment '是否红包   1 是  0 否',
  `coupon_code_create_time`                           datetime comment '券码生成时间',
  `coupon_code_used`                             varchar(10) comment '券码是否已经使用(true/false)',
  PRIMARY KEY (`coupon_relate_user_id`)      
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '优惠券跟用户绑定表';

ALTER TABLE mark_coupon_relate_user_0 ADD INDEX `IDX_MARK_COUPON_RELATE_USER1` (`user_id`);
ALTER TABLE mark_coupon_relate_user_0 ADD INDEX `IDX_MARK_COUPON_RELATE_USER_COUPON_CODE` (`coupon_code_id`);
ALTER TABLE mark_coupon_relate_user_0 ADD INDEX `IDX_MARK_COUPON_RELATE_USER_COUPON` (`coupon_id`);
ALTER TABLE mark_coupon_relate_user_0 ADD INDEX `IDX_MARK_COUPON_RELATE_USER_CREATE_TIME` (`create_time`);

DROP TABLE IF EXISTS mark_coupon_relate_user_1;
CREATE TABLE mark_coupon_relate_user_1 LIKE mark_coupon_relate_user_0;
DROP TABLE IF EXISTS mark_coupon_relate_user_2;
CREATE TABLE mark_coupon_relate_user_2 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_3;
CREATE TABLE mark_coupon_relate_user_3 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_4;
CREATE TABLE mark_coupon_relate_user_4 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_5;
CREATE TABLE mark_coupon_relate_user_5 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_6;
CREATE TABLE mark_coupon_relate_user_6 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_7;
CREATE TABLE mark_coupon_relate_user_7 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_8;
CREATE TABLE mark_coupon_relate_user_8 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_9;
CREATE TABLE mark_coupon_relate_user_9 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_10;
CREATE TABLE mark_coupon_relate_user_10 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_11;
CREATE TABLE mark_coupon_relate_user_11 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_12;
CREATE TABLE mark_coupon_relate_user_12 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_13;
CREATE TABLE mark_coupon_relate_user_13 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_14;
CREATE TABLE mark_coupon_relate_user_14 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_15;
CREATE TABLE mark_coupon_relate_user_15 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_16;
CREATE TABLE mark_coupon_relate_user_16 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_17;
CREATE TABLE mark_coupon_relate_user_17 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_18;
CREATE TABLE mark_coupon_relate_user_18 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_19;
CREATE TABLE mark_coupon_relate_user_19 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_20;
CREATE TABLE mark_coupon_relate_user_20 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_21;
CREATE TABLE mark_coupon_relate_user_21 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_22;
CREATE TABLE mark_coupon_relate_user_22 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_23;
CREATE TABLE mark_coupon_relate_user_23 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_24;
CREATE TABLE mark_coupon_relate_user_24 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_25;
CREATE TABLE mark_coupon_relate_user_25 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_26;
CREATE TABLE mark_coupon_relate_user_26 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_27;
CREATE TABLE mark_coupon_relate_user_27 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_28;
CREATE TABLE mark_coupon_relate_user_28 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_29;
CREATE TABLE mark_coupon_relate_user_29 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_30;
CREATE TABLE mark_coupon_relate_user_30 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_31;
CREATE TABLE mark_coupon_relate_user_31 LIKE mark_coupon_relate_user_1;
DROP TABLE IF EXISTS mark_coupon_relate_user_32;



-- ----------------------------
-- Table structure for mark_membership_card
-- ----------------------------
DROP TABLE IF EXISTS `mark_membership_card`;
CREATE TABLE `mark_membership_card` (
  `card_id`                             BIGINT not null auto_increment comment '标识',
  `channel_id`                      BIGINT comment '渠道标识',
  `card_prefix_number`      varchar(10) comment '批次号',
  `amount`                              int comment '数量',
  `create_time`                     datetime comment '创建时间',
  `binding_channel_time`    datetime comment '绑定渠道时间',
  `is_binding_discount`     varchar(10) comment '是否绑定优惠措施',
  `binding_discount_time`   datetime comment '绑定优惠措施的时间',
  `is_activate`                         varchar(10) comment '是否已激活',
  `update_time`             datetime DEFAULT CURRENT_TIMESTAMP  ,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

ALTER TABLE mark_membership_card ADD INDEX `IDX_MSCARD_CHANNEL_ID` (`channel_id`);
ALTER TABLE mark_membership_card ADD INDEX `IDX_MSCARD_ISACTIVATE` (`is_activate`);
ALTER TABLE mark_membership_card ADD INDEX `IDX_MSCARD_PREFIX_NUMBER` (`card_prefix_number`);

-- ----------------------------
-- Table structure for mark_membership_card_code
-- ----------------------------
DROP TABLE IF EXISTS `mark_membership_card_code`;
CREATE TABLE `mark_membership_card_code` (
 `card_id`                     BIGINT not null auto_increment comment '标识',
 `card_code`                varchar(20) not null  comment '会员卡号',
  `used`                        varchar(10) comment '是否使用(true/false)',
  `create_time`         datetime comment '创建时间',
  `update_time`         datetime DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE mark_membership_card_code ADD INDEX `IDX_MEMBERSHIP_CARD_CODE` (`card_code`);
-- ----------------------------
-- Table structure for mark_membership_card_discount
-- ----------------------------
DROP TABLE IF EXISTS `mark_membership_card_discount`;
CREATE TABLE `mark_membership_card_discount` (
  `card_discount_id`            BIGINT not null auto_increment comment '标识',
  `card_id`                             BIGINT comment '标识',
  `create_time`                     datetime comment '创建时间',
  `operator_name`               varchar(50) comment '',
  `coupon_id`                   decimal(11,0) comment '优惠券活动ID',
  PRIMARY KEY (`card_discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE mark_membership_card_discount ADD INDEX `IDX_MSCARD_DISCOUNT_CARDID` (`card_id`);


-- ----------------------------
-- Table structure for mark_m_c_c_history
-- ----------------------------
DROP TABLE IF EXISTS `mark_m_c_c_history`;
CREATE TABLE `mark_m_c_c_history` (
  `card_code`                   varchar(20)  NOT NULL comment '会员卡号',
  `card_id`                         BIGINT comment '标识',
  `used`                                varchar(10) comment '是否使用(true/false)',
  `create_time`             datetime comment ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mark_m_c_d_history
-- ----------------------------
DROP TABLE IF EXISTS `mark_m_c_d_history`;
CREATE TABLE `mark_m_c_d_history` (
  `card_discount_id`        BIGINT NOT NULL  comment '标示',
  `card_id`                         BIGINT comment '标识',
  `create_time`                 datetime comment '创建时间',
  `operator_name`           varchar(50) comment '',
  `coupon_id`               decimal(11,0) comment '优惠券活动ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mark_m_c_history
-- ----------------------------
DROP TABLE IF EXISTS `mark_m_c_history`;
CREATE TABLE `mark_m_c_history` (
  `card_id`                                 BIGINT not null  comment '标识',
  `channel_id`                          BIGINT comment '渠道标识',
  `card_prefix_number`          varchar(10) comment '批次号',
  `amount`                                  int comment '数量',
  `create_time`                         datetime comment '创建时间',
  `binding_channel_time`        datetime comment '绑定渠道时间',
  `is_binding_discount`         varchar(10) comment '是否绑定优惠措施',
  `binding_discount_time`   datetime comment '绑定优惠措施的时间',
  `is_activate`                         varchar(10) comment '是否已激活',
  `update_time`             datetime DEFAULT CURRENT_TIMESTAMP  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mark_coupon_usage
-- ----------------------------
DROP TABLE IF EXISTS `mark_coupon_usage`;
CREATE TABLE `mark_coupon_usage` (
  `usage_id`            bigint not null auto_increment comment '使用ID',
  `coupon_code_id`      bigint DEFAULT NULL comment '号码ID',
  `object_id`           bigint DEFAULT NULL comment '对象ID',
  `object_type`         varchar(30) DEFAULT NULL comment '对象类型',
  `create_time`         datetime DEFAULT NULL comment '',
  `sub_object_id_b`     bigint DEFAULT NULL comment '子类型IDB' ,
  `strategy`            varchar(30) DEFAULT NULL comment '策略' ,
  `amount`               int DEFAULT NULL comment '',
  `update_time`         datetime DEFAULT NULL comment '优惠金额',  
  `coupon_id`           bigint DEFAULT NULL comment '优惠活动ID',
  PRIMARY KEY (`usage_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '优惠券码使用表';

ALTER TABLE mark_coupon_usage ADD INDEX `idx_mark_coupon_usage_code_id` (`coupon_code_id`);
ALTER TABLE mark_coupon_usage ADD INDEX `idx_mark_coupon_usage_coupon_id` (`coupon_id`);
ALTER TABLE mark_coupon_usage ADD INDEX `IDX_MARK_COUPON_USAGE` (`object_id`,`object_type`);

-- ------------------------------
-- Table structure for mark_coupon_dept
-- ------------------------------
DROP TABLE IF EXISTS `mark_coupon_dept`;
CREATE TABLE `mark_coupon_dept` (
  `coupon_dept_id`    BIGINT NOT NULL AUTO_INCREMENT COMMENT '优惠费用承担部门ID',
  `coupon_id`         BIGINT NOT NULL COMMENT '优惠活动ID',
  `assume_dept`       VARCHAR(40) NOT NULL COMMENT '承担部分中文名',
  `assume_percent`    DECIMAL(6, 2) NOT NULL COMMENT '承担费用比例',
  `assume_dept_value` VARCHAR(40) COMMENT '承担部门值',
  `create_time`       DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time`       datetime NULL ON UPDATE CURRENT_TIMESTAMP  COMMENT '上次更新时间',
  PRIMARY KEY (`coupon_dept_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '优惠券部门费用承担表';
ALTER TABLE mark_coupon_dept ADD INDEX `idx_mark_coupon_dept_coupon_id` (`coupon_id`);
