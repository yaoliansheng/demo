-- BEGIN 测试表
CREATE TABLE IF NOT EXISTS `demo` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` VARCHAR (64)  NOT NULL COMMENT '名称',
  `mobile_no` VARCHAR (64)  NOT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='测试表';
-- END 测试表




























