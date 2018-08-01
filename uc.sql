/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.15
Source Server Version : 50717
Source Host           : 192.168.1.15:3306
Source Database       : uc

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-01 09:56:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_application
-- ----------------------------
DROP TABLE IF EXISTS `t_application`;
CREATE TABLE `t_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '应用的编码',
  `name` varchar(255) DEFAULT NULL COMMENT '应用的名称',
  `remark` varchar(1024) DEFAULT NULL COMMENT '描述/备注信息',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除, 0:否,1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_application
-- ----------------------------
INSERT INTO `t_application` VALUES ('2', 'UC', 'uc浏览器', null, '2018-07-11 15:02:12', null, '1');
INSERT INTO `t_application` VALUES ('3', 'code', '应用', '备注', '2018-07-11 15:01:37', '2018-02-06 10:16:44', '1');
INSERT INTO `t_application` VALUES ('4', 'df9392c7063b4fef8e20c57162517fa4', '8e1361f5e7fe423abbc9e2b9a8dbce1c', null, '2018-07-11 18:27:42', null, '1');
INSERT INTO `t_application` VALUES ('5', '1', '阿里云普通平台aly阿里云普通平台阿里云pt', null, '2018-07-11 10:36:43', '2018-02-06 16:17:04', '1');
INSERT INTO `t_application` VALUES ('6', '7', '7', null, '2018-07-10 10:45:47', '2018-02-06 16:17:02', '1');
INSERT INTO `t_application` VALUES ('7', '8', '8', null, '2018-07-10 10:45:47', '2018-02-06 15:12:45', '1');
INSERT INTO `t_application` VALUES ('8', 'xunle', '迅雷', '刚刚', '2018-07-10 10:45:47', '2018-02-07 16:24:53', '1');
INSERT INTO `t_application` VALUES ('9', 'app', 'app', null, '2018-07-10 10:45:47', '2018-02-07 14:33:36', '1');
INSERT INTO `t_application` VALUES ('10', 'aa2', 'aa2', null, '2018-02-07 15:23:15', '2018-02-07 14:39:00', '1');
INSERT INTO `t_application` VALUES ('11', 'aa3', 'aa3', null, '2018-02-07 15:02:45', '2018-02-07 14:39:28', '1');
INSERT INTO `t_application` VALUES ('12', 'aaa4', 'aaa4', null, '2018-02-07 15:01:47', '2018-02-07 14:41:16', '1');
INSERT INTO `t_application` VALUES ('13', '444a', 'aaa4', null, '2018-02-07 15:23:15', '2018-02-07 15:02:54', '1');
INSERT INTO `t_application` VALUES ('14', 'bbb', 'aaa', null, '2018-07-10 10:45:47', '2018-02-07 15:43:31', '1');
INSERT INTO `t_application` VALUES ('15', 'bbaabb', 'aa', null, '2018-07-10 10:45:47', '2018-02-07 15:44:58', '1');
INSERT INTO `t_application` VALUES ('16', 'ee', 'one', null, '2018-07-10 10:45:47', '2018-02-07 15:45:17', '1');
INSERT INTO `t_application` VALUES ('17', 'test0012', 'test001', 'test001', '2018-07-11 15:02:42', '2018-07-10 11:03:02', '1');
INSERT INTO `t_application` VALUES ('18', 'code', 'code', '', '2018-07-11 15:02:42', '2018-07-11 15:01:50', '1');
INSERT INTO `t_application` VALUES ('19', 'uc', '用户中心', '用户中心', '2018-07-11 15:02:35', '2018-07-11 15:02:35', '0');
INSERT INTO `t_application` VALUES ('20', 'MAI', '脉链星球', '脉链星球', '2018-07-11 18:27:11', '2018-07-11 15:04:58', '1');
INSERT INTO `t_application` VALUES ('21', 'maichain', '脉链星球', '脉链星球', '2018-07-12 12:35:04', '2018-07-12 12:35:04', '0');

-- ----------------------------
-- Table structure for t_application_user
-- ----------------------------
DROP TABLE IF EXISTS `t_application_user`;
CREATE TABLE `t_application_user` (
  `user_id` int(255) DEFAULT NULL COMMENT '用户的ID',
  `application_id` int(255) DEFAULT NULL COMMENT '应用的ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_application_user
-- ----------------------------
INSERT INTO `t_application_user` VALUES ('1', '3');
INSERT INTO `t_application_user` VALUES ('1', '2');
INSERT INTO `t_application_user` VALUES ('2', '1');
INSERT INTO `t_application_user` VALUES ('2', '2');
INSERT INTO `t_application_user` VALUES ('31', '2');
INSERT INTO `t_application_user` VALUES ('31', '5');
INSERT INTO `t_application_user` VALUES ('3', '2');
INSERT INTO `t_application_user` VALUES ('3', '3');
INSERT INTO `t_application_user` VALUES ('3', '4');
INSERT INTO `t_application_user` VALUES ('3', '5');
INSERT INTO `t_application_user` VALUES ('14', '3');
INSERT INTO `t_application_user` VALUES ('14', '5');
INSERT INTO `t_application_user` VALUES ('134', '21');

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint(1) DEFAULT '10' COMMENT '类型, 10:公司, 20:部门',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `logo` varchar(128) DEFAULT NULL COMMENT 'Logo标志',
  `nation` varchar(128) DEFAULT '中国' COMMENT '国家/地区',
  `province` varchar(128) DEFAULT NULL COMMENT '省/直辖市',
  `city` varchar(128) DEFAULT NULL COMMENT '市/州',
  `county` varchar(128) DEFAULT NULL COMMENT '区/县',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `unit_type` varchar(64) DEFAULT NULL COMMENT '单位类型',
  `industry_type` varchar(64) DEFAULT NULL COMMENT '行业类型',
  `member_size` varchar(32) DEFAULT NULL COMMENT '人员规模',
  `total_output_value` varchar(16) DEFAULT NULL COMMENT '生产总值(万元)',
  `linkman` varchar(32) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `mobile_phone` varchar(32) DEFAULT NULL COMMENT '移动电话',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `website` varchar(64) DEFAULT NULL COMMENT '官方网站',
  `description` text COMMENT '企业描述',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用状态, 0:禁用,1:可用',
  `auth_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '认证状态, 0:未认证,1:已认证',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除, 0:否,1:是',
  `sort` bigint(11) NOT NULL DEFAULT '0' COMMENT '排序值(越大越靠前)',
  `remark` varchar(1024) DEFAULT NULL COMMENT '描述/备注信息',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='组织基本信息';

-- ----------------------------
-- Records of t_organization
-- ----------------------------
INSERT INTO `t_organization` VALUES ('12', '10', '棒棒棒222', null, null, '浙江', '江西省', '杭州', '西湖区', '3333', '棒棒棒', '巴巴爸爸', '112312', '1222', '3333', '111', 'bb1111', '111', null, '4444', '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-24 18:27:02');
INSERT INTO `t_organization` VALUES ('13', '10', '棒棒棒', null, null, '浙江', '浙江', '杭州', '西湖区', '', null, null, null, null, '', '', null, '', null, null, '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-24 18:33:33');
INSERT INTO `t_organization` VALUES ('14', '10', '本宝宝1', '13', null, '中国', '浙江省', '杭州', '西湖区', '', null, null, null, null, '', '', null, '', null, null, '1', '0', '1', '0', null, '2018-01-25 11:49:08', '2018-01-24 18:33:58');
INSERT INTO `t_organization` VALUES ('15', '10', '小道科技', '15', null, null, '福建省', '杭州', '西湖区', '浙江省 杭州市 西湖区 雷峰塔 3楼', null, null, null, null, '天道', '0755-88886666', null, '8888@qq.com', null, null, '1', '0', '1', '0', null, '2018-01-26 10:59:07', '2018-01-25 09:28:08');
INSERT INTO `t_organization` VALUES ('16', '10', '哇哈哈', null, null, null, '浙江省', '杭州', '西湖区', '西湖 雷峰塔2楼', null, null, null, null, '哇哈哈', '0731 - 44445555', null, '44445555@qq.com', null, null, '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 09:32:38');
INSERT INTO `t_organization` VALUES ('17', '10', '呜呜呜1222', '12', null, null, '浙江省', '杭州', '西湖区', '雷峰塔2楼', null, null, null, null, '呜呜呜2', '0766 -  8888 9998', null, ' 8888 9998@qq.com', null, null, '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 09:33:50');
INSERT INTO `t_organization` VALUES ('18', '10', '小小道科技', '15', null, null, '福建省', '杭州', '西湖区', '雷峰塔2楼', null, null, null, null, '小小道', '0755- 11113333', null, '111@qq.com', null, null, '1', '0', '1', '0', null, '2018-01-26 10:59:07', '2018-01-25 09:46:09');
INSERT INTO `t_organization` VALUES ('19', '10', '天道科技有限公司', '18', null, null, '浙江省', '杭州', '西湖区', '天道科技有限公司天道科技有限公司天道科技有限公司', null, null, null, null, '天道科技有限公司', '333', null, '444', null, null, '1', '0', '1', '0', null, '2018-01-26 10:59:07', '2018-01-25 10:20:26');
INSERT INTO `t_organization` VALUES ('20', '10', '小道科技', null, null, null, '浙江省', '杭州', '西湖区', '雷峰塔3楼', '创业公司', '互联网', '1000', '11111', '天道', '0755- 888 99999', '171 8888 9999', '8888@qq.com', null, null, '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 14:28:09');
INSERT INTO `t_organization` VALUES ('21', '20', '天道科技', null, null, null, '浙江省', '杭州', '西湖区', '', '创业公司1', '互联网2', '1000', '', '天道', '', '', '', null, null, '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 14:29:34');
INSERT INTO `t_organization` VALUES ('22', '10', 'xiao', null, null, null, '江西省', '温州', '余杭区', '发发发', '单位', '类型', '100', '100', '一万', '11', '131 4444 5555 ', '22', null, '11111111', '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 16:54:20');
INSERT INTO `t_organization` VALUES ('23', '20', '嘻嘻嘻', null, null, null, '浙江省', '杭州', '西湖区', '嘻嘻嘻嘻嘻嘻', '嘻嘻嘻', '嘻嘻嘻', '11', '111', '嘻嘻嘻', '', '', '', null, '嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻', '1', '0', '1', '0', null, '2018-01-26 10:58:59', '2018-01-25 16:57:25');
INSERT INTO `t_organization` VALUES ('24', '10', '1', null, null, null, '浙江省', '杭州', '西湖区', '9', '3', '4', '5', '', '2', '7', '6', '8', null, '10', '1', '0', '1', '0', null, '2018-01-26 10:54:37', '2018-01-25 17:44:34');
INSERT INTO `t_organization` VALUES ('25', '10', '2', null, null, null, '浙江省', '杭州', '西湖区', '', '4', '3', '5', '', '3', '4', '4', '4', null, '4', '1', '0', '1', '0', null, '2018-01-26 10:53:36', '2018-01-25 17:44:45');
INSERT INTO `t_organization` VALUES ('26', '10', '3', null, null, null, '浙江省', '杭州', '西湖区', '', '3', '3', '3', '3', '3', '3', '3', '3', null, '', '1', '0', '1', '0', null, '2018-01-26 10:54:26', '2018-01-25 17:44:52');
INSERT INTO `t_organization` VALUES ('27', '10', '4', null, null, null, '浙江省', '杭州', '西湖区', '4', '4', '4', '4', '4', '4', '4', '4', '4', null, '4', '1', '0', '1', '0', null, '2018-01-26 10:53:28', '2018-01-25 17:44:59');
INSERT INTO `t_organization` VALUES ('28', '10', '5', null, null, null, '浙江省', '杭州', '西湖区', '5', '5', '5', '5', '5', '5', '5', '5', '5', null, '5', '1', '0', '1', '0', null, '2018-01-25 17:47:58', '2018-01-25 17:45:07');
INSERT INTO `t_organization` VALUES ('29', '10', '小道科技', null, null, '中国', '广东省', '深圳市', '南山区', '深圳市 南山区 东方科技大厦', '创业公司1', '互联网', '10', '200', '小道', '0755 - 8888 9999', '131 8888 9999 ', '88889999@qq.com', null, '小道科技！！！', '1', '0', '0', '0', null, '2018-01-31 15:09:48', '2018-01-26 11:01:32');
INSERT INTO `t_organization` VALUES ('30', '20', '研发部', '29', null, '中国', '广东省', '深圳市', '南山区', '8', '1', '2', '3', '4', '', '6', '5', '7', null, '9', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:02:24');
INSERT INTO `t_organization` VALUES ('31', '20', 'UI部', '29', null, null, '浙江省', '宁波市', '北仑区', '', '', '', null, '', '', '', '', '', null, '小道科技下属UI部门', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:02:47');
INSERT INTO `t_organization` VALUES ('32', '10', '测试3', '29', null, '中国', '广东省', '深圳市', '南山区', '', '', '', null, '', '', '', '', '', null, '\n\n\n\n\n\n', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:03:50');
INSERT INTO `t_organization` VALUES ('33', '10', '和巨信息', null, null, null, '浙江省', '湖州市', '长兴县', '深圳市南山区 东方科技大厦 ', '创业公司', '互联网', '10', '10', '和巨信息', '0755-55554444', '13155554444', '55554444@qq.com', null, '和巨信息', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:23');
INSERT INTO `t_organization` VALUES ('34', '10', '测试1', null, null, '中国', '浙江省', '绍兴市', '柯桥区', '', '', '', null, '', '', '', '', '', null, '\n', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:30');
INSERT INTO `t_organization` VALUES ('35', '10', '测试2', null, null, null, '浙江省', '温州市', '龙湾区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:35');
INSERT INTO `t_organization` VALUES ('36', '20', '测试3', null, null, null, '浙江省', '温州市', '鹿城区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:40');
INSERT INTO `t_organization` VALUES ('37', '10', '测试4', null, null, null, '浙江省', '宁波市', '江东区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:44');
INSERT INTO `t_organization` VALUES ('38', '10', 'UI部', '29', null, null, '浙江省', '杭州', '西湖区', '', '', '', null, '', '', '', '', '', null, '小道科技下属UI部门\n', '0', '0', '1', '0', null, '2018-01-29 17:17:45', '2018-01-26 11:05:47');
INSERT INTO `t_organization` VALUES ('39', '10', '测试6', null, null, '中国', '重庆市', '重庆市', '江北区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:51');
INSERT INTO `t_organization` VALUES ('40', '10', '测试7', null, null, '中国', '浙江省', '温州市', '瑞安市', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:55');
INSERT INTO `t_organization` VALUES ('41', '10', '测试8', null, null, null, '浙江省', '宁波市', '江北区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:05:59');
INSERT INTO `t_organization` VALUES ('42', '10', '测试9', null, null, null, '浙江省', '杭州', '西湖区', '', '', '', null, '', '', '', '', '', null, '', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 11:06:05');
INSERT INTO `t_organization` VALUES ('43', '10', '测试10', null, null, null, '浙江省', '杭州', '西湖区', '', '', '', null, '', '', '', '', '', null, '测试10测试10测试10', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 16:29:46');
INSERT INTO `t_organization` VALUES ('44', '10', '测试11', null, null, null, '浙江省', '杭州', '西湖区', '', '', '', null, '', '', '', '', '', null, '测试10测试10测试10', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 16:29:51');
INSERT INTO `t_organization` VALUES ('45', '10', '测试12', null, null, null, '浙江省', '杭州', '西湖区', '', '', '', null, '', '', '', '', '', null, '测试10测试10测试10', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 16:29:57');
INSERT INTO `t_organization` VALUES ('46', '10', '测试13', null, null, null, '浙江省', '宁波市', '江北区', '', '', '', null, '', '', '', '', '', null, '测试10测试10测试10', '0', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-26 16:30:02');
INSERT INTO `t_organization` VALUES ('47', '10', '销售部', '29', null, '中国', '浙江省', '', '', '', '', '', null, '', '', '', '', '', null, '销售部', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-27 09:43:48');
INSERT INTO `t_organization` VALUES ('48', '10', '前端工作组', '30', null, '中国', '浙江省', '衢州市', '柯城区', '', '', '', null, '', '', '', '', '', null, '前端工作组', '1', '0', '1', '0', null, '2018-07-11 18:08:03', '2018-01-27 09:44:04');

-- ----------------------------
-- Table structure for t_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_user`;
CREATE TABLE `t_organization_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `organization_id` bigint(20) NOT NULL COMMENT '组织ID',
  `is_manager` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为组织管理员, 0:否,1:是',
  PRIMARY KEY (`organization_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织用户信息';

-- ----------------------------
-- Records of t_organization_user
-- ----------------------------
INSERT INTO `t_organization_user` VALUES ('1', '29', '0');
INSERT INTO `t_organization_user` VALUES ('3', '29', '0');
INSERT INTO `t_organization_user` VALUES ('14', '29', '0');
INSERT INTO `t_organization_user` VALUES ('15', '29', '0');
INSERT INTO `t_organization_user` VALUES ('1', '30', '0');
INSERT INTO `t_organization_user` VALUES ('2', '30', '0');
INSERT INTO `t_organization_user` VALUES ('3', '30', '0');
INSERT INTO `t_organization_user` VALUES ('1', '32', '0');
INSERT INTO `t_organization_user` VALUES ('2', '32', '0');
INSERT INTO `t_organization_user` VALUES ('3', '32', '0');
INSERT INTO `t_organization_user` VALUES ('14', '32', '0');
INSERT INTO `t_organization_user` VALUES ('15', '32', '0');
INSERT INTO `t_organization_user` VALUES ('16', '32', '0');
INSERT INTO `t_organization_user` VALUES ('17', '32', '0');
INSERT INTO `t_organization_user` VALUES ('18', '32', '0');
INSERT INTO `t_organization_user` VALUES ('19', '32', '0');
INSERT INTO `t_organization_user` VALUES ('20', '32', '0');
INSERT INTO `t_organization_user` VALUES ('21', '32', '0');
INSERT INTO `t_organization_user` VALUES ('22', '32', '0');
INSERT INTO `t_organization_user` VALUES ('23', '32', '0');
INSERT INTO `t_organization_user` VALUES ('24', '32', '0');
INSERT INTO `t_organization_user` VALUES ('2', '33', '0');
INSERT INTO `t_organization_user` VALUES ('3', '33', '0');
INSERT INTO `t_organization_user` VALUES ('14', '33', '0');
INSERT INTO `t_organization_user` VALUES ('15', '33', '0');
INSERT INTO `t_organization_user` VALUES ('16', '33', '0');
INSERT INTO `t_organization_user` VALUES ('17', '33', '0');
INSERT INTO `t_organization_user` VALUES ('18', '33', '0');
INSERT INTO `t_organization_user` VALUES ('19', '33', '0');
INSERT INTO `t_organization_user` VALUES ('20', '33', '0');
INSERT INTO `t_organization_user` VALUES ('21', '33', '0');
INSERT INTO `t_organization_user` VALUES ('22', '33', '0');
INSERT INTO `t_organization_user` VALUES ('23', '33', '0');
INSERT INTO `t_organization_user` VALUES ('24', '33', '0');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `permission_key` varchar(128) NOT NULL COMMENT '权限Key',
  `app_id` varchar(64) NOT NULL COMMENT '所属应用ID',
  `type` varchar(32) NOT NULL DEFAULT 'PAGE' COMMENT '类型, 页面:PAGE,按钮:BTN,元素:ELE,接口:IMPL',
  `method` varchar(32) NOT NULL DEFAULT 'GET' COMMENT '请求方式：GET,POST,PUT,DELETE,OPTIONS,PATCH,HEAD',
  `url` varchar(256) DEFAULT '#' COMMENT '页面或接口地址',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `sort` bigint(11) NOT NULL DEFAULT '0' COMMENT '排序值(越大越靠前)',
  `status` tinyint(1) DEFAULT '1' COMMENT '权限是否可用, 0:禁用,1:可用',
  `remark` varchar(1024) DEFAULT NULL COMMENT '描述/备注信息',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除, 0:否,1:是',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`permission_key`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COMMENT='权限信息';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('13', '用户中心', 'uc', 'uc', 'MENU', 'GET', '/', null, '0', '1', '用户中心', '0', '2018-01-31 11:08:07', '2018-01-29 17:51:23');
INSERT INTO `t_permission` VALUES ('14', '用户管理', 'uc_usermanagement', 'uc', 'PAGE', 'GET', '/dist/html/userManagement.html', '13', '0', '1', '用户管理', '0', '2018-01-30 17:36:50', '2018-01-29 17:52:58');
INSERT INTO `t_permission` VALUES ('15', '角色管理', 'uc_rolemanagement', 'uc', 'PAGE', 'GET', '/dist/html/roleManagement.html', '13', '0', '1', '角色管理', '0', '2018-01-30 17:36:51', '2018-01-29 18:32:26');
INSERT INTO `t_permission` VALUES ('16', '组织管理', 'uc_orgmanagement', 'uc', 'PAGE', 'GET', '/dist/html/orgManagement.html', '13', '0', '1', '组织管理', '0', '2018-01-30 17:36:48', '2018-01-30 09:22:42');
INSERT INTO `t_permission` VALUES ('17', '权限资源管理', 'uc_rrmanagement', 'uc', 'PAGE', 'GET', '/dist/html/RRManagement.html', '13', '0', '1', '权限资源管理', '0', '2018-01-30 17:36:49', '2018-01-30 09:48:54');
INSERT INTO `t_permission` VALUES ('18', '用户编辑按钮', 'uc_user_edit', 'uc', 'BTN', 'POST', '/api/auth/v1/user/profile', '14', '0', '1', '用户编辑按钮', '0', '2018-01-30 14:29:22', '2018-01-30 10:05:55');
INSERT INTO `t_permission` VALUES ('19', '用户详情按钮', 'uc_user_detail', 'uc', 'BTN', 'POST', '/api/auth/v1/user/{id}', '14', '0', '1', '用户详情按钮', '0', '2018-01-30 14:45:09', '2018-01-30 14:45:09');
INSERT INTO `t_permission` VALUES ('20', '用户状态修改', 'uc_user_status', 'uc', 'BTN', 'POST', '/api/auth/v1/user/status', '14', '0', '1', '用户状态修改', '0', '2018-01-30 16:31:50', '2018-01-30 14:45:59');
INSERT INTO `t_permission` VALUES ('21', '角色添加按钮', 'uc_role_add', 'uc', 'BTN', 'POST', '/api/auth/v1/role/save', '15', '0', '1', '角色添加按钮\n', '0', '2018-01-30 16:19:37', '2018-01-30 16:19:37');
INSERT INTO `t_permission` VALUES ('22', '角色删除按钮', 'uc_role_delete', 'uc', 'BTN', 'POST', '/api/auth/v1/role/delete', '15', '0', '1', '角色删除按钮\n', '0', '2018-01-30 17:36:26', '2018-01-30 16:20:51');
INSERT INTO `t_permission` VALUES ('23', '角色编辑按钮', 'uc_role_edit', 'uc', 'BTN', 'POST', '/api/auth/v1/role/update', '15', '0', '1', '角色编辑按钮\n', '0', '2018-01-30 16:21:24', '2018-01-30 16:21:24');
INSERT INTO `t_permission` VALUES ('24', '角色详情按钮', 'uc_role_detail', 'uc', 'BTN', 'POST', '/api/auth/v1/role/detail/{id}', '15', '0', '1', '角色详情按钮', '0', '2018-01-30 16:21:56', '2018-01-30 16:21:56');
INSERT INTO `t_permission` VALUES ('25', '角色状态修改按钮', 'uc_role_status', 'uc', 'BTN', 'POST', '/api/auth/v1/role/status', '15', '0', '1', '角色状态修改按钮\n', '0', '2018-01-30 16:23:11', '2018-01-30 16:23:11');
INSERT INTO `t_permission` VALUES ('26', '角色分配权限按钮', 'uc_role_permission', 'uc', 'BTN', 'POST', '/api/auth/v1/role/permission/save', '15', '0', '1', '角色分配权限按钮\n', '0', '2018-01-30 16:23:38', '2018-01-30 16:23:38');
INSERT INTO `t_permission` VALUES ('27', '角色分配用户按钮', 'uc_role_user', 'uc', 'BTN', 'POST', '/api/auth/v1/role/user/save', '15', '0', '1', '角色分配用户按钮\n', '0', '2018-01-30 16:24:11', '2018-01-30 16:24:11');
INSERT INTO `t_permission` VALUES ('28', '组织添加按钮', 'uc_org_add', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/save', '16', '0', '1', '组织添加按钮\n', '0', '2018-01-30 16:25:00', '2018-01-30 16:25:00');
INSERT INTO `t_permission` VALUES ('29', '组织删除按钮', 'uc_org_delete', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/delete', '16', '0', '1', '组织删除按钮\n', '0', '2018-01-30 16:25:44', '2018-01-30 16:25:44');
INSERT INTO `t_permission` VALUES ('30', '组织编辑按钮', 'uc_org_edit', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/update', '16', '0', '1', '组织编辑按钮\n', '0', '2018-01-30 16:26:18', '2018-01-30 16:26:18');
INSERT INTO `t_permission` VALUES ('31', '组织详情按钮', 'uc_org_detail', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/detail/{id}', '16', '0', '1', '组织详情按钮\n', '0', '2018-01-30 16:26:44', '2018-01-30 16:26:44');
INSERT INTO `t_permission` VALUES ('32', '组织状态修改按钮', 'uc_org_status', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/status', '16', '0', '1', '组织状态修改按钮\n', '0', '2018-01-30 16:27:17', '2018-01-30 16:27:17');
INSERT INTO `t_permission` VALUES ('33', '组织分配用户按钮', 'uc_org_user', 'uc', 'BTN', 'POST', '/api/auth/v1/organization/user/save', '16', '0', '1', '组织分配用户按钮\n', '0', '2018-01-30 16:27:46', '2018-01-30 16:27:46');
INSERT INTO `t_permission` VALUES ('34', '权限资源添加按钮', 'uc_permission_add', 'uc', 'BTN', 'POST', '/api/auth/v1/permission/save', '17', '0', '1', '权限资源添加按钮\n', '0', '2018-01-30 16:28:15', '2018-01-30 16:28:15');
INSERT INTO `t_permission` VALUES ('35', '权限资源删除按钮', 'uc_permission_delete', 'uc', 'BTN', 'POST', '/api/auth/v1/permission/delete', '17', '0', '1', '权限资源删除按钮\n', '0', '2018-01-30 16:28:39', '2018-01-30 16:28:39');
INSERT INTO `t_permission` VALUES ('36', '权限资源编辑按钮', 'uc_permission_edit', 'uc', 'BTN', 'POST', '/api/auth/v1/permission/update', '17', '0', '1', '权限资源编辑按钮\n', '0', '2018-01-30 16:29:11', '2018-01-30 16:29:11');
INSERT INTO `t_permission` VALUES ('37', '权限资源详情按钮', 'uc_permission_detail', 'uc', 'BTN', 'POST', '/api/auth/v1/permission/detail/{id}', '17', '0', '1', '权限资源详情按钮\n', '0', '2018-01-30 16:29:37', '2018-01-30 16:29:37');
INSERT INTO `t_permission` VALUES ('38', '权限资源状态修改按钮', 'uc_permission_status', 'uc', 'BTN', 'POST', '/api/auth/v1/permission/status', '17', '0', '1', '权限资源状态修改按钮\n', '0', '2018-01-30 16:30:00', '2018-01-30 16:30:00');
INSERT INTO `t_permission` VALUES ('39', '用户删除按钮', 'uc_user_delete', 'uc', 'BTN', 'POST', '/api/auth/v2/user/delete', '14', '0', '1', '用户删除按钮\n', '0', '2018-01-30 17:41:44', '2018-01-30 17:41:44');
INSERT INTO `t_permission` VALUES ('40', '用户重置密码按钮', 'uc_user_resetpwd', 'uc', 'BTN', 'POST', '/api/auth/v3/user/resetpwd', '14', '0', '1', '用户重置密码按钮\n', '0', '2018-01-30 17:42:16', '2018-01-30 17:42:16');
INSERT INTO `t_permission` VALUES ('42', '应用管理', 'app', 'uc', 'PAGE', 'GET', '/dist/html/appManagement.html', '13', '0', '1', '应用管理', '0', '2018-02-07 10:59:22', '2018-02-07 10:59:22');
INSERT INTO `t_permission` VALUES ('43', '用户分配应用按钮', 'uc_user_userapp', 'uc', 'BTN', 'POST', '/api/auth/v3/user/userApp', '14', '0', '1', '用户分配应用按钮', '0', '2018-02-07 15:57:07', '2018-02-07 15:57:07');
INSERT INTO `t_permission` VALUES ('44', '应用添加按钮', 'uc_app_add', 'uc', 'BTN', 'POST', 'auth/v1/application/save', '42', '0', '1', '应用添加按钮', '0', '2018-02-07 16:00:31', '2018-02-07 16:00:31');
INSERT INTO `t_permission` VALUES ('45', '应用删除按钮', 'uc_app_delete', 'uc', 'BTN', 'POST', 'auth/v1/application/delete', '42', '0', '1', '应用删除按钮', '0', '2018-02-07 16:03:48', '2018-02-07 16:01:14');
INSERT INTO `t_permission` VALUES ('46', '应用查看按钮', 'uc_app_detail', 'uc', 'BTN', 'GET', 'auth', '42', '0', '1', '应用查看按钮', '0', '2018-02-07 16:05:12', '2018-02-07 16:05:12');
INSERT INTO `t_permission` VALUES ('47', '应用编辑按钮', 'uc_app_edit', 'uc', 'BTN', 'POST', 'auth/v1/application/update', '42', '0', '1', '应用编辑按钮', '0', '2018-02-07 16:06:14', '2018-02-07 16:05:55');
INSERT INTO `t_permission` VALUES ('56', '应用列表', 'app_list_', 'uc', 'ELE', 'GET', '', '42', '0', '1', '', '0', '2018-07-11 18:14:56', '2018-07-11 18:14:56');
INSERT INTO `t_permission` VALUES ('65', '脉链星球', 'maichain', 'maichain', 'MENU', 'GET', '/', null, '0', '1', '脉链星球父节点', '0', '2018-07-14 15:58:19', '2018-07-14 15:11:34');
INSERT INTO `t_permission` VALUES ('66', '财务管理', 'maichain_finance', 'maichain', 'MENU', 'GET', '/finance', '65', '0', '1', '财务管理子节点', '0', '2018-07-14 15:32:22', '2018-07-14 15:13:21');
INSERT INTO `t_permission` VALUES ('67', '用户列表', 'maichain_finance_userlist', 'maichain', 'PAGE', 'GET', '/html/user-list.html', '66', '0', '1', '脉链星球用户列表', '0', '2018-07-16 09:56:25', '2018-07-14 15:15:12');
INSERT INTO `t_permission` VALUES ('68', '赞赏记录', 'maichain_finance_appreciatelist', 'maichain', 'PAGE', 'GET', '/html/appreciate-list.html', '66', '0', '1', '赞赏记录', '0', '2018-07-16 09:56:37', '2018-07-14 15:31:49');
INSERT INTO `t_permission` VALUES ('69', '解锁记录', 'maichain_finance_unlocklist', 'maichain', 'PAGE', 'GET', '/html/unlock-list.html', '66', '0', '1', '解锁记录', '0', '2018-07-16 09:57:48', '2018-07-14 15:33:37');
INSERT INTO `t_permission` VALUES ('70', '代理候选人', 'maichain_finance_candidatelist', 'maichain', 'PAGE', 'GET', '/html/candidate-list.html', '66', '0', '1', '代理候选人', '0', '2018-07-16 09:57:58', '2018-07-14 15:36:17');
INSERT INTO `t_permission` VALUES ('71', '代理商审核', 'maichain_finance_agentauditlist', 'maichain', 'PAGE', 'GET', '/html/agent-audit-list.html', '66', '0', '1', '代理商审核', '0', '2018-07-16 09:58:07', '2018-07-14 15:37:11');
INSERT INTO `t_permission` VALUES ('72', '代理商列表', 'maichain_finance_agentlist', 'maichain', 'PAGE', 'GET', '/html/agent-list.html', '66', '0', '1', '代理商列表', '0', '2018-07-16 09:58:18', '2018-07-14 15:37:55');
INSERT INTO `t_permission` VALUES ('73', '代理奖励钻', 'maichain_finance_awardmai', 'maichain', 'PAGE', 'GET', '/html/award-mai-list.html', '66', '0', '1', '代理奖励(钻)', '0', '2018-07-16 09:59:35', '2018-07-14 15:40:10');
INSERT INTO `t_permission` VALUES ('74', '平台操作', 'maichain_finance_operation', 'maichain', 'PAGE', 'GET', '/html/operation.html', '66', '0', '1', '平台操作', '0', '2018-07-16 09:58:43', '2018-07-14 15:42:07');
INSERT INTO `t_permission` VALUES ('75', '解锁', 'maichain_finance_unlock', 'maichain', 'PAGE', 'GET', '/html/unlock.html', '66', '0', '1', '解锁', '0', '2018-07-16 09:58:54', '2018-07-14 15:51:24');
INSERT INTO `t_permission` VALUES ('76', '脉圈管理', 'maichain_circle', 'maichain', 'MENU', 'GET', '/circle', '65', '0', '1', '脉圈管理子节点', '0', '2018-07-14 15:56:48', '2018-07-14 15:56:48');
INSERT INTO `t_permission` VALUES ('77', '文章管理', 'maichain_circle_articlelist', 'maichain', 'PAGE', 'GET', '/html/article-list.html', '76', '0', '1', '脉圈文章管理', '0', '2018-07-16 09:56:48', '2018-07-14 15:58:12');
INSERT INTO `t_permission` VALUES ('78', '文章列表', 'maichain_circle_circlelist', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/getCircleList', null, '0', '1', null, '0', '2018-07-17 10:26:00', '2018-07-17 09:41:07');
INSERT INTO `t_permission` VALUES ('79', '文章详情', 'maichain_circle_webcircle_details', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/getWebCircleDetails', null, '0', '1', null, '0', '2018-07-17 10:25:58', '2018-07-17 09:46:44');
INSERT INTO `t_permission` VALUES ('80', '文章详情--评论', 'maichain_circle_webcircle_comment', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/getWebCircleComment', null, '0', '1', null, '0', '2018-07-17 10:25:57', '2018-07-17 09:48:46');
INSERT INTO `t_permission` VALUES ('81', '文章操作', 'maichain_circle_update_webcircle', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/updateWebCircle', null, '0', '1', null, '0', '2018-07-17 10:25:56', '2018-07-17 09:52:04');
INSERT INTO `t_permission` VALUES ('82', '审核开关列表', 'maichain_circle_system_setup', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/getSystemSetup', null, '0', '1', null, '0', '2018-07-17 10:25:55', '2018-07-17 09:53:03');
INSERT INTO `t_permission` VALUES ('83', '修改审核开关', 'maichain_circle_update_system_setup', 'maichain', 'IMPL', 'POST', '/api/web/v2/circle/updateSystemSetup', null, '0', '1', null, '0', '2018-07-17 10:25:53', '2018-07-17 09:53:49');
INSERT INTO `t_permission` VALUES ('84', '系统转账', 'maichain_eos_sys_to_user_transaction', 'maichain', 'IMPL', 'POST', '/api/web/v2/eos/sysToUserTransaction', null, '0', '1', null, '0', '2018-07-17 10:25:52', '2018-07-17 09:59:27');
INSERT INTO `t_permission` VALUES ('85', '解锁eos', 'maichain_eos_unlock', 'maichain', 'IMPL', 'POST', '/api/web/v2/eos/unlock_eos', null, '0', '1', null, '0', '2018-07-17 10:25:50', '2018-07-17 10:00:34');
INSERT INTO `t_permission` VALUES ('86', 'eos解锁记录', 'maichain_eos_get_unlocks', 'maichain', 'IMPL', 'POST', '/api/web/v2/eos/get/unlockEos', null, '0', '1', null, '0', '2018-07-17 10:25:49', '2018-07-17 10:01:31');
INSERT INTO `t_permission` VALUES ('87', '查看代理候选人', 'maichain_exchange_get_candidates', 'maichain', 'IMPL', 'POST', '/api/web/v2/exchange/get/candidates', null, '0', '1', null, '0', '2018-07-17 10:25:48', '2018-07-17 10:03:48');
INSERT INTO `t_permission` VALUES ('88', '代理审核', 'maichain_exchange_get_agents', 'maichain', 'IMPL', 'POST', '/api/web/v2/exchange/get/agents', null, '0', '1', null, '0', '2018-07-17 10:25:47', '2018-07-17 10:04:31');
INSERT INTO `t_permission` VALUES ('89', '兑换记录', 'maichain_exchange_forward_record ', 'maichain', 'IMPL', 'POST', '/api/web/v2/exchange/forward/record', null, '0', '1', null, '0', '2018-07-18 11:37:55', '2018-07-17 10:13:57');
INSERT INTO `t_permission` VALUES ('90', '代理商提现', 'maichain_exchange_put_forward', 'maichain', 'IMPL', 'POST', '/api/web/v2/exchange/put/forward', null, '0', '1', null, '0', '2018-07-17 10:25:45', '2018-07-17 10:14:46');
INSERT INTO `t_permission` VALUES ('91', '获取订单', 'maichain_order_getlist', 'maichain', 'IMPL', 'POST', '/api/web/v2/order/get/orders', null, '0', '1', null, '0', '2018-07-17 10:25:43', '2018-07-17 10:16:03');
INSERT INTO `t_permission` VALUES ('92', '获取用列表', 'maichain_user_getlist', 'maichain', 'IMPL', 'POST', '/api/web/v2/user/get/users', null, '0', '1', '', '0', '2018-07-17 10:25:26', '2018-07-17 10:17:33');
INSERT INTO `t_permission` VALUES ('93', '获取代理商信息', 'maichain_user_agent', 'maichain', 'IMPL', 'POST', '/api/web/v2/user/get/usersByAgent', null, '0', '1', '', '0', '2018-07-17 10:25:11', '2018-07-17 10:18:48');
INSERT INTO `t_permission` VALUES ('94', '审核代理商', 'maichain_user_toexamine', 'maichain', 'IMPL', 'POST', '/api/web/v2/user/toExamine', null, '0', '1', '', '0', '2018-07-17 10:24:52', '2018-07-17 10:19:50');
INSERT INTO `t_permission` VALUES ('95', '文章审核开关', 'maichain_circle_audit', 'maichain', 'PAGE', 'GET', '/html/article-audit.html', '76', '0', '1', '文章审核开关', '0', '2018-07-18 10:23:26', '2018-07-18 10:18:12');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色是否可用, 0:禁用,1:可用',
  `remark` varchar(1024) DEFAULT NULL COMMENT '描述/备注信息',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除, 0:否,1:是',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('14', 'manage', '1', '管理员A', '0', '2018-07-16 18:15:02', '2018-01-29 18:22:44');
INSERT INTO `t_role` VALUES ('15', 'user', '1', '只有查看权限', '0', '2018-07-16 18:15:01', '2018-01-30 21:44:40');
INSERT INTO `t_role` VALUES ('16', 'admin', '1', '所有权限', '0', '2018-07-14 12:14:08', '2018-01-31 10:19:17');
INSERT INTO `t_role` VALUES ('19', 'maiAdmin', '1', '脉链星球', '0', '2018-07-17 11:27:38', '2018-07-17 11:27:38');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限信息';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('14', '13');
INSERT INTO `t_role_permission` VALUES ('15', '13');
INSERT INTO `t_role_permission` VALUES ('16', '13');
INSERT INTO `t_role_permission` VALUES ('14', '14');
INSERT INTO `t_role_permission` VALUES ('16', '14');
INSERT INTO `t_role_permission` VALUES ('14', '15');
INSERT INTO `t_role_permission` VALUES ('16', '15');
INSERT INTO `t_role_permission` VALUES ('14', '16');
INSERT INTO `t_role_permission` VALUES ('16', '16');
INSERT INTO `t_role_permission` VALUES ('14', '17');
INSERT INTO `t_role_permission` VALUES ('16', '17');
INSERT INTO `t_role_permission` VALUES ('16', '18');
INSERT INTO `t_role_permission` VALUES ('14', '19');
INSERT INTO `t_role_permission` VALUES ('16', '19');
INSERT INTO `t_role_permission` VALUES ('16', '20');
INSERT INTO `t_role_permission` VALUES ('14', '21');
INSERT INTO `t_role_permission` VALUES ('16', '21');
INSERT INTO `t_role_permission` VALUES ('16', '22');
INSERT INTO `t_role_permission` VALUES ('16', '23');
INSERT INTO `t_role_permission` VALUES ('14', '24');
INSERT INTO `t_role_permission` VALUES ('16', '24');
INSERT INTO `t_role_permission` VALUES ('16', '25');
INSERT INTO `t_role_permission` VALUES ('16', '26');
INSERT INTO `t_role_permission` VALUES ('16', '27');
INSERT INTO `t_role_permission` VALUES ('14', '28');
INSERT INTO `t_role_permission` VALUES ('16', '28');
INSERT INTO `t_role_permission` VALUES ('14', '29');
INSERT INTO `t_role_permission` VALUES ('16', '29');
INSERT INTO `t_role_permission` VALUES ('14', '30');
INSERT INTO `t_role_permission` VALUES ('16', '30');
INSERT INTO `t_role_permission` VALUES ('14', '31');
INSERT INTO `t_role_permission` VALUES ('16', '31');
INSERT INTO `t_role_permission` VALUES ('14', '32');
INSERT INTO `t_role_permission` VALUES ('16', '32');
INSERT INTO `t_role_permission` VALUES ('14', '33');
INSERT INTO `t_role_permission` VALUES ('16', '33');
INSERT INTO `t_role_permission` VALUES ('14', '34');
INSERT INTO `t_role_permission` VALUES ('16', '34');
INSERT INTO `t_role_permission` VALUES ('16', '35');
INSERT INTO `t_role_permission` VALUES ('16', '36');
INSERT INTO `t_role_permission` VALUES ('14', '37');
INSERT INTO `t_role_permission` VALUES ('16', '37');
INSERT INTO `t_role_permission` VALUES ('16', '38');
INSERT INTO `t_role_permission` VALUES ('16', '39');
INSERT INTO `t_role_permission` VALUES ('16', '40');
INSERT INTO `t_role_permission` VALUES ('14', '42');
INSERT INTO `t_role_permission` VALUES ('16', '42');
INSERT INTO `t_role_permission` VALUES ('16', '43');
INSERT INTO `t_role_permission` VALUES ('16', '44');
INSERT INTO `t_role_permission` VALUES ('16', '45');
INSERT INTO `t_role_permission` VALUES ('16', '46');
INSERT INTO `t_role_permission` VALUES ('16', '47');
INSERT INTO `t_role_permission` VALUES ('16', '56');
INSERT INTO `t_role_permission` VALUES ('15', '65');
INSERT INTO `t_role_permission` VALUES ('16', '65');
INSERT INTO `t_role_permission` VALUES ('19', '65');
INSERT INTO `t_role_permission` VALUES ('15', '66');
INSERT INTO `t_role_permission` VALUES ('16', '66');
INSERT INTO `t_role_permission` VALUES ('19', '66');
INSERT INTO `t_role_permission` VALUES ('15', '67');
INSERT INTO `t_role_permission` VALUES ('16', '67');
INSERT INTO `t_role_permission` VALUES ('19', '67');
INSERT INTO `t_role_permission` VALUES ('15', '68');
INSERT INTO `t_role_permission` VALUES ('16', '68');
INSERT INTO `t_role_permission` VALUES ('19', '68');
INSERT INTO `t_role_permission` VALUES ('15', '69');
INSERT INTO `t_role_permission` VALUES ('16', '69');
INSERT INTO `t_role_permission` VALUES ('19', '69');
INSERT INTO `t_role_permission` VALUES ('15', '70');
INSERT INTO `t_role_permission` VALUES ('16', '70');
INSERT INTO `t_role_permission` VALUES ('19', '70');
INSERT INTO `t_role_permission` VALUES ('15', '71');
INSERT INTO `t_role_permission` VALUES ('16', '71');
INSERT INTO `t_role_permission` VALUES ('19', '71');
INSERT INTO `t_role_permission` VALUES ('15', '72');
INSERT INTO `t_role_permission` VALUES ('16', '72');
INSERT INTO `t_role_permission` VALUES ('19', '72');
INSERT INTO `t_role_permission` VALUES ('15', '73');
INSERT INTO `t_role_permission` VALUES ('16', '73');
INSERT INTO `t_role_permission` VALUES ('19', '73');
INSERT INTO `t_role_permission` VALUES ('15', '74');
INSERT INTO `t_role_permission` VALUES ('16', '74');
INSERT INTO `t_role_permission` VALUES ('19', '74');
INSERT INTO `t_role_permission` VALUES ('15', '75');
INSERT INTO `t_role_permission` VALUES ('16', '75');
INSERT INTO `t_role_permission` VALUES ('19', '75');
INSERT INTO `t_role_permission` VALUES ('15', '76');
INSERT INTO `t_role_permission` VALUES ('16', '76');
INSERT INTO `t_role_permission` VALUES ('19', '76');
INSERT INTO `t_role_permission` VALUES ('15', '77');
INSERT INTO `t_role_permission` VALUES ('16', '77');
INSERT INTO `t_role_permission` VALUES ('19', '77');
INSERT INTO `t_role_permission` VALUES ('14', '78');
INSERT INTO `t_role_permission` VALUES ('15', '78');
INSERT INTO `t_role_permission` VALUES ('16', '78');
INSERT INTO `t_role_permission` VALUES ('19', '78');
INSERT INTO `t_role_permission` VALUES ('14', '79');
INSERT INTO `t_role_permission` VALUES ('15', '79');
INSERT INTO `t_role_permission` VALUES ('16', '79');
INSERT INTO `t_role_permission` VALUES ('19', '79');
INSERT INTO `t_role_permission` VALUES ('14', '80');
INSERT INTO `t_role_permission` VALUES ('15', '80');
INSERT INTO `t_role_permission` VALUES ('16', '80');
INSERT INTO `t_role_permission` VALUES ('19', '80');
INSERT INTO `t_role_permission` VALUES ('14', '81');
INSERT INTO `t_role_permission` VALUES ('16', '81');
INSERT INTO `t_role_permission` VALUES ('19', '81');
INSERT INTO `t_role_permission` VALUES ('16', '82');
INSERT INTO `t_role_permission` VALUES ('19', '82');
INSERT INTO `t_role_permission` VALUES ('16', '83');
INSERT INTO `t_role_permission` VALUES ('19', '83');
INSERT INTO `t_role_permission` VALUES ('14', '84');
INSERT INTO `t_role_permission` VALUES ('16', '84');
INSERT INTO `t_role_permission` VALUES ('19', '84');
INSERT INTO `t_role_permission` VALUES ('16', '85');
INSERT INTO `t_role_permission` VALUES ('19', '85');
INSERT INTO `t_role_permission` VALUES ('14', '86');
INSERT INTO `t_role_permission` VALUES ('15', '86');
INSERT INTO `t_role_permission` VALUES ('16', '86');
INSERT INTO `t_role_permission` VALUES ('19', '86');
INSERT INTO `t_role_permission` VALUES ('14', '87');
INSERT INTO `t_role_permission` VALUES ('15', '87');
INSERT INTO `t_role_permission` VALUES ('16', '87');
INSERT INTO `t_role_permission` VALUES ('19', '87');
INSERT INTO `t_role_permission` VALUES ('16', '88');
INSERT INTO `t_role_permission` VALUES ('19', '88');
INSERT INTO `t_role_permission` VALUES ('15', '89');
INSERT INTO `t_role_permission` VALUES ('16', '89');
INSERT INTO `t_role_permission` VALUES ('19', '89');
INSERT INTO `t_role_permission` VALUES ('16', '90');
INSERT INTO `t_role_permission` VALUES ('19', '90');
INSERT INTO `t_role_permission` VALUES ('14', '91');
INSERT INTO `t_role_permission` VALUES ('15', '91');
INSERT INTO `t_role_permission` VALUES ('16', '91');
INSERT INTO `t_role_permission` VALUES ('19', '91');
INSERT INTO `t_role_permission` VALUES ('14', '92');
INSERT INTO `t_role_permission` VALUES ('15', '92');
INSERT INTO `t_role_permission` VALUES ('16', '92');
INSERT INTO `t_role_permission` VALUES ('19', '92');
INSERT INTO `t_role_permission` VALUES ('14', '93');
INSERT INTO `t_role_permission` VALUES ('15', '93');
INSERT INTO `t_role_permission` VALUES ('16', '93');
INSERT INTO `t_role_permission` VALUES ('19', '93');
INSERT INTO `t_role_permission` VALUES ('16', '94');
INSERT INTO `t_role_permission` VALUES ('19', '94');
INSERT INTO `t_role_permission` VALUES ('19', '95');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(16) NOT NULL DEFAULT '保密' COMMENT '性别，0:女,1:男,2:保密',
  `avatar` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `is_phone_actived` tinyint(1) NOT NULL DEFAULT '0' COMMENT '手机是否激活, 0:未激活,1:已激活',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `is_email_actived` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮箱是否激活, 0:未激活,1:已激活',
  `native_place` varchar(64) DEFAULT NULL COMMENT '籍贯',
  `birthday` date NOT NULL DEFAULT '1990-01-01' COMMENT '出生日期',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `nation` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `county` varchar(64) DEFAULT NULL COMMENT '区/县',
  `address` varchar(256) DEFAULT NULL COMMENT '联系地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `marital_status` varchar(16) NOT NULL DEFAULT '保密' COMMENT '婚姻状况，0:保密,1:未婚,2:已婚,3:离异,4:丧偶',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级账号ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否可用, 0:禁用,1:可用',
  `remark` varchar(512) DEFAULT NULL COMMENT '描述/备注信息',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除, 0:否,1:是',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '$2a$10$wIktH7g6oMQcxanX7Afrh.d5FXvxJQ89Rn.Yxp91gCq8/Rgawxlai', '管理员', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-07-31 18:03:43', '保密', null, '1', null, '0', '2018-07-31 18:03:43', '2018-01-23 15:06:33');
INSERT INTO `t_user` VALUES ('2', 'addawd', '$2a$10$N.JEuJnN04pdRcwCiA/cWO/RsTnaed.4bKwFue5KyHei.pMSvQzmK', '啊啊', null, '保密', 'http://otqftko25.bkt.clouddn.com/2018/03/07/49afe63a-2cb8-49e2-9a28-b81553c0923a.jpg', null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-24 10:21:17');
INSERT INTO `t_user` VALUES ('3', 'admin100', '$2a$10$LGTLiI7t4AjWe/H8A5qUWOKYV10MMu8f4PlS0585xtF/h0sMpSQE6', '吖吖', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-02-07 11:00:50', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-24 10:29:19');
INSERT INTO `t_user` VALUES ('14', 'admin101', '$2a$10$WJVuM7JcwxrYMfBY/gRiMeYzT4OfHPNr8i.u5LlHgJvMGqVhxHx8W', '丫丫', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-02-01 09:33:25', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:22:27');
INSERT INTO `t_user` VALUES ('15', 'admin102', '$2a$10$pZpflm4o.00D6P7Ph/2HP.QodA1q3XH0pcrNEcBfQsbtIILpacDE.', '丫丫丫', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-31 09:09:15', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:22:51');
INSERT INTO `t_user` VALUES ('16', 'admin103', '$2a$10$lM.0k/U1exn2Cu59RWR07.45izH4bU3kcDz2PHaqdDX7Y6zLr21Zi', '呀呀', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-29 09:46:19', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:23:02');
INSERT INTO `t_user` VALUES ('17', 'admin104', '$2a$10$jFtaM7flocByrsVd/WJWW.j0y1LXL57bvEm97UR1tafmyaGizYeya', '亚亚', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-26 14:36:17', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:23:17');
INSERT INTO `t_user` VALUES ('18', 'admin105', '$2a$10$bu7deWa6cQNJV4F22NDRuO3zdmZDHTqc0RqMBYHz2LEyDE7qiuJtC', '啦啦', null, '保密', null, '13100001231', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-26 11:40:09', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:23:49');
INSERT INTO `t_user` VALUES ('19', 'admin106', '$2a$10$kS5Dla0xRa1CDzwsNkd8ReqVijXf/8.h8Qds128bnEgUMoFKaqHi.', '拉拉', null, '保密', null, '13100001230', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-26 11:24:32', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:23:56');
INSERT INTO `t_user` VALUES ('20', 'admin107', '$2a$10$EWRf4Dvr72pp6nLPxHR7I.MsQWS/jmb7yTu46UJ0VD2JsBD6sSHwC', '哦哦', null, '保密', null, '13100001234', '0', '123456@qqq.com', '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-27 09:54:16', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:38:17');
INSERT INTO `t_user` VALUES ('21', 'admin108', '$2a$10$H5/ybBgpTZA6N9gfw.OXjecmHxbRMkYraUdcON0k2M993XkiMgXA.', '噢噢', null, '保密', null, '13500000001', '0', 'oo12345@qq.com', '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-26 10:40:04', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:38:41');
INSERT INTO `t_user` VALUES ('22', 'admin109', '$2a$10$IIrkCDyCuHFHl/i1Tbqer.FoyG8QAtMRNZpx1M3f6AERXsMNgnWcS', '哦噢', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-30 16:14:44', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:40:19');
INSERT INTO `t_user` VALUES ('23', 'admin110', '$2a$10$OKfnk80nvFzj.EByOrCXMOvmLmyMN0w9cVIirV7CayP1cdBlXQxKK', '喔噢', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-25 17:25:13', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-25 11:42:23');
INSERT INTO `t_user` VALUES ('24', 'string123', '$2a$10$6Hajasm2cToa6wFNzY9UXOK.z7skEE7oceXaElDafSnziSUwy5QSO', '呵呵', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-30 17:21:14', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-26 11:03:28');
INSERT INTO `t_user` VALUES ('25', 'admin111', '$2a$10$gB2oGpV37kqUGlsjfxi5bOYavqwTuRwJNnIXkydtE4OctOl8BdAPi', '习大大', null, '保密', null, '18100001234', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-29 18:18:11', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-29 18:08:27');
INSERT INTO `t_user` VALUES ('26', 'string', '$2a$10$pWjbpjxVG09H1k79v1OUoeI4XJGcIyhYc9DhHRTdhmgWPUz8GusJ2', '哈哈哈', null, '保密', null, '18812345678', '0', 'string@qq.com', '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-31 11:44:31', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-30 15:35:23');
INSERT INTO `t_user` VALUES ('27', 'tese001', '$2a$10$CYE9GJOhiDo7tQWjzics4e97wf9jdHMwIHk3YAskKoDVX7EfKZwhe', '测试一号', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-01-30 17:54:17', '2018-01-30 17:52:58');
INSERT INTO `t_user` VALUES ('28', 'a123465', '$2a$10$W/THnIHKiPUrIjVZwWHLze9TX.aU9uNJEnYoU9XjZg8oHgfMmCwz.', '啊啊啊啊', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-30 17:57:28', '保密', null, '1', null, '1', '2018-01-30 18:02:57', '2018-01-30 17:56:15');
INSERT INTO `t_user` VALUES ('29', 'aa123456', '$2a$10$W/THnIHKiPUrIjVZwWHLze9TX.aU9uNJEnYoU9XjZg8oHgfMmCwz.', '啊啊啊啊啊啊', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-30 18:02:10', '保密', null, '1', null, '1', '2018-01-30 18:02:57', '2018-01-30 17:56:26');
INSERT INTO `t_user` VALUES ('30', 'look001', '$2a$10$G6Y6ZwZpPyLCZc85uQJBAeco5bddWx/gmu5aSpDcdqYtO0pgQAG.e', '查看员一', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-02-05 15:33:23', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-30 21:46:00');
INSERT INTO `t_user` VALUES ('31', 'look002', '$2a$10$6aEymQOTc52lj.EtCArrGuZ2QChxV8iPcaKzNWu6cVxp2Gpfh6Yai', '查看员二', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-01-31 15:11:56', '保密', null, '1', null, '1', '2018-07-10 11:05:16', '2018-01-31 15:11:48');
INSERT INTO `t_user` VALUES ('87', '15308962370', '$2a$10$Ba9rVAdpR33GtP2zOE2akOXqLi93kh8Vp.2BS6revxUU6FBXsoB0G', '小罗', '俄罗斯', '保密', 'http://otqftko25.bkt.clouddn.com/2018/03/07/51ff252a-bd49-4a07-989f-552c67528ba8.jpg', '15308962370', '0', null, '0', null, '1990-01-01', '460030199201276615', null, null, null, null, null, '2018-03-07 10:25:04', '保密', null, '1', null, '0', '2018-03-08 09:39:30', '2018-03-07 10:23:51');
INSERT INTO `t_user` VALUES ('88', '13112345678', '$2a$10$5uFU6buCt4hh6aFCLebA9OCx1PRt7Ui7Rp/ARwj7Ofu0h0/PRdy.S', null, null, '保密', null, '13112345678', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-07 15:14:50', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-07 15:13:36');
INSERT INTO `t_user` VALUES ('90', '13632762173', '$2a$10$lxIcLKyd3zhGLBHGxkQKYOi5aeEzzmRcSnivXThqEWElUoY7vPROe', '阿狸', '阿狸', '保密', null, '13632762173', '0', null, '0', null, '1990-01-01', '440783199901011314', null, null, null, null, null, '2018-03-15 09:47:43', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-08 14:17:19');
INSERT INTO `t_user` VALUES ('91', '13612345678', '$2a$10$1WDq/YloKABzbHAmX2jyGuFyk/QO2EGOgMggUXhUy3lAkw7zTWrp2', null, '1111', '保密', null, '13612345678', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-09 11:48:59', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-09 11:47:43');
INSERT INTO `t_user` VALUES ('92', '15308962306', '$2a$10$8gogxeU9gXd0CCIzPmvcCe5r7obzZK4xLWoe3uiCXA0t/AhSbnHqe', null, 'string', '保密', null, '15308962306', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-09 13:09:09', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-09 13:07:55');
INSERT INTO `t_user` VALUES ('93', '13687654321', '$2a$10$.d1l7lWaPHOrChjHDNKUauov76.4dVaBbG48TU9qhnAPrt/A7Na/C', null, '1111', '保密', null, '13687654321', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-09 18:03:55');
INSERT INTO `t_user` VALUES ('94', '13687654322', '$2a$10$.j/T51Viq46OznIdUD796eEWHBpWofq2wfM0YXfyl5iy7/yCVcfe.', null, '1111', '保密', null, '13687654322', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-09 18:04:25');
INSERT INTO `t_user` VALUES ('95', '18387654321', '$2a$10$hw3YDkDEdUTQ9Wb7SeS/u.i6GP/u5bMaZOWqqBxkMhyG./tcMSNY2', null, '111', '保密', null, '18387654321', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 09:58:36');
INSERT INTO `t_user` VALUES ('99', '13632762174', '$2a$10$5wcLgtgZ3O1G6JuAkpRzZumTzivzypkk5NEk4cOwEjIpgG8EXUzSy', null, '茂达', '保密', null, '13632762174', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 15:03:33', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 10:09:05');
INSERT INTO `t_user` VALUES ('100', '18300001234', '$2a$10$KkrTdqZpEnbK2n54lRZBL.hKxVTZduwOb/reR1NQHRYa4fkMOgLEW', null, 'nickname', '保密', null, '18300001234', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 10:10:23', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 10:09:08');
INSERT INTO `t_user` VALUES ('101', '18300001235', '$2a$10$2m1RHhqjW/ttdwjyJCJOR.6rhiPPv.Rws2YHFSDHuhOgQu9HdRdyS', null, 'nickname', '保密', null, '18300001235', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 10:11:30', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 10:10:15');
INSERT INTO `t_user` VALUES ('103', '15308962701', '$2a$10$PRXRdUh7QIVhNb8Mlz6H/.2aI8YoBr1Hmv1ogc7Khe5KrZ1d5/912', null, '别名', '保密', null, '15308962701', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 10:49:22', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 10:47:39');
INSERT INTO `t_user` VALUES ('110', '15308962702', '$2a$10$.oy8M/w8CcDu4KDgBdl/weJnCtRDoHVACsS87bOfT5CNhgK839Tqi', null, '别名', '保密', null, '15308962702', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 10:59:24', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 10:58:09');
INSERT INTO `t_user` VALUES ('117', '15308962703', '$2a$10$GTVOat9n2pw43aQIrUdjPeFNALx3Pl7nLTAz45CMO6WxlypDUOA2G', null, '别名', '保密', null, '15308962703', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 12:30:55', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 12:29:40');
INSERT INTO `t_user` VALUES ('118', '15308962704', '$2a$10$ShN98tuHG51kMbO9Y7qCvOyTup5S2Kg.6bWLmQTz4hnN.R0l8jaSS', null, '别名', '保密', null, '15308962704', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 12:42:35');
INSERT INTO `t_user` VALUES ('119', '15308962705', '$2a$10$wTxN.QrZn0KNTktnkG2UqesnMFb7N6EhQqnjRBgbZ9YEvcUb6NZHa', null, '别名', '保密', null, '15308962705', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, null, '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 12:47:52');
INSERT INTO `t_user` VALUES ('120', '15308962706', '$2a$10$1W97E6T7duHe8/JdsHOGWu16kEB9MBG527W7dcSVNpSXONDWc6DqO', null, '别名', '保密', null, '15308962706', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 12:51:32', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 12:50:17');
INSERT INTO `t_user` VALUES ('121', '15308962707', '$2a$10$G3UK4iV2hczQx2kZ7p1H4enFEWSAVASVkwQPKVWDW/.Vx0k9PCVVq', null, '别名', '保密', null, '15308962707', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 12:52:32', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 12:51:17');
INSERT INTO `t_user` VALUES ('122', '18512345678', '$2a$10$BjSViTQ3MLC27ELv8F0wJ.0h7lkCvHNwcDp9FenMEgaKkQ4YnHyMe', null, '112311', '保密', null, '18512345678', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 17:02:12', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 14:17:51');
INSERT INTO `t_user` VALUES ('123', '13632762175', '$2a$10$sXsJu15BCrmRsjIGWUOPouXRfMsv6S1Bm5/f2xbDyYfWvdlJ4phRS', '阿卡丽', '阿狸', '保密', null, '13632762175', '0', null, '0', null, '1990-01-01', '440783199001011310', null, null, null, null, null, '2018-03-15 09:53:46', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 14:46:42');
INSERT INTO `t_user` VALUES ('124', '18512345677', '$2a$10$d8URVB4K9GtQib0TjGp7OeNIdO9LdENSVuwliTtUzLjOy/4QAFJSC', null, '1111', '保密', null, '18512345677', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 16:40:05', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 16:38:50');
INSERT INTO `t_user` VALUES ('125', '18512345676', '$2a$10$5./A1woCz0dPVz42ko3z7OPbL2nN/8qcc9J.YyuGlYVsw.6LNBCMe', null, '1111', '保密', null, '18512345676', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 16:51:15', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 16:50:00');
INSERT INTO `t_user` VALUES ('126', '18512345675', '$2a$10$Dlo7AZbHV3aBQ0g2qEIeUuAlZF3aJiQ3T86PanjJ73nFXASgNS6Ci', null, '1111', '保密', null, '18512345675', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 16:53:00', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 16:51:45');
INSERT INTO `t_user` VALUES ('127', '18512345674', '$2a$10$zCnPNQlgwErii/DdVaN2mOfwJEamuLfVOaKJWEfD.2CDG9dtqMcY2', null, '1111', '保密', null, '18512345674', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 16:54:31', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 16:53:16');
INSERT INTO `t_user` VALUES ('128', '18512345673', '$2a$10$UgrsezCIMOd/d6fNf6CR/.s3rlDzbT3E4Wkfr078.Ljn8K1JjarDq', null, '1111', '保密', null, '18512345673', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-10 16:55:50', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-10 16:54:34');
INSERT INTO `t_user` VALUES ('129', '18888812341', '$2a$10$nraeko9D9yDqMRo0gPaVEODBe7ucNPvW6YBhHNVCmx.BPVu6OGW56', null, '1111', '保密', null, '18888812341', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-13 09:41:10', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-13 09:39:53');
INSERT INTO `t_user` VALUES ('131', '18812345679', '$2a$10$1fOT9hOM3VBrT2rH94MsPeBe3Kc87JwSPzFrajcEQdTVBvUG95x6i', null, '1111', '保密', null, '18812345679', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-13 15:36:07', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-13 10:17:57');
INSERT INTO `t_user` VALUES ('132', '18812345670', '$2a$10$ISjUL4NR7gpmUR6pFYJBp.SAR5dQm3HiYmhws9jWQJDWhD3jAvmcu', null, '1111213123', '保密', null, '18812345670', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-13 10:21:37', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-13 10:20:20');
INSERT INTO `t_user` VALUES ('133', '18876543221', '$2a$10$Zxh5pXsdHAVzSCYo3jYnM.CfRNOtcpy.YesaPPpFHHha6kh9aLzCm', null, '123123123', '保密', null, '18876543221', '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-03-13 17:01:21', '保密', null, '1', null, '1', '2018-07-11 18:11:47', '2018-03-13 15:53:34');
INSERT INTO `t_user` VALUES ('134', 'erbei520', '$2a$10$xRR.L0hVFpgaHH9NR4kZHO1IEdouMB1FlXzYIps58ZyKTGnxlMgzm', '兒贝', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-07-19 15:34:37', '保密', null, '1', null, '0', '2018-07-19 15:34:37', '2018-07-11 10:29:22');
INSERT INTO `t_user` VALUES ('135', 'admintest', '$2a$10$c.bgzKhyIwn1uaQ/XB0yDe3kygwKvv.dhMBDDBlNa1tdH1NOWsJYq', '管理员测试', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-07-19 11:31:18', '保密', null, '1', null, '0', '2018-07-19 11:31:18', '2018-07-12 18:38:28');
INSERT INTO `t_user` VALUES ('136', 'test01', '$2a$10$v2xZdlt.RzvTulBdbV.UMu3WXFlPJHJ/qD55zmnkFUNxpbfNyJ6oW', '测试', null, '保密', null, null, '0', null, '0', null, '1990-01-01', null, null, null, null, null, null, '2018-07-25 15:43:09', '保密', null, '1', null, '0', '2018-07-25 15:43:09', '2018-07-18 16:17:52');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色信息';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('14', '1');
INSERT INTO `t_user_role` VALUES ('14', '3');
INSERT INTO `t_user_role` VALUES ('14', '14');
INSERT INTO `t_user_role` VALUES ('14', '15');
INSERT INTO `t_user_role` VALUES ('14', '16');
INSERT INTO `t_user_role` VALUES ('14', '17');
INSERT INTO `t_user_role` VALUES ('14', '18');
INSERT INTO `t_user_role` VALUES ('14', '19');
INSERT INTO `t_user_role` VALUES ('14', '20');
INSERT INTO `t_user_role` VALUES ('14', '21');
INSERT INTO `t_user_role` VALUES ('14', '22');
INSERT INTO `t_user_role` VALUES ('14', '23');
INSERT INTO `t_user_role` VALUES ('14', '24');
INSERT INTO `t_user_role` VALUES ('14', '25');
INSERT INTO `t_user_role` VALUES ('14', '26');
INSERT INTO `t_user_role` VALUES ('14', '30');
INSERT INTO `t_user_role` VALUES ('15', '1');
INSERT INTO `t_user_role` VALUES ('15', '135');
INSERT INTO `t_user_role` VALUES ('16', '1');
INSERT INTO `t_user_role` VALUES ('16', '135');
INSERT INTO `t_user_role` VALUES ('19', '134');
INSERT INTO `t_user_role` VALUES ('19', '136');
