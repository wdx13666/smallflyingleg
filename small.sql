/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : localhost:3306
 Source Schema         : small

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : 65001

 Date: 24/05/2019 17:15:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sf_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sf_sys_permission`;
CREATE TABLE `sf_sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `res_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `res_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sf_sys_permission
-- ----------------------------
INSERT INTO `sf_sys_permission` VALUES (4, 0, '权限菜单', 'menu', 'menu', 'perm', '');
INSERT INTO `sf_sys_permission` VALUES (7, 2, '用户添加', 'button', 'systemUserAdd', 'xxx', '');
INSERT INTO `sf_sys_permission` VALUES (8, 4, '角色管理', 'menu', '11', 'table.html', '');
INSERT INTO `sf_sys_permission` VALUES (9, 4, '资源管理', 'menu', '1', 'permission.html', '');
INSERT INTO `sf_sys_permission` VALUES (18, 4, '用户管理', 'menu', 'user', 'user.html', '');

-- ----------------------------
-- Table structure for sf_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sf_sys_role`;
CREATE TABLE `sf_sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sf_sys_role
-- ----------------------------
INSERT INTO `sf_sys_role` VALUES (106, 'admin');
INSERT INTO `sf_sys_role` VALUES (107, '测试管理员');
INSERT INTO `sf_sys_role` VALUES (108, '用户管理员');

-- ----------------------------
-- Table structure for sf_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sf_sys_role_permission`;
CREATE TABLE `sf_sys_role_permission`  (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sf_sys_role_permission
-- ----------------------------
INSERT INTO `sf_sys_role_permission` VALUES (55, 4);
INSERT INTO `sf_sys_role_permission` VALUES (55, 7);
INSERT INTO `sf_sys_role_permission` VALUES (55, 8);
INSERT INTO `sf_sys_role_permission` VALUES (55, 9);
INSERT INTO `sf_sys_role_permission` VALUES (55, 18);
INSERT INTO `sf_sys_role_permission` VALUES (91, 4);
INSERT INTO `sf_sys_role_permission` VALUES (91, 8);
INSERT INTO `sf_sys_role_permission` VALUES (91, 9);
INSERT INTO `sf_sys_role_permission` VALUES (91, 18);
INSERT INTO `sf_sys_role_permission` VALUES (92, 4);
INSERT INTO `sf_sys_role_permission` VALUES (92, 8);
INSERT INTO `sf_sys_role_permission` VALUES (92, 9);
INSERT INTO `sf_sys_role_permission` VALUES (93, 4);
INSERT INTO `sf_sys_role_permission` VALUES (93, 9);
INSERT INTO `sf_sys_role_permission` VALUES (93, 18);
INSERT INTO `sf_sys_role_permission` VALUES (94, 4);
INSERT INTO `sf_sys_role_permission` VALUES (94, 8);
INSERT INTO `sf_sys_role_permission` VALUES (94, 9);
INSERT INTO `sf_sys_role_permission` VALUES (94, 18);
INSERT INTO `sf_sys_role_permission` VALUES (95, 4);
INSERT INTO `sf_sys_role_permission` VALUES (95, 8);
INSERT INTO `sf_sys_role_permission` VALUES (95, 9);
INSERT INTO `sf_sys_role_permission` VALUES (96, 7);
INSERT INTO `sf_sys_role_permission` VALUES (98, 4);
INSERT INTO `sf_sys_role_permission` VALUES (98, 7);
INSERT INTO `sf_sys_role_permission` VALUES (98, 8);
INSERT INTO `sf_sys_role_permission` VALUES (98, 9);
INSERT INTO `sf_sys_role_permission` VALUES (98, 18);
INSERT INTO `sf_sys_role_permission` VALUES (106, 4);
INSERT INTO `sf_sys_role_permission` VALUES (106, 8);
INSERT INTO `sf_sys_role_permission` VALUES (106, 9);
INSERT INTO `sf_sys_role_permission` VALUES (106, 18);
INSERT INTO `sf_sys_role_permission` VALUES (107, 4);
INSERT INTO `sf_sys_role_permission` VALUES (107, 18);
INSERT INTO `sf_sys_role_permission` VALUES (108, 7);

-- ----------------------------
-- Table structure for sf_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sf_sys_user`;
CREATE TABLE `sf_sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `full_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sf_sys_user
-- ----------------------------
INSERT INTO `sf_sys_user` VALUES (1, 'zhangsan', '张三', '86fb1b048301461bdc71d021d2af3f97', NULL);
INSERT INTO `sf_sys_user` VALUES (2, 'lisi', '李四', 'c9351e5cf153923f052ebe1462cca93a', '2');
INSERT INTO `sf_sys_user` VALUES (3, 'wangwu', '王五', '92262648696eae1b0a321cbd2b238bf2', '3');
INSERT INTO `sf_sys_user` VALUES (4, 'user1', '用户1', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (5, 'user2', '用户2', '86fb1b048301461bdc71d021d2af3f97', '5');
INSERT INTO `sf_sys_user` VALUES (6, 'user3', '用户3', '86fb1b048301461bdc71d021d2af3f97', '5');
INSERT INTO `sf_sys_user` VALUES (7, 'user4', '用户4', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (8, 'user5', '用户5', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (9, 'user6', '用户6', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (10, 'user7', '用户7', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (11, 'user8', '用户811', NULL, NULL);
INSERT INTO `sf_sys_user` VALUES (12, 'user9', '用户9', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (13, 'user10', '用户10', '86fb1b048301461bdc71d021d2af3f97', '4');
INSERT INTO `sf_sys_user` VALUES (14, 'wdx', 'wdx', '123456', NULL);
INSERT INTO `sf_sys_user` VALUES (20, 'wdx', '王大大', '123456', NULL);
INSERT INTO `sf_sys_user` VALUES (26, '3', '3', '3', NULL);
INSERT INTO `sf_sys_user` VALUES (27, '3', '3', '3', NULL);

-- ----------------------------
-- Table structure for sf_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sf_sys_user_role`;
CREATE TABLE `sf_sys_user_role`  (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sf_sys_user_role
-- ----------------------------
INSERT INTO `sf_sys_user_role` VALUES (1, 1);
INSERT INTO `sf_sys_user_role` VALUES (2, 2);
INSERT INTO `sf_sys_user_role` VALUES (25, 106);
INSERT INTO `sf_sys_user_role` VALUES (25, 107);
INSERT INTO `sf_sys_user_role` VALUES (25, 108);
INSERT INTO `sf_sys_user_role` VALUES (26, 106);
INSERT INTO `sf_sys_user_role` VALUES (27, 106);
INSERT INTO `sf_sys_user_role` VALUES (27, 108);

-- ----------------------------
-- Table structure for sf_workflow
-- ----------------------------
DROP TABLE IF EXISTS `sf_workflow`;
CREATE TABLE `sf_workflow`  (
  `workflow_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_id` int(11) NOT NULL COMMENT '站点',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排序',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
  PRIMARY KEY (`workflow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作流' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sf_workflow_event
-- ----------------------------
DROP TABLE IF EXISTS `sf_workflow_event`;
CREATE TABLE `sf_workflow_event`  (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `workflow_id` int(11) NOT NULL COMMENT '工作流',
  `date_id` int(11) NOT NULL COMMENT '数据标识',
  `user_id` int(11) NOT NULL COMMENT '发起人',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `next_step` int(11) NOT NULL DEFAULT 0 COMMENT '下个步骤',
  `date_type` int(11) NOT NULL DEFAULT 0 COMMENT '数据类型(0默认内容)',
  `is_finish` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否结束',
  `pass_num` int(11) NULL DEFAULT NULL COMMENT '会签本节点通过人数',
  PRIMARY KEY (`event_id`) USING BTREE,
  INDEX `workflow_id`(`workflow_id`) USING BTREE,
  CONSTRAINT `sf_workflow_event_ibfk_1` FOREIGN KEY (`workflow_id`) REFERENCES `sf_workflow` (`workflow_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1086 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作流轨迹' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sf_workflow_event_user
-- ----------------------------
DROP TABLE IF EXISTS `sf_workflow_event_user`;
CREATE TABLE `sf_workflow_event_user`  (
  `event_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL COMMENT '工作流转id',
  `user_id` int(11) NOT NULL COMMENT '流转用户',
  PRIMARY KEY (`event_user_id`) USING BTREE,
  INDEX `fk_jc_we_user`(`user_id`) USING BTREE,
  INDEX `fk_jc_we_event`(`event_id`) USING BTREE,
  CONSTRAINT `fk_jc_we_event` FOREIGN KEY (`event_id`) REFERENCES `sf_workflow_event` (`event_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1262 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作流轨迹用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sf_workflow_node
-- ----------------------------
DROP TABLE IF EXISTS `sf_workflow_node`;
CREATE TABLE `sf_workflow_node`  (
  `workflow_id` int(11) NOT NULL COMMENT '工作流',
  `role_id` int(11) NOT NULL COMMENT '角色',
  `priority` int(11) NOT NULL DEFAULT 10 COMMENT '排序',
  `is_countersign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '1 会签 0普通流转',
  PRIMARY KEY (`workflow_id`, `priority`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作流节点' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sf_workflow_record
-- ----------------------------
DROP TABLE IF EXISTS `sf_workflow_record`;
CREATE TABLE `sf_workflow_record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '操作人',
  `event_id` int(11) NOT NULL DEFAULT 0 COMMENT '轨迹',
  `record_time` datetime(0) NOT NULL COMMENT '创建时间',
  `opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '意见',
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '类型(1:通过  2退回 3保持)',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1756 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作流日志记录' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
