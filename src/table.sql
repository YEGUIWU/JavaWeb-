set names utf8mb4;
-- create database my_web;
use my_web;
create table tab_personal_user
(
   pid                  int not null auto_increment,
   username             varchar(20) not null,
   password             varchar(32) not null,
   name                 varchar(8),
   birthday             date,
   sex                  char(1) default '男',
   telephone            varchar(11),
   email                varchar(128),
   brief				varchar(128),
   icon					varchar(32),
   school				varchar(32),
   education			varchar(4),
   status               char(1) ,
   code					varchar(50),
   
   primary key (pid),
   unique key AK_nq_username (username),
   unique key AK_nq_code (code)
);
select * from tab_personal_user;
-- update tab_personal_user set sex='男' where pid=1;
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("yeguiwu", "a123456", "叶贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "709394");
-- delete from tab_user where uid = 3 or uid = 4; 

CREATE TABLE tab_work_exp(
		exp_id int  auto_increment PRIMARY KEY ,
		pid int not null,
		title VARCHAR (64),
		content VARCHAR (256),
		FOREIGN KEY (pid) REFERENCES tab_personal_user(pid) 
);

insert into tab_work_exp(pid, title, content) 
value (1, "实验室工作", "打杂");
select * from tab_work_exp;

CREATE TABLE tab_project_exp(
		exp_id int  auto_increment PRIMARY KEY ,
		pid int not null,
		title VARCHAR (64),
		content VARCHAR (256),
		FOREIGN KEY (pid) REFERENCES tab_personal_user(pid) 
);

insert into tab_project_exp(pid, title, content) 
value (1, "服务器框架", "C++高性能低延迟多线程多协程异步IO服务器框架");
select * from tab_project_exp;
