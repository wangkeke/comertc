/*
Navicat MySQL Data Transfer

Source Server         : 192.168.9.34mysql
Source Server Version : 50715
Source Host           : 192.168.9.34:3306
Source Database       : comertc

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2022-02-24 10:18:21
*/

SET FOREIGN_KEY_CHECKS=0;

create database `comertc`;

-- ----------------------------
-- Table structure for rtc_device
-- ----------------------------
DROP TABLE IF EXISTS `rtc_device`;
CREATE TABLE `rtc_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(50) DEFAULT NULL,
  `device_code` varchar(50) DEFAULT NULL,
  `rtsp_url` varchar(255) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of rtc_device
-- ----------------------------

-- ----------------------------
-- Table structure for rtc_meet
-- ----------------------------
DROP TABLE IF EXISTS `rtc_meet`;
CREATE TABLE `rtc_meet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meet_name` varchar(50) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `meet_code` varchar(50) DEFAULT NULL,
  `meet_pwd` varchar(20) DEFAULT NULL,
  `creator_uid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of rtc_meet
-- ----------------------------
INSERT INTO `rtc_meet` VALUES ('1', '测试会议-1', '2022-02-24 18:00:00', null, '66471435', '123456', '1', '0', '0', null, '2022-02-23 15:16:41');
INSERT INTO `rtc_meet` VALUES ('2', '测试会议-2', '2022-02-24 18:00:00', null, '59781c3f', '111111', '1', '0', '0', null, '2022-02-23 15:18:51');

-- ----------------------------
-- Table structure for rtc_room
-- ----------------------------
DROP TABLE IF EXISTS `rtc_room`;
CREATE TABLE `rtc_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(50) DEFAULT NULL,
  `room_code` varchar(50) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `last_live_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of rtc_room
-- ----------------------------
INSERT INTO `rtc_room` VALUES ('1', '天天吃鸡', 'ttcj', '0', null, null, '2022-02-21 18:54:04', '1');

-- ----------------------------
-- Table structure for rtc_user
-- ----------------------------
DROP TABLE IF EXISTS `rtc_user`;
CREATE TABLE `rtc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `portrait` varchar(255) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of rtc_user
-- ----------------------------
INSERT INTO `rtc_user` VALUES ('1', 'admin', '123456', 'admin', null, '1', '0', '2022-02-22 10:35:40', '2022-02-21 18:53:21');
