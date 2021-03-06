# facebook-clone
Facebook-clone application based on Facebook created with Spring Boot and MySQL

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)

## General info
The purpose of creating this project is to broaden my programming skills. RestAPI as Spring Boot application which gives information abours users, posts, comments from database.

## Technologies
- Java 11
- Spring Boot
- Spring Security
- Spring Cache
- Hibernate
- JWT
- MySQL
- Lombok
- Liquibase
- Apache Maven 3.6.3


## Setup
You need to configure your IDE (I'm using Intellij IDEA) with JDK 11 and Apache Maven to run this application. You also need to create MySQL database called Facebook. Tables will be created automatically while application is building.

#### You need to be authenticated with JWT token which you will get after being logged in. For request' needs I used Postman.
![login example](./images/login.PNG)

##### You need to send Post and Put request as a JSON body:
![put example](./images/put.PNG)

##### Get request example:
![get example](./images/get.PNG)

## Features
- Register, verify account with link, which application will send on your e-mail
- Create posts, when you're logged in
- Get posts with comments ordered by creation date
- Get posts filtered by user and by id

#### To Do
- Web Page
- Upload photos
