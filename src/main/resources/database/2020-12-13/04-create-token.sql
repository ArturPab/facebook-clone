--liquibase formatted sql
--changeset apabjan:4

create table token (
id bigint not null auto_increment,
expiry_date datetime(6),
token varchar(255),
user_user_id bigint,
primary key (id)) engine=InnoDB;

alter table token add constraint FK79keudebybjlldk2o4i0nwqev foreign key (user_user_id) references user(user_id);