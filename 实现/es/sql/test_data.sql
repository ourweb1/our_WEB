#user id name email password tele

insert into user values (1 , 'cj' , 'cj@gg.com' , 'cj' , '5252');

#good id user_id name detail price category_id img_src
insert into Good values(1 , 1 , 'Thinking in Java' , '8成新' ,15 ,  'img/1.jpg' ,1);
insert into good values(2 , 1 , 'Thinking in Java' , '8成新' ,15 ,  'img/1.jpg' ,1);
insert into good values(3 , 1 , 'Thinking in Java' , '8成新' ,15 ,  'img/1.jpg' ,1);
insert into good values(4 , 1 , 'Thinking in Java' , '8成新' ,15 ,  'img/1.jpg' ,1);
insert into good values(5 , 1 , 'Thinking in Java' , '8成新' ,15 , 'img/1.jpg' ,1);
insert into good values(6 , 1 , 'Thinking in Java' , '8成新' ,15 , 'img/1.jpg' ,1);


#category id name
insert into category values(1 , '旧书');
insert into category values(11 , '计算机');
insert into category values(12, '出国');
insert into category values(13, '考研');

insert into category values(2 , '生活用品');
insert into category values(21 , '衣服');
insert into category values(22, '化妆品');
insert into category values(23, '杂货');

insert into good_category values(1 , 11);
insert into good_category values(2 , 11);
insert into good_category values(3 , 11);
insert into good_category values(4 , 11);
insert into good_category values(5 , 11);
insert into good_category values(6 , 11);