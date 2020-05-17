set names utf8mb4;
-- create database my_web;
use my_web;
create table tab_personal_user
(
   uid                  int not null auto_increment,
   username             varchar(20) not null,
   password             varchar(32) not null,
   name                 varchar(8),
   birthday             date,
   sex                  char(1),
   telephone            varchar(11),
   email                varchar(128),
   brief				varchar(128),
   icon					varchar(32),
   school				varchar(32),
   education			varchar(4),
   status               char(1) ,
   code					varchar(50),
   
   primary key (uid),
   unique key AK_nq_username (username),
   unique key AK_nq_code (code)
);
-- insert into tab_user(username,password,email,status,code) values("yeguiwu", "a123456", "yeguiwu@qq.com", 'Y', "709394");
select * from tab_personal_user;
-- delete from tab_user where uid = 3 or uid = 4; 