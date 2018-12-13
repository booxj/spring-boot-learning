CREATE DATABASE spring_boot_security;
USE spring_boot_security;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1', null);
INSERT INTO `t_role_permission` VALUES ('1', '2', null);
INSERT INTO `t_role_permission` VALUES ('1', '3', null);
INSERT INTO `t_role_permission` VALUES ('1', '4', null);
INSERT INTO `t_role_permission` VALUES ('2', '4', null);

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `pid` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES ('1', '增', 'add', '0');
INSERT INTO `t_sys_permission` VALUES ('2', '删', 'delete', '0');
INSERT INTO `t_sys_permission` VALUES ('3', '改', 'edite', '0');
INSERT INTO `t_sys_permission` VALUES ('4', '查', 'get', '0');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'ADMIN', 'admin');
INSERT INTO `t_sys_role` VALUES ('2', 'GUEST', 'guest');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', 'admin', '$2a$10$Fq8KytG7Fy4/cU8lYEEONOsHjbin/ubL5bfCo9DU5lgNdAAxgf836', null);
INSERT INTO `t_sys_user` VALUES ('2', 'guest', '$2a$10$1c.CKzn92gSN6eDxKdWarObMnRGZgKMSB6ft1pD1v/TyrH76BdjrO', null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `created` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', null);
INSERT INTO `t_user_role` VALUES ('1', '2', null);
INSERT INTO `t_user_role` VALUES ('2', '2', null);
