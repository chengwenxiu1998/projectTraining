/*
Navicat MySQL Data Transfer

Source Server         : hello
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : timebank

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-12-05 15:35:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `chat_record`
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `u_id_1` int(11) NOT NULL,
  `u_id_2` int(11) NOT NULL,
  `cr_record_text_url` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`u_id_1`,`u_id_2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_record
-- ----------------------------

-- ----------------------------
-- Table structure for `coin_trage_record`
-- ----------------------------
DROP TABLE IF EXISTS `coin_trage_record`;
CREATE TABLE `coin_trage_record` (
  `u_id` int(11) NOT NULL,
  `ctr_finish_time` datetime NOT NULL,
  `ctr_count` int(11) DEFAULT NULL,
  `ctr_is_add_or_reduce` bit(1) DEFAULT NULL,
  PRIMARY KEY (`u_id`,`ctr_finish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coin_trage_record
-- ----------------------------
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 10:50:21', '20', '');
INSERT INTO `coin_trage_record` VALUES ('2', '2018-12-29 10:50:21', '20', '');
INSERT INTO `coin_trage_record` VALUES ('3', '2018-12-29 11:51:26', '30', '');
INSERT INTO `coin_trage_record` VALUES ('4', '2018-12-05 11:51:26', '30', '');
INSERT INTO `coin_trage_record` VALUES ('5', '2018-12-29 12:53:24', '40', '');
INSERT INTO `coin_trage_record` VALUES ('6', '2018-12-29 12:53:24', '40', '');
INSERT INTO `coin_trage_record` VALUES ('7', '2018-12-29 13:54:06', '20', '');
INSERT INTO `coin_trage_record` VALUES ('8', '2018-12-29 12:54:06', '20', '');
INSERT INTO `coin_trage_record` VALUES ('9', '2018-12-29 12:56:40', '30', '');
INSERT INTO `coin_trage_record` VALUES ('10', '2018-12-29 12:56:40', '30', '');

-- ----------------------------
-- Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `d_id` int(11) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `u_id_louzhu` int(11) DEFAULT NULL,
  `d_topic_content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('1', '1', '1', '代取快递完成成效怎么样？');
INSERT INTO `discuss` VALUES ('2', '2', '2', '代买饭送的准时吗？');
INSERT INTO `discuss` VALUES ('3', '3', '3', '约网球感受怎么样？');

-- ----------------------------
-- Table structure for `discuss_reply`
-- ----------------------------
DROP TABLE IF EXISTS `discuss_reply`;
CREATE TABLE `discuss_reply` (
  `d_id` int(11) NOT NULL DEFAULT '0',
  `u_id_reply` int(11) NOT NULL DEFAULT '0',
  `dr_reply_content` varchar(1000) DEFAULT NULL,
  `dr_reply_time` datetime NOT NULL,
  PRIMARY KEY (`d_id`,`u_id_reply`,`dr_reply_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss_reply
-- ----------------------------
INSERT INTO `discuss_reply` VALUES ('1', '2', '很准时送到！', '2018-12-29 11:08:24');
INSERT INTO `discuss_reply` VALUES ('2', '3', '按要求时间内送达，很好。', '2018-12-29 11:09:06');
INSERT INTO `discuss_reply` VALUES ('3', '1', '送饭很快！', '2018-12-29 13:09:31');
INSERT INTO `discuss_reply` VALUES ('4', '3', '饭还热着，很好！', '2018-12-29 13:12:02');
INSERT INTO `discuss_reply` VALUES ('5', '1', '对方很专业！', '2018-12-30 11:10:30');

-- ----------------------------
-- Table structure for `shaishai`
-- ----------------------------
DROP TABLE IF EXISTS `shaishai`;
CREATE TABLE `shaishai` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) DEFAULT NULL,
  `s_accept_person_say` varchar(800) DEFAULT NULL,
  `s_send_person_say` varchar(800) DEFAULT NULL,
  `s_like_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shaishai
-- ----------------------------
INSERT INTO `shaishai` VALUES ('1', '1', '能充分利用自己的时间！', '节省了很多时间，很方便！', '129');
INSERT INTO `shaishai` VALUES ('2', '2', '能通过剩余时间获得利润，很好！', '准时送达！', '79');
INSERT INTO `shaishai` VALUES ('3', '3', 'Good!', '都是按要求做的，方便快捷。', '80');

-- ----------------------------
-- Table structure for `shaishai_comment`
-- ----------------------------
DROP TABLE IF EXISTS `shaishai_comment`;
CREATE TABLE `shaishai_comment` (
  `s_id` int(11) NOT NULL DEFAULT '0',
  `u_id_comment` int(11) NOT NULL DEFAULT '0',
  `c_comment_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `c_comment_content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`s_id`,`u_id_comment`,`c_comment_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shaishai_comment
-- ----------------------------
INSERT INTO `shaishai_comment` VALUES ('1', '1', '2018-12-29 13:55:51', '是在规定时间送到的吗？');
INSERT INTO `shaishai_comment` VALUES ('2', '2', '2018-12-29 14:56:28', '使用体验怎么样？');
INSERT INTO `shaishai_comment` VALUES ('3', '3', '2018-12-30 13:57:08', '是按要求做的吗？');

-- ----------------------------
-- Table structure for `sign_in`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in`;
CREATE TABLE `sign_in` (
  `u_id` int(11) NOT NULL,
  `sign_day_count` int(11) DEFAULT NULL,
  `if_sign_in` bit(1) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in
-- ----------------------------
INSERT INTO `sign_in` VALUES ('1', '3', '');
INSERT INTO `sign_in` VALUES ('2', '4', '');
INSERT INTO `sign_in` VALUES ('3', '6', '');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_text` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '代取快递');
INSERT INTO `tag` VALUES ('2', '代饭');
INSERT INTO `tag` VALUES ('3', '代接水');
INSERT INTO `tag` VALUES ('4', '约逛街');
INSERT INTO `tag` VALUES ('5', '约运动');
INSERT INTO `tag` VALUES ('6', '约学习');
INSERT INTO `tag` VALUES ('7', '其他');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id_send` int(11) NOT NULL,
  `u_time` datetime DEFAULT NULL,
  `tc_id` int(11) DEFAULT NULL,
  `t_desc` varchar(1000) DEFAULT NULL,
  `t_coin_count` int(11) DEFAULT NULL,
  `t_state` varchar(10) DEFAULT NULL,
  `u_id_accept` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `t_endtime` datetime DEFAULT NULL,
  `t_accept_time` datetime DEFAULT NULL,
  `t_finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', '2018-12-13 10:14:04', '1', '有没有小伙伴去一食堂取快递呀', '20', '待接收', '1', '1', '2018-12-20 10:15:14', null, null);
INSERT INTO `task` VALUES ('2', '2', '2018-12-28 15:25:10', '2', '有没有小伙伴可以帮忙带饭', '10', '进行中', '3', '2', '2018-12-28 16:26:04', '2018-12-28 16:00:19', null);
INSERT INTO `task` VALUES ('3', '3', '2018-12-29 15:26:55', '1', '有人需要陪练网球吗', '15', '已完成', '1', '1', '2018-12-29 17:27:42', '2018-12-29 15:31:03', '2018-12-29 17:26:22');

-- ----------------------------
-- Table structure for `task_category`
-- ----------------------------
DROP TABLE IF EXISTS `task_category`;
CREATE TABLE `task_category` (
  `tc_id` int(11) NOT NULL AUTO_INCREMENT,
  `tc_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of task_category
-- ----------------------------
INSERT INTO `task_category` VALUES ('1', '卖时间');
INSERT INTO `task_category` VALUES ('2', '买时间');

-- ----------------------------
-- Table structure for `task_image`
-- ----------------------------
DROP TABLE IF EXISTS `task_image`;
CREATE TABLE `task_image` (
  `ti_id` int(11) NOT NULL AUTO_INCREMENT,
  `it_image` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`ti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_image
-- ----------------------------
INSERT INTO `task_image` VALUES ('1', 'image/p1.jpg');
INSERT INTO `task_image` VALUES ('2', 'image/p2.jpg');
INSERT INTO `task_image` VALUES ('3', 'image/p3.jpg');
INSERT INTO `task_image` VALUES ('4', 'image/p4.jpg');
INSERT INTO `task_image` VALUES ('5', 'image/p5.jpg');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) DEFAULT NULL,
  `u_phone` varchar(11) DEFAULT NULL,
  `u_sex` bit(1) DEFAULT NULL,
  `u_area` varchar(60) DEFAULT NULL,
  `u_nickname` varchar(20) DEFAULT NULL,
  `u_image` varchar(50) DEFAULT NULL,
  `u_password` varchar(20) DEFAULT NULL,
  `u_id_card` char(18) DEFAULT NULL,
  `u_coin` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '程文秀', '18032762013', '', '河北省石家庄市河北师范大学', '满满', null, '123', '123456', '100');
INSERT INTO `users` VALUES ('2', '刘曼', '18033873961', '', '河北省石家庄市裕华区裕翔街道', '遇见', null, '456', '234567', '100');
INSERT INTO `users` VALUES ('3', '张帅', '15226515859', '', '河北省石家庄市河北师范大学', '帅帅', null, '789', '345678', null);
