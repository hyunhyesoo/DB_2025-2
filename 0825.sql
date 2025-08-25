create table 고객(
    고객아이디 VARCHAR(20) not null primary key,
    고객이름 VARCHAR(10) not null,
    나이 int,
    등급 VARCHAR(10) not null,
    직업 VARCHAR(20),
    적립금 int DEFAULT 0
);