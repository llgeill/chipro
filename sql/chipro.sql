/*
 Navicat Premium Data Transfer

 Source Server         : 林海虹-阿里云-mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 106.15.72.153:3306
 Source Schema         : chipro

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 05/02/2020 21:55:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for COMMUNITY_PRODUCTION
-- ----------------------------
DROP TABLE IF EXISTS `COMMUNITY_PRODUCTION`;
CREATE TABLE `COMMUNITY_PRODUCTION` (
  `PRODUCTION_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '作品编码',
  `USER_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户编码',
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作品名称',
  `INTRODUCE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '介绍',
  `INSTRUCTION` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  `LABEL` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '标签',
  `MOBILE_KEYBOARD` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '手机键盘',
  `RESOURCE_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '资源编码',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(0) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PRODUCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='作品';

-- ----------------------------
-- Table structure for MANAGE_CLASS_ROOM
-- ----------------------------
DROP TABLE IF EXISTS `MANAGE_CLASS_ROOM`;
CREATE TABLE `MANAGE_CLASS_ROOM` (
  `CLASS_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '课室编码',
  `NAME` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '课室名称',
  `CODE` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '课室编码',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `SCHOOL_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '学校编码',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`CLASS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='课室';

-- ----------------------------
-- Table structure for MANAGE_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `MANAGE_RESOURCE`;
CREATE TABLE `MANAGE_RESOURCE` (
  `RESOURCE_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资源编码',
  `RESOURCE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '资源名称',
  `RESOURCE_PATH` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源路径',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源';

-- ----------------------------
-- Table structure for MANAGE_REVIEW
-- ----------------------------
DROP TABLE IF EXISTS `MANAGE_REVIEW`;
CREATE TABLE `MANAGE_REVIEW` (
  `REVIEW_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '点评编码',
  `TASK_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '任务编码',
  `USER_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '学生编码',
  `REVIEW_CONTENT` longtext COLLATE utf8_bin COMMENT '点评内容',
  `SCOPE` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '作品得分',
  `PRODUCTION_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '作品编码',
  `STATUS` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改编码',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`REVIEW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='作品点评';

-- ----------------------------
-- Table structure for MANAGE_SCHOOL
-- ----------------------------
DROP TABLE IF EXISTS `MANAGE_SCHOOL`;
CREATE TABLE `MANAGE_SCHOOL` (
  `SCHOOL_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '学校编码',
  `PID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父编码',
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '学校名称',
  `CODE` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '学校编码',
  `EMAIL` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '学校邮箱',
  `PRINCIPAL` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学校校长',
  `ADDRESS` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '学校地址',
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '学校电话',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`SCHOOL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学校';

-- ----------------------------
-- Table structure for MANAGE_TASK
-- ----------------------------
DROP TABLE IF EXISTS `MANAGE_TASK`;
CREATE TABLE `MANAGE_TASK` (
  `TASK_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '任务编码',
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '任务名称',
  `ASSIGNED_CLASS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '指派课室',
  `TITLE` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '任务标题',
  `WORKS_COUNT` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '作品提交数量',
  `WORKS_REQUIRE` longtext COLLATE utf8_bin COMMENT '作品要求',
  `EDITOR` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '编辑器版本',
  `RESOURCE_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '资源编码',
  `DEADLINE` datetime DEFAULT NULL COMMENT '截止时间',
  `CREATE_USER` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER` varchar(0) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `REMARKS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务';

-- ----------------------------
-- Table structure for OSS_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `OSS_PERMISSION`;
CREATE TABLE `OSS_PERMISSION` (
  `PERMISSION_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限id',
  `PERMISSION_NAME` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `PERMISSION_NOTE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限说明',
  `PERMISSION_REMARK` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限备注',
  `PERMISSION_ICON` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限图标',
  `PERMISSION_PATH` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '权限路径',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_PERSON` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_PERSON` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Records of OSS_PERMISSION
-- ----------------------------
BEGIN;
INSERT INTO `OSS_PERMISSION` VALUES ('1', 'token授权', '用于授权', '无', NULL, '/oauth/token', '2019-11-10 16:32:45', '1', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for OSS_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `OSS_ROLE`;
CREATE TABLE `OSS_ROLE` (
  `ROLE_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `ROLE_NAME` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_PERSON` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_PERSON` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of OSS_ROLE
-- ----------------------------
BEGIN;
INSERT INTO `OSS_ROLE` VALUES ('1', 'user', '2019-11-10 12:05:34', '1', NULL, NULL);
INSERT INTO `OSS_ROLE` VALUES ('2', 'admin', '2019-11-10 12:05:48', '1', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for OSS_ROLE_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `OSS_ROLE_PERMISSION`;
CREATE TABLE `OSS_ROLE_PERMISSION` (
  `ROLE_AND_PERMISSION_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `ROLE_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `PERMISSION_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_PERSON` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ROLE_AND_PERMISSION_ID`) USING BTREE,
  KEY `OSS_ROLE_PERMISSION_ROLE_ID` (`ROLE_ID`),
  KEY `OSS_ROLE_PERMISSION_PERMISSION_ID` (`PERMISSION_ID`),
  CONSTRAINT `OSS_ROLE_PERMISSION_PERMISSION_ID` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `OSS_PERMISSION` (`PERMISSION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OSS_ROLE_PERMISSION_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `OSS_ROLE` (`ROLE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限关系表';

-- ----------------------------
-- Records of OSS_ROLE_PERMISSION
-- ----------------------------
BEGIN;
INSERT INTO `OSS_ROLE_PERMISSION` VALUES ('1', '1', '1', '2019-11-10 16:33:16', '1');
COMMIT;

-- ----------------------------
-- Table structure for OSS_USER
-- ----------------------------
DROP TABLE IF EXISTS `OSS_USER`;
CREATE TABLE `OSS_USER` (
  `USER_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `USER_NAME` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `USER_NAME_ALIAS` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `PASSWORD` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `GENDER` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` date DEFAULT NULL COMMENT '生日',
  `IS_ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `FAILURE_TIME` datetime DEFAULT NULL COMMENT '失效时间',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_PERSON` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_PERSON` varchar(0) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of OSS_USER
-- ----------------------------
BEGIN;
INSERT INTO `OSS_USER` VALUES ('1', 'admin', '李利光', '$2a$10$nP143nNR5jMX50A8t3fnIeEyjlkjBDcEsHp/rsTLY6I0mpU2FmI4O', '13711919653', '903857227@qq.com', 'F', '2019-11-10', 1, '2020-12-31 21:39:43', '2019-11-13 11:30:50', '1', NULL, NULL);
INSERT INTO `OSS_USER` VALUES ('1224329073141022721', '1224329072419602433', NULL, '$2a$10$pV1SRUe1at0qJ3jZchmJPent01Da2BbAtNnJ661CK.3PMP48EEL8u', NULL, '13711919653@163.com', NULL, NULL, 1, '2021-02-03 21:49:32', '2020-02-03 21:49:33', '1', NULL, NULL);
INSERT INTO `OSS_USER` VALUES ('1224335312885387266', '1224335311870365697', NULL, '$2a$10$IQ4ZeHmhwX2apZ/bol9K3Or22OcMb2dVIicFd/tl06IqqXxUuuSAW', NULL, '427002055@qq.com', NULL, NULL, 1, '2021-02-03 14:14:20', '2020-02-03 14:14:20', '1', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for OSS_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `OSS_USER_ROLE`;
CREATE TABLE `OSS_USER_ROLE` (
  `USER_AND_ROLE_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `USER_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `ROLE_ID` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `CREATE_PERSON` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  PRIMARY KEY (`USER_AND_ROLE_ID`) USING BTREE,
  KEY `OSS_USER_ROLE_USER_ID` (`USER_ID`),
  KEY `OSS_USER_ROLE_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `OSS_USER_ROLE_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `OSS_ROLE` (`ROLE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OSS_USER_ROLE_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `OSS_USER` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

-- ----------------------------
-- Records of OSS_USER_ROLE
-- ----------------------------
BEGIN;
INSERT INTO `OSS_USER_ROLE` VALUES ('1', '1', '1', '2019-11-10 12:06:03', '1');
INSERT INTO `OSS_USER_ROLE` VALUES ('2', '1', '2', '2019-11-10 12:06:12', '1');
COMMIT;

-- ----------------------------
-- Table structure for TEST
-- ----------------------------
DROP TABLE IF EXISTS `TEST`;
CREATE TABLE `TEST` (
  `TEST_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `TEST_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `TEST_CONTENT` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATE_PERSON` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `UPDATE_PERSON` datetime DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测试';

-- ----------------------------
-- Records of TEST
-- ----------------------------
BEGIN;
INSERT INTO `TEST` VALUES ('06ca8017449b3764b68c9e122b13aafa', 'llg', NULL, '2020-01-25 17:20:20', '1', NULL, NULL);
INSERT INTO `TEST` VALUES ('1', '测试名称', '测试内容', '2020-01-25 17:18:44', '1', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `CLIENT_ID` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '客户端id',
  `RESOURCE_IDS` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源id集合',
  `CLIENT_SECRET` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端密码',
  `SCOPE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端权限',
  `AUTHORIZED_GRANT_TYPES` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端授权模式',
  `WEB_SERVER_REDIRECT_URI` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端重定向uri',
  `AUTHORITIES` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户的权限范围',
  `ACCESS_TOKEN_VALIDITY` int(11) DEFAULT NULL COMMENT 'access_token的有效时间(秒)',
  `REFRESH_TOKEN_VALIDITY` int(11) DEFAULT NULL COMMENT 'refresh_token有效期(秒)',
  `ADDITIONAL_INFORMATION` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '补充信息(json)',
  `AUTOAPPROVE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否自动approval操作',
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Oauth客户端信息';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('chipro', '', '$2a$10$RwmYWQFTmLIfAVAtmZcjZuKMMX6pTBwVeuFugLexKUYMwWbd8V4zi', 'read,write', 'password,refresh_token,authorization_code,sms_code,email_code', 'https://www.baidu.com', NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
