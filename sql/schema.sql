SET SQL_SAFE_UPDATES = 0;

DROP DATABASE IF EXISTS objects;

CREATE DATABASE objects;

USE objects;

CREATE TABLE User(
id int primary key auto_increment,
username varchar(20) unique not null,
password varchar(100) not null,
name varchar(30) not null,
surname varchar(30) not null,
birthDate date not null,
email varchar(80) not null
);

CREATE TABLE Bucket(
uri varchar(20),
usernameOwner varchar(20),
FOREIGN KEY (usernameOwner) REFERENCES User(username)
ON DELETE CASCADE
ON UPDATE CASCADE,
PRIMARY KEY(uri)
);


CREATE TABLE Object(
	id int unique auto_increment,
	name varchar(50),
    uri varchar(100),
	bucketUri varchar(20),
    usernameOwner varchar(20),
    content LONGBLOB,
    version int not null,
    contentLength int,
    contentType varchar(10),
    lastModified date,
    createdDate date,
    hash text,
    PRIMARY KEY(id,version,uri,usernameOwner,bucketUri),
    FOREIGN KEY(usernameOwner) REFERENCES User(username) 
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (bucketUri) REFERENCES Bucket(uri)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
