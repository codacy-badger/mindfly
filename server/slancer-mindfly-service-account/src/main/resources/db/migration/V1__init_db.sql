
create database account_service default charset=utf8mb4;

CREATE TABLE IF NOT EXISTS `user_info` (
  `id` unsigned bigint(20)  NOT NULL,
  `nick_name` varchar(100) NOT NULL,
  `gender` unsigned tinyint not NULL default 0,
  `birthday` datetime NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL,
  `delete_flag` unsigned tinyint NOT NULL default 0,
  PRIMARY KEY (`id`)
) Engine=InnoDB default charset=utf8mb4;
--
--CREATE TABLE IF NOT EXISTS `username_account` (
--  `user_id` BIGINT(20) UNSIGNED NOT NULL,
--  `username` VARCHAR(256) NOT NULL,
--  `password` VARCHAR(256) NOT NULL,
--  `create_time` DATETIME NOT NULL,
--  PRIMARY KEY (`user_id`),
--  UNIQUE KEY username_account_username (`username`)
--) Engine=InnoDB default charset=utf8mb4;

