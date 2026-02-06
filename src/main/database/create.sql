-- Active: 1768313575910@@127.0.0.1@5432@spr@public

CREATE DATABASE spr;
-- \c spr;

CREATE TABLE hotel(
   id VARCHAR(50),
   nom VARCHAR(50),
   PRIMARY KEY(id)
);

CREATE TABLE reservation(
   id VARCHAR(50) ,
   idclient VARCHAR(4)  NOT NULL,
   nbpassager INTEGER,
   dateheure_arrive TIMESTAMP default current_timestamp,
   idhotel VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idhotel) REFERENCES hotel(id)
);