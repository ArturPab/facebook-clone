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

--changeset apabjan:2

insert into user (user_id, email, password, name, last_name, city, birthday, created, enabled) values (null, 'test@test.com', '$2a$10$1pLr1Evlg2KdH5RWVD4VUOQ2cYvgk57lVhcj7BiMNwljeW.GWYLaW', 'test', 'test', 'Katowice', '2000-09-05', '2021-01-18 19:27:05.068335', 1);