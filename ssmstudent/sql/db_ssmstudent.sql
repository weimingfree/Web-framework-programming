/*
Navicat MySQL Data Transfer

Source Server         : 本地333
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_ssmstudent

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-07-29 10:06:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES ('1', 'admin', 'admin', '1');

-- ----------------------------
-- Table structure for s_clazz
-- ----------------------------
DROP TABLE IF EXISTS `s_clazz`;
CREATE TABLE `s_clazz` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `info` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_clazz
-- ----------------------------
INSERT INTO `s_clazz` VALUES ('1', '软件工程一班', '软件工程专业。');
INSERT INTO `s_clazz` VALUES ('4', '英语一班', '英语专业');
INSERT INTO `s_clazz` VALUES ('5', '计算机科学与技术一班', '计算机专业');
INSERT INTO `s_clazz` VALUES ('9', '机械工程一班', '机械专业');

-- ----------------------------
-- Table structure for s_course
-- ----------------------------
DROP TABLE IF EXISTS `s_course`;
CREATE TABLE `s_course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `teacher_id` int(5) NOT NULL,
  `course_date` varchar(32) DEFAULT NULL,
  `selected_num` int(5) NOT NULL DEFAULT '0',
  `max_num` int(5) NOT NULL DEFAULT '50',
  `cgrade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_teacher_foreign` (`teacher_id`) USING BTREE,
  CONSTRAINT `course_teacher_foreign` FOREIGN KEY (`teacher_id`) REFERENCES `s_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_course
-- ----------------------------
INSERT INTO `s_course` VALUES ('1', '大学英语', '9', '周一上午8点', '1', '50', '3');
INSERT INTO `s_course` VALUES ('2', '大学数学', '10', '周二上午10点', '1', '50', '3');
INSERT INTO `s_course` VALUES ('3', '计算机基础', '11', '周三上午9点', '2', '50', '4');
INSERT INTO `s_course` VALUES ('14', '网络工程', '11', '周四下午2点', '1', '56', '5');
INSERT INTO `s_course` VALUES ('15', 'SSM整合', '12', '周五上午10点', '1', '50', '6');
INSERT INTO `s_course` VALUES ('16', '英雄联盟', '11', '周六下午3点', '1', '30', '3');

-- ----------------------------
-- Table structure for s_selected_course
-- ----------------------------
DROP TABLE IF EXISTS `s_selected_course`;
CREATE TABLE `s_selected_course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) NOT NULL,
  `course_id` int(5) NOT NULL,
  `teacher_id` int(5) NOT NULL,
  `score_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `selected_course_student_fk` (`student_id`) USING BTREE,
  KEY `selected_course_course_fk` (`course_id`) USING BTREE,
  KEY `teacher_id` (`teacher_id`) USING BTREE,
  CONSTRAINT `s_selected_course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `s_teacher` (`id`),
  CONSTRAINT `selected_course_course_fk` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`),
  CONSTRAINT `selected_course_student_fk` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_selected_course
-- ----------------------------
INSERT INTO `s_selected_course` VALUES ('23', '4', '1', '9', '60');
INSERT INTO `s_selected_course` VALUES ('25', '11', '3', '11', '90');

-- ----------------------------
-- Table structure for s_student
-- ----------------------------
DROP TABLE IF EXISTS `s_student`;
CREATE TABLE `s_student` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sn` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `clazz_id` int(5) NOT NULL,
  `sex` varchar(5) NOT NULL DEFAULT '男',
  `mobile` varchar(12) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`,`sn`) USING BTREE,
  KEY `student_clazz_id_foreign` (`clazz_id`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  CONSTRAINT `student_clazz_id_foreign` FOREIGN KEY (`clazz_id`) REFERENCES `s_clazz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_student
-- ----------------------------
INSERT INTO `s_student` VALUES ('1', 'S11528202944239', '张三', '123456', '1', '女', '13918654525', '30');
INSERT INTO `s_student` VALUES ('4', 'S51528379586807', '李四', '123456', '5', '男', '13356565656', '12');
INSERT INTO `s_student` VALUES ('9', 'S41528633634989', '王五', '123456', '5', '男', '13333332133', '18');
INSERT INTO `s_student` VALUES ('11', 'S51608100081223', '赵六', '123456', '5', '男', '15013962014', '18');
INSERT INTO `s_student` VALUES ('12', 'S91608101472505', '小明', '123456', '5', '男', '13428563965', '30');

-- ----------------------------
-- Table structure for s_teacher
-- ----------------------------
DROP TABLE IF EXISTS `s_teacher`;
CREATE TABLE `s_teacher` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `sn` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `clazz_id` int(5) NOT NULL,
  `sex` varchar(5) NOT NULL DEFAULT '男',
  `tposition` varchar(12) DEFAULT NULL,
  `salary` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`,`sn`) USING BTREE,
  KEY `student_clazz_id_foreign` (`clazz_id`) USING BTREE,
  KEY `id` (`id`) USING BTREE,
  CONSTRAINT `s_teacher_ibfk_1` FOREIGN KEY (`clazz_id`) REFERENCES `s_clazz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of s_teacher
-- ----------------------------
INSERT INTO `s_teacher` VALUES ('9', 'T11528608730648', '刘老师', '123456', '1', '男', '教授', '2000');
INSERT INTO `s_teacher` VALUES ('10', 'T11528609224588', '李老师', '123456', '4', '男', '院长', '1000');
INSERT INTO `s_teacher` VALUES ('11', 'T51528617262403', '马老师', '123456', '9', '男', '讲师', '1000');
INSERT INTO `s_teacher` VALUES ('12', 'T91588750367766', '王老师', '123456', '9', '男', '校长', '2000');
