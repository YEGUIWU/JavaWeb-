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
   status               char(1),
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
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("test1", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848_1");
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("test2", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848_2");
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("test3", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848_4");
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("test4", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848_3");
insert into tab_personal_user(username,password,name,birthday,sex, telephone, email, brief, status,code)
values("test5", "a123456", "贵鋈", "1999-11-3","男", "15815994727","yeguiwu@qq.com", "nothing is impossible!!!", 'Y', "8848_5");
-- delete from tab_user where uid = 3 or uid = 4; 

-- update tab_personal_user set sex='男' where pid=1;
-- delete from tab_user where uid = 3 or uid = 4; 

-- --------------------------------------------
-- 				PersonalBrief
-- --------------------------------------------
select pid, username, email, status from tab_personal_user;

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
   
   code					varchar(50),
   primary key (eid),
   unique key AK_nq_username (username),
   unique key AK_nq_code (code)
);
select * from tab_enterprise_user;
-- ALTER TABLE tab_enterprise_user
-- ADD code varchar(50);
-- ALTER TABLE tab_enterprise_user
-- ADD unique key AK_nq_code (code);
update  tab_enterprise_user
set status = "Y" where eid = 7;
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

-- --------------------------------------------
-- 				EnterpriseBrief
-- --------------------------------------------
select eid, username, email, status from tab_enterprise_user limit 2,2;



-- update tab_enterprise_user set email='test@mail.com' where eid=6;


------------------------------------------------------------------------------
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
 update tab_recruit set status = "已关闭" where rid = 1;
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
insert into tab_recruit(eid, title, position, salary, description, requirement,priority,welfare)
value (1, "vb实习生", "北京", "3-5k", "打杂", "熟练使用各种vb技术", "英语6级优先", "五险一金");
update tab_recruit set issue = date(now()) where rid = 1;
update tab_recruit set status = "已发布" where rid = 1;


-- --------------------------------------------
-- 			recrut management info
-- --------------------------------------------

explain select tr.rid, tr.eid, teu.username, teu.name, tr.issue, tr.title
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid
where tr.status = "已发布"
limit 0,3;


create index idx_status on tab_recruit(status);
-- drop index idx_status on tab_recruit;
show index from tab_recruit;

explain select tr.rid, tr.eid, teu.username, teu.name, tr.issue, tr.title
from tab_recruit tr, tab_enterprise_user teu
where tr.eid = teu.eid and tr.status = "已发布";

explain select * from tab_recruit where status = '已发布';
explain select * from tab_recruit where eid = '1' and status = '已发布';


-- --------------------------------------------
-- 					RecruitInfo(detail)
-- --------------------------------------------
explain select tr.rid, tr.title, tr.position, tr.salary, tr.description, tr.requirement, tr.priority, tr.welfare, tr.issue, tr.status, teu.brief
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid 
where tr.status = '已发布'
order by tr.rid desc;


select count(*) from tab_recruit where tab_recruit.status = "已发布";
-- limit 0,11;

-- --------------------------------------------
-- 					 RecruitBrief
-- --------------------------------------------
select count(*) from tab_recruit;

explain select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid 
where tr.status = '已发布'
order by tr.rid desc, tr.issue desc
limit 0,11; 

explain select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary
from tab_recruit tr ,tab_enterprise_user teu
where tr.eid = teu.eid  and tr.status = '已发布'
order by tr.rid desc
limit 0,11; 

-- --------------------------------------------
-- 				Redis RecruitBrief
-- --------------------------------------------

explain select tr.rid, teu.eid, teu.name, teu.logo, tr.title, tr.issue, tr.position, tr.salary
from tab_recruit tr
join tab_enterprise_user teu on tr.eid = teu.eid 
where tr.status = '已发布' and(tr.rid = 1 or tr.rid = 3 or tr.rid = 4);

-- -------------------------------------------------- 
-- 						查外键
-- -------------------------------------------------- 
create index idx_eid_status on tab_recruit(eid,status);
SELECT
  constraint_name
FROM
  information_schema.REFERENTIAL_CONSTRAINTS
WHERE
  constraint_schema = 'my_web' AND table_name = 'tab_recruit';
  
ALTER TABLE tab_recruit
DROP FOREIGN KEY tab_recruit_ibfk_3;
drop index idx_rid_status on tab_recruit;

ALTER TABLE tab_recruit
ADD FOREIGN KEY (eid)
REFERENCES tab_enterprise_user(eid);

------------------------------------------------------------------------------
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

update tab_apply set status = "待面试" where aid = 15;

select * from tab_apply;

delete from tab_apply where aid = 17;
-- --------------------------------------------
-- 					apply info
-- --------------------------------------------
show index from tab_apply;
create index idx_status on tab_apply(status);
explain select ta.aid, ta.pid, ta.rid, tr.eid, ta.time ,ta.status, tr.title, tpu.name, teu.name
from tab_apply ta
join tab_personal_user tpu on ta.pid = tpu.pid
join tab_recruit tr on tr.rid = ta.rid
join tab_enterprise_user teu on tr.eid = teu.eid
order by ta.time desc; 
-- order by ta.aid desc; 

explain select ta.aid, ta.pid, ta.rid, tr.eid, ta.time ,ta.status, tr.title, tpu.name, teu.name
from tab_apply ta, tab_personal_user tpu,tab_recruit tr, tab_enterprise_user teu
where ta.pid = tpu.pid and tr.rid = ta.rid and tr.eid = teu.eid
-- order by ta.time desc; 
order by ta.aid desc; 

explain select ta.aid, ta.pid, ta.rid, ta.status, tr.title, tpu.name 
from tab_apply ta 
join tab_personal_user tpu on ta.pid = tpu.pid 
join tab_recruit tr on tr.rid = ta.rid;

explain select ta.aid, ta.pid, ta.rid, ta.status, tr.title, tpu.name 
from tab_apply ta, tab_personal_user tpu,tab_recruit tr
where ta.pid = tpu.pid and tr.rid = ta.rid;


-- select date(now());
-- select curdate();


-- ------------------------------------------------------------------------------
-- 									投诉信息表								   --
-- ------------------------------------------------------------------------------
create table tab_complain
(
   cid                  int not null auto_increment,
   pid					int not null,
   rid 					int not null,
   title 				varchar(32),
   content				varchar(256),
   time             	datetime default now(),
   status				varchar(16) default "待处理",
   result				varchar(64),
   primary key (cid), 
   FOREIGN KEY (pid) REFERENCES tab_personal_user(pid),
   FOREIGN KEY (rid) REFERENCES tab_recruit(rid)
);
select * from tab_complain;
insert into tab_complain(pid, rid, title, content) value (1, 1, "薪资有问题", "钱太少");



-- --------------------------------------------
-- 				ComplainInfo table
-- --------------------------------------------
explain select tc.cid, tc.pid, tc.rid, tc.title, tc.content, tc.time, tc.status, tc.result, teu.name, tr.title
from tab_complain tc
join tab_recruit tr on tc.rid = tr.rid
join tab_enterprise_user teu on teu.eid = tr.eid;

-- --------------------------------------------
-- 		Complain Management Info table
-- --------------------------------------------
-- select tc.cid, tc.pid, tc.rid, teu.eid, tc.time, tpu.username, teu.username, tr.title, tc.status
explain select *
from tab_complain tc
join tab_personal_user tpu on tc.pid = tpu.pid
join tab_recruit tr on tc.rid = tr.rid 
join tab_enterprise_user teu on tr.eid = teu.eid
where tc.status = '已处理';
-- limit 0, 5;

-- ------------------------------------------------------------------------------
-- 									黑名单信息表	   						   --
-- ------------------------------------------------------------------------------
create table tab_blacklist
(
   bid                  int not null auto_increment,
   pid					int not null,
   eid					int not null,
   primary key (bid), 
   FOREIGN KEY (pid)    REFERENCES tab_personal_user(pid),
   FOREIGN KEY (eid)    REFERENCES tab_enterprise_user(eid),
   UNIQUE KEY `eid` (`eid`, `pid`)
);

select * from tab_blacklist;

insert into tab_blacklist(pid, eid) value(4,1);
-- --------------------------------------------
-- 				BlackInfo table
-- --------------------------------------------
explain select tb.bid, tb.pid, tb.eid, tpu.username, tpu.name
from tab_blacklist tb
join tab_personal_user tpu on tb.pid = tpu.pid;

select * from tab_admin_user where username = 'yeguiwu'