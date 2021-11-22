DROP DATABASE IF EXISTS objects;
CREATE DATABASE objects;
USE objects;
CREATE TABLE User(
id int primary key auto_increment,
username varchar(20) unique not null,
password varchar(20) not null,
name varchar(30) not null,
surname varchar(30) not null,
birthDate date not null,
email varchar(80) not null
);
