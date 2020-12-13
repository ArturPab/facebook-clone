--liquibase formatted sql
--changeset apabjan:2

create table POST (
post_id bigint not null auto_increment,
content varchar(255),
user_id bigint,
created datetime(6),
primary key (post_id)) engine=InnoDB;

alter table POST add constraint FK72mt33dhhs48hf9gcqrq4fxte foreign key (user_id) references user(user_id);