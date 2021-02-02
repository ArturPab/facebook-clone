--liquibase formatted sql
--changeset apabjan:3

create table COMMENT (
comment_id bigint not null auto_increment,
content varchar(255),
created datetime(6),
post_id bigint,
user_id bigint,
primary key (comment_id)) engine=InnoDB;

alter table COMMENT add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 foreign key (post_id) references post(post_id);
alter table COMMENT add constraint FK8kcum44fvpupyw6f5baccx25c foreign key (user_id) references user(user_id);

insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 1, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 1, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 1, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 8, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 8, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 8, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 9, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 9, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 9, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 10, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 10, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 10, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 10, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 11, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 11, 1);
insert into COMMENT (comment_id, content, created, post_id, user_id) values (null, 'test', '2021-01-18 19:29:19.846996', 11, 1);
