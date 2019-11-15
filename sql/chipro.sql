/*
 Navicat Premium Data Transfer

 Source Server         : cihog
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : chipro

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/11/2019 10:17:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for OAUTH_CLIENT_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `OAUTH_CLIENT_DETAILS`;
CREATE TABLE `OAUTH_CLIENT_DETAILS` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of OAUTH_CLIENT_DETAILS
-- ----------------------------
BEGIN;
INSERT INTO `OAUTH_CLIENT_DETAILS` VALUES ('chipro', '', '$2a$10$RwmYWQFTmLIfAVAtmZcjZuKMMX6pTBwVeuFugLexKUYMwWbd8V4zi', 'read,write', 'password,refresh_token,authorization_code,sms_code', 'https://www.baidu.com', NULL, NULL, NULL, NULL, NULL);
COMMIT;

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
INSERT INTO `OSS_USER` VALUES ('1', 'admin', '李利光', '$2a$10$nP143nNR5jMX50A8t3fnIeEyjlkjBDcEsHp/rsTLY6I0mpU2FmI4O', '13711919653', '903857227@qq.com', 'F', '2019-11-10', 1, '2019-11-30 21:39:43', '2019-11-13 11:30:50', '1', NULL, NULL);
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

SET FOREIGN_KEY_CHECKS = 1;
