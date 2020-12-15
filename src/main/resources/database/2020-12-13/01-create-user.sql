--liquibase formatted sql
--changeset apabjan:1

create table USER (
user_id bigint not null auto_increment,
email varchar(50) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
name varchar(30) NOT NULL,
last_name varchar(50) NOT NULL,
city varchar(50) NOT NULL,
birthday date NOT NULL,
created datetime(6),
enabled bit not null,
primary key (user_id)) engine=InnoDB;