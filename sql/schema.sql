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
username_owner varchar(20),
FOREIGN KEY (username_owner) REFERENCES User(username),
PRIMARY KEY(uri)
);

CREATE TABLE Metadates(
    uri varchar(20),
    metakey varchar(20),
    value varchar(20),
    FOREIGN KEY (uri) REFERENCES Bucket(uri)
);


CREATE TABLE Object(
    uri varchar(100),
	bucketUri varchar(20),
    username_owner varchar(20),
    content LONGBLOB,
    version int,
    contentLength int,
    contentType varchar(10),
    lastModified date,
    createdDate date,
    hash text,
    PRIMARY KEY(uri,version,username_owner,bucketUri),
    FOREIGN KEY(username_owner) REFERENCES User(username),
    FOREIGN KEY (bucketUri) REFERENCES Bucket(uri)
);
select * from bucket;
select * from object;
select * from user;
SELECT uri FROM Object WHERE bucketUri = 'pedro' AND uri LIKE '/carp1%';
SELECT * FROM Object WHERE bucketUri='pedro' AND uri ='Ejem';

DELETE FROM Object Where uri LIKE 'qwerty2';