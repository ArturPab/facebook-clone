--liquibase formatted sql
--changeset apabjan:4

create table TOKEN (
token_id bigint not null auto_increment,
expiry_date datetime(6),
token varchar(255),
user_id bigint,
primary key (token_id)) engine=InnoDB;

alter table TOKEN add constraint FK79keudebybjlldk2o4i0nwqev foreign key (user_id) references user(user_id);