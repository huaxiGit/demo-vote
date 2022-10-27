/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : allen_vote

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 27/10/2022 14:37:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_vote
-- ----------------------------
DROP TABLE IF EXISTS `pms_vote`;
CREATE TABLE `pms_vote`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `vote_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '投票主题名称',
  `vote_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '投票介绍',
  `signup_begin_time` datetime(0) NULL DEFAULT NULL COMMENT '报名开始时间',
  `signup_end_time` datetime(0) NULL DEFAULT NULL COMMENT '报名结束时间',
  `vote_begin_time` datetime(0) NULL DEFAULT NULL COMMENT '投票开始时间',
  `vote_end_time` datetime(0) NULL DEFAULT NULL COMMENT '投票结束时间',
  `vote_status` int(10) NULL DEFAULT NULL COMMENT ' 状态（1 ：选举结束 0 ：正在选举中）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_vote
-- ----------------------------
INSERT INTO `pms_vote` VALUES (18, 'allen学生选举获得', '大学活动选举校草，大学来参与', NULL, NULL, '2022-10-25 17:50:22', '2022-10-27 13:14:22', 0, '2022-10-27 13:25:20', NULL, 0);
INSERT INTO `pms_vote` VALUES (19, 'allen学生选举获得', '大学活动选举校草，大学来参与', NULL, NULL, '2022-10-27 13:59:22', '2022-10-27 13:59:59', 0, '2022-10-27 14:00:07', NULL, 0);

-- ----------------------------
-- Table structure for pms_vote_detail
-- ----------------------------
DROP TABLE IF EXISTS `pms_vote_detail`;
CREATE TABLE `pms_vote_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `vote_id` bigint(20) NULL DEFAULT NULL COMMENT '活动id',
  `vote_signup_id` bigint(20) NULL DEFAULT NULL COMMENT '用户选举',
  `signup_user_id` bigint(20) NULL DEFAULT NULL COMMENT '投票用户',
  `signup_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '选举名称主题',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_vote_detail
-- ----------------------------

-- ----------------------------
-- Table structure for pms_vote_signup
-- ----------------------------
DROP TABLE IF EXISTS `pms_vote_signup`;
CREATE TABLE `pms_vote_signup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户参与id',
  `signup_index` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '报名编号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `vote_id` bigint(20) NULL DEFAULT NULL COMMENT '报名编号',
  `signup_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '选举名称',
  `vote_num` bigint(10) NULL DEFAULT 0 COMMENT '投票数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_vote_signup
-- ----------------------------
INSERT INTO `pms_vote_signup` VALUES (6, '20221027130001', 11, 14, 'allen学生选举获得', 0, '2022-10-27 13:18:52', NULL, 0);
INSERT INTO `pms_vote_signup` VALUES (7, '20221027130002', 12, 14, 'allen学生选举获得', 0, '2022-10-27 13:19:02', NULL, 0);

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `signup_mailbox` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `signup_identity` varchar(120) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `signup_identity_only`(`signup_identity`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_user
-- ----------------------------
INSERT INTO `v_user` VALUES (10, '我的名称', '123@QQ.COM', '440303030303033', '2022-10-27 13:17:49', NULL, 0);
INSERT INTO `v_user` VALUES (11, '我的2名称', '123@QQ.COM', '440303030303023', '2022-10-27 13:18:01', NULL, 0);
INSERT INTO `v_user` VALUES (12, '我的2名称', '123@QQ.COM', '340303030303023', '2022-10-27 13:18:05', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
