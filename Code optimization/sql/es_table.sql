/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/6/20 9:57:54                            */
/*==============================================================*/

drop database if exists es;

create database es;

use es;

drop table if exists Category;

drop table if exists Comment;

drop table if exists Good;

drop table if exists User;

drop table if exists collect_goods;

drop table if exists good_category;

/*==============================================================*/
/* Table: Category                                              */
/*==============================================================*/
create table Category
(
   id                   int not null auto_increment,
   name                 varchar(20) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Comment                                               */
/*==============================================================*/
create table Comment
(
   id                   int not null auto_increment,
   good_id              int,
   user_id              int,
   pid                  int,
   content              varchar(200) not null,
   root_id              int,
   primary key (id)
);

/*==============================================================*/
/* Table: Good                                                  */
/*==============================================================*/
create table Good
(
   id                   int not null auto_increment,
   user_id              int,
   name                 varchar(20) not null,
   detail               varchar(200),
   price                double,
   img_src              varchar(20),
   create_time			datetime default now(),
   primary key (id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id                   int not null auto_increment,
   name                 varchar(20) not null,
   email                varchar(20) not null,
   password             varchar(20) not null,
   telephone            varchar(20),
   primary key (id)
);

/*==============================================================*/
/* Table: collect_goods                                         */
/*==============================================================*/
create table collect_goods
(
   user_id              int,
   good_id              int
);

/*==============================================================*/
/* Table: good_category                                         */
/*==============================================================*/
create table good_category
(
   good_id              int,
   category_id          int
);

alter table Comment add constraint FK_Reference_3 foreign key (user_id)
      references User (id) on delete restrict on update restrict;

alter table Comment add constraint FK_Reference_4 foreign key (good_id)
      references Good (id) on delete restrict on update restrict;

alter table Comment add constraint FK_Reference_7 foreign key (pid)
      references Comment (id) on delete restrict on update restrict;

alter table Good add constraint FK_Reference_1 foreign key (user_id)
      references User (id) on delete restrict on update restrict;

alter table collect_goods add constraint FK_Reference_8 foreign key (good_id)
      references Good (id) on delete restrict on update restrict;

alter table collect_goods add constraint FK_Reference_9 foreign key (user_id)
      references User (id) on delete restrict on update restrict;

alter table good_category add constraint FK_Reference_5 foreign key (good_id)
      references Good (id) on delete restrict on update restrict;

alter table good_category add constraint FK_Reference_6 foreign key (category_id)
      references Category (id) on delete restrict on update restrict;

