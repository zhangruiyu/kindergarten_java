
SET FOREIGN_KEY_CHECKS=0;



-- ----------------------------
-- Table structure for bbs_user
-- ----------------------------
DROP TABLE IF EXISTS `kg_user`;
CREATE TABLE `kg_user` (
  id int(11)  AUTO_INCREMENT PRIMARY KEY ,
  tel varchar(11) default '' not null,
  login_password varchar(128) default '' not null,
  wx_open_id varchar(100) default '' not null,
  login_count int default '0' not null,
  constraint kg_user_tel_uindex
  unique (tel)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kg_profile`;
CREATE TABLE `kg_profile` (
  id int(11)  AUTO_INCREMENT PRIMARY KEY ,
  passport_id int(10) unsigned default '0' not null,
  tel char(11)  not null,
  real_name varchar(32) default '' null,
  gender_id tinyint(3) unsigned default '0' not null,
  birthday date default '2010-11-21' null,
  address varchar(255) default '' null,
  avatar varchar(255) default '' null,
  add_time datetime not null,
  school_ID int default '0' not null,
  login_count int default '0' not null,
  register_time timestamp default CURRENT_TIMESTAMP not null,
  register_ip varchar(30) default '' not null,
  last_login_time timestamp default CURRENT_TIMESTAMP not null,
  last_login_ip varchar(30) default '' not null,
  constraint kg_user_tel_uindex
  unique (tel)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `kg_role`;
CREATE TABLE `kg_role` (
  id int(11)  AUTO_INCREMENT PRIMARY KEY ,
  role_name varchar(10) null
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `kg_role_user`;
CREATE TABLE `kg_role_user` (
  id int(11)  AUTO_INCREMENT PRIMARY KEY ,
  user_id int(11)  ,
  role_id int(11)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
