
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID：标识列',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名：唯一键，登陆时使用',
  `user_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码：长度6~10位，MD5加密',
  `user_type` int(11) NOT NULL COMMENT '用户类型：1 管理员 2 普通用户',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


INSERT INTO `t_user` VALUES (1, 'user257', 'E10ADC3949BA59ABBE56E057F20F883E', 2);
INSERT INTO `t_user` VALUES (2, 'usertest', 'E10ADC3949BA59ABBE56E057F20F883E', 2);
INSERT INTO `t_user` VALUES (3, 'user1', '81DC9BDB52D04DC20036DBD8313ED055', 2);
INSERT INTO `t_user` VALUES (10, '李四', '66666666', 2);
INSERT INTO `t_user` VALUES (11, '文理学生', '12345678', 1);
INSERT INTO `t_user` VALUES (12, '文理学生2', '12345678', 1);
INSERT INTO `t_user` VALUES (13, '文理学生3', '12345678', 1);
INSERT INTO `t_user` VALUES (14, '文理学生5', '12345678', 1);
INSERT INTO `t_user` VALUES (15, '文理学生6', '12345678', 1);
INSERT INTO `t_user` VALUES (16, '文理学生7', '12345678', 1);
INSERT INTO `t_user` VALUES (17, '文理学生8', '12345678', 1);
INSERT INTO `t_user` VALUES (18, '文理学生10', '12345678', 1);

SET FOREIGN_KEY_CHECKS = 1;
