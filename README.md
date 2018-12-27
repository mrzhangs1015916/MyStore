# 数据库
```
create database MyStore character set utf8;
use MyStore;
```

# 数据库表结构

## 用户表
```
create table person(
id int auto_increment primary key comment "主键", 
userName varchar(100) comment "用户名", 
password varchar(100) comment "密码md5加密",
nickName varchar(50) comment "用户昵称",
userType tinyint(3) comment "类型，买家0，卖家1") 
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

## 内容表
```
create table content(
id int auto_increment primary key comment "主键",  
price bigint  comment "当前价格",
title varchar(100) comment "标题",
icon varchar(200) comment "图片",
abstract varchar(200) comment "摘要",
text mediumtext comment "正文"  )
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

## 交易记录表
```
create table trx(
id int auto_increment primary key comment "主键",  
contentId int  comment "内容ID",
personId int comment "用户ID",
price int comment "购买价格",
time bigint comment "购买时间")
ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

# 表内容
## 用户数据
0编号（buyer）为买家密码为MD5加密的reyub,

1编号（seller）为卖家，密码为MD5加密的relles
```
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('0','buyer','37254660e226ea65ce6f1efd54233424','buyer','0');
update person set id = 0 where userName = "buyer";
insert into `person` (`id`, `userName`, `password`, `nickName`, `userType`) values('1','seller','981c57a5cfb0f868e064904b8745766f','seller','1');
```

## 内容数据
```
INSERT INTO `content` VALUES ('1', '10000', 'JAVA编程思想', 'http://img14.360buyimg.com/n1/jfs/t2191/111/699154754/198998/32d7bfe0/5624b582Nbc01af5b.jpg', 'Java编程思想', 'java编程思想，编程思想'), 
('2', '8888800', '汽车', 'http://img1.xcarimg.com/b101/s5797/m_20140903142527961439.jpg', '汽车', '汽车汽车，汽车'), 
('3', '20000', 'Java编程', 'http://img1.xcarimg.com/b101/s5797/m_20140903142527961439.jpg', 'Java编程思想', 'Java编程思想Java编程思想'), 
('4', '322300', '编程思想132', 'http://img1.xcarimg.com/b101/s5797/m_20140903142527961439.jpg', 'Java编程思想', 'Java编程思想'), 
('6', '1100', '内容2', 'http://avatar.csdn.net/5/2/7/1_by53008749.jpg', '内容2222222', '按时打算打算的撒打算的撒'), 
('7', '7897898', '内容3', 'http://avatar.csdn.net/8/D/F/1_wangeclipse.jpg', '内容3达到', '内容3达到内容3达到内容3达到内容3达到内容3达到内容3达到内容3达到内容3达到内容3达到'), 
('9', '115645', '内容56', 'http://avatar.csdn.net/E/C/F/1_t5500.jpg', 'n内容444http://avatar.csdn.net/E/C/F/1_t5500.jpg', 'http://avatar.csdn.net/E/C/F/1_t5500.jpghttp://avatar.csdn.net/E/C/F/1_t5500.jpghttp://avatar.csdn.net/E/C/F/1_t5500.jpg'), 
('10', '15648900', '内容12', 'http://avatar.csdn.net/6/7/2/1_q547550831.jpg', '前排出售瓜子', '测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，测试用例，'), 
('11', '1234545', '测试用例', 'http://avatar.csdn.net/6/7/2/1_q547550831.jpg', '测试用例', '测试用例，测试用例，测试用例，测试用例，测试用例，'), 
('12', '1561645', '测试2', 'http://avatar.csdn.net/6/7/2/1_q547550831.jpg', '是的', '22222'), 
('13', '4568730', '测试3', 'http://avatar.csdn.net/6/7/2/1_q547550831.jpg', 'http://avatar.csdn.net/6/7/2/1_q547550831.jpg', '测试3333333334124124121');

```

## 订单数据
```
INSERT INTO `trx` VALUES ('1', '1', '1', '9900', '1307041488303'), 
('2', '3', '1', '19000', '1307041488303'), 
('3', '2', '2', '8888800', '1479712205565'), 
('4', '7', '2', '7897898', '1479712223007'), 
('5', '1', '2', '10000', '1479712293546'), 
('6', '1', '2', '10000', '1479712419776'), 
('7', '4', '2', '322300', '1479712609402');
```