--liquibase formatted sql
--changeset apabjan:2

create table POST (
post_id bigint not null auto_increment,
content varchar(255),
user_id bigint,
created datetime(6),
primary key (post_id)) engine=InnoDB;

alter table POST add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user(user_id);

insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846906');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846907');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846908');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846909');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846910');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846926');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846936');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846946');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846956');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846966');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846976');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846986');
insert into post (post_id, content, user_id, created) values (null, 'test', 1, '2021-01-18 19:29:19.846996');