
--create database account_service default charset utf8;

CREATE TABLE IF NOT EXISTS `weichat_account` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `openId` VARCHAR(256) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `delete_flag` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY account_oppenId (`openId`)
) Engine=InnoDB default charset utf8;

