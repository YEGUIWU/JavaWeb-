set names utf8mb4;
-- create database my_web;
use my_web;

-- ------------------------------------------------------------------------------
-- personal user
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
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("guiwu", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848");
-- delete from tab_user where uid = 3 or uid = 4; 


-- ------------------------------------------------------------------------------
-- 								work exp
-- ------------------------------------------------------------------------------
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

-- ------------------------------------------------------------------------------
-- 									project exp
-- ------------------------------------------------------------------------------
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

-- ------------------------------------------------------------------------------
-- 								enterprise user
-- ------------------------------------------------------------------------------
create table tab_enterprise_user
(
   eid                  int not null auto_increment,
   username             varchar(20) not null,
   password             varchar(32) not null,
   name             	varchar(20),
   email                varchar(128),
   size					varchar(32),
   location				varchar(128),
   logo					varchar(32),
   brief				varchar(128),
   status               char(1),
   
   primary key (eid),
   unique key AK_nq_username (username)
);

insert into tab_enterprise_user(username, password, status)
value ("yeguiwu", "a123456", "Y");
insert into tab_enterprise_user(username, password, status)
value ("baiduyun", "a123456", "Y");
insert into tab_enterprise_user(username, password, status)
value ("jingdong", "a123456", "Y");
insert into tab_enterprise_user(username, password, status)
value ("tenxun", "a123456", "Y");
insert into tab_enterprise_user(username, password, status)
value ("weiruan", "a123456", "Y");
insert into tab_enterprise_user(username, password, status)
value ("huawei", "a123456", "Y");

select * from tab_enterprise_user;
-- update tab_personal_user set sex='男' where pid=1;
-- delete from tab_user where uid = 3 or uid = 4; 

-- ------------------------------------------------------------------------------
-- admin user
-- ------------------------------------------------------------------------------
create table tab_admin_user
(
   aid                  int not null auto_increment,
   username             varchar(20) not null,
   password             varchar(32) not null,
   name             	varchar(20) not null,
   
   primary key (aid),
   unique key AK_nq_username (username)
);

insert into tab_admin_user(username, password, name)
value ("yeguiwu", "a123456", "叶贵鋈");
select * from tab_admin_user;

-- ------------------------------------------------------------------------------
-- 									招聘信息表								   --
-- ------------------------------------------------------------------------------
create table tab_recruit
(
   rid                  int not null auto_increment,
   eid 					int not null,
   title             	varchar(32) not null,
   position             varchar(32),
   salary             	varchar(32),
   description			varchar(256),
   requirement			varchar(256),
   priority				varchar(256),
   welfare				varchar(256),
   issue				date,
   status				varchar(16) default "未发布",
   primary key (rid),
   FOREIGN KEY (eid) REFERENCES tab_enterprise_user(eid) 
);
select * from tab_recruit;
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "Java实习生", "北京", "3-5k", "打杂", "熟练使用各种Java技术", "英语6级优先", "五险一金");
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "C++实习生", "北京", "3-5k", "打杂", "熟练使用各种C++技术", "英语6级优先", "五险一金");
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "前端实习生", "北京", "3-5k", "打杂", "熟练使用各种前端技术", "英语6级优先", "五险一金");
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "php实习生", "北京", "3-5k", "打杂", "熟练使用各种php技术", "英语6级优先", "五险一金");
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "C#实习生", "北京", "3-5k", "打杂", "熟练使用各种C#技术", "英语6级优先", "五险一金");
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "go实习生", "北京", "3-5k", "打杂", "熟练使用各种Go技术", "英语6级优先", "五险一金");
update tab_recruit set issue = date(now()) where rid = 1;
-- ------------------------------------------------------------------------------
-- 									应聘信息表								   --
-- ------------------------------------------------------------------------------
create table tab_apply
(
   aid                  int not null auto_increment,
   pid 					int not null,
   rid 					int not null,
   time             	datetime default now(),
   status				varchar(16) default "待接受",
   primary key (aid), 
   FOREIGN KEY (pid) REFERENCES tab_personal_user(pid),
   FOREIGN KEY (rid) REFERENCES tab_recruit(rid) 
);
insert into tab_apply(pid, rid) value(1, 1);
insert into tab_apply(pid, rid) value(1, 3);
insert into tab_apply(pid, rid) value(2, 1);
insert into tab_apply(pid, rid) value(2, 3);

select * from tab_apply;
-- --------------------------------------------
-- 					apply info
-- --------------------------------------------
select ta.aid, ta.pid, ta.rid, tr.eid, ta.time ,ta.status, tr.title, tpu.name, teu.name
from tab_apply ta
join tab_personal_user tpu on ta.pid = tpu.pid
join tab_recruit tr on tr.rid = ta.rid
join tab_enterprise_user teu on tr.eid = teu.eid
order by ta.time desc; 

-- select ta.aid, ta.pid, ta.rid, ta.status, tr.title, tpu.name 
-- from tab_apply ta 
-- join tab_personal_user tpu on ta.pid = tpu.pid 
-- join tab_recruit tr on tr.rid = ta.rid;


-- select date(now());
-- select curdate();

-- --------------------------------------------
-- 					RecruitInfo(detail)
-- --------------------------------------------
select tr.rid, tr.title, tr.position, tr.salary, tr.description, tr.requirement, tr.priority, tr.welfare, tr.issue, tr.status, teu.brief
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid 
where tr.status = '已发布'
order by tr.rid desc, tr.issue desc;


select count(*) from tab_recruit where tab_recruit.status = "已发布";
-- limit 0,11;

-- --------------------------------------------
-- 					 RecruitBrief
-- --------------------------------------------
select count(*) from tab_recruit;

select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid 
where tr.status = '已发布' 
limit 0,11; 

