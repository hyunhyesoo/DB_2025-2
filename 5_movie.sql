CREATE TABLE 회원 (
    아이디      VARCHAR2(20) PRIMARY KEY,
    비밀번호    VARCHAR2(20) NOT NULL,
    이름        VARCHAR2(20) NOT NULL,
    전화번호    VARCHAR2(20) NOT NULL,
    등급        VARCHAR2(10) DEFAULT 'basic' NOT NULL
                 CHECK (등급 IN ('basic','gold','vip')),
    적립금      INT          DEFAULT 0 NOT NULL
                 CHECK (적립금 >= 0),
    권한        VARCHAR2(10) DEFAULT 'user' NOT NULL
                 CHECK (권한 IN ('user','admin'))
);


CREATE TABLE 영화 (
    영화번호    VARCHAR2(20) PRIMARY KEY,
    영화제목    VARCHAR2(30) NOT NULL,
    상영시간    INT          NOT NULL
                 CHECK (상영시간 >= 0),
    장르        VARCHAR2(20) NOT NULL,
    관람등급    VARCHAR2(10) DEFAULT '15세' NOT NULL
                 CHECK (관람등급 IN ('전체','12세','15세','19세'))
);

CREATE TABLE 상영관 (
    상영관번호  VARCHAR2(20) PRIMARY KEY,
    총좌석수    INT          NOT NULL
                 CHECK (총좌석수 >= 0)
);


CREATE TABLE 좌석 (
    좌석번호    VARCHAR2(10) PRIMARY KEY,
    상영관번호  VARCHAR2(20) NOT NULL
                 REFERENCES 상영관(상영관번호)
);


CREATE TABLE 상영정보 (
    상영번호    VARCHAR2(20) PRIMARY KEY,
    상영일시    DATE         NOT NULL,
    상영관번호  VARCHAR2(20) NOT NULL
                 REFERENCES 상영관(상영관번호),
    영화번호    VARCHAR2(20) NOT NULL
                 REFERENCES 영화(영화번호)
);


CREATE TABLE 예매 (
    예매번호    VARCHAR2(20) PRIMARY KEY,
    예매일시    DATE         NOT NULL,
    결제금액    INT          NOT NULL
                 CHECK (결제금액 >= 0),
    좌석번호    VARCHAR2(10) NOT NULL
                 REFERENCES 좌석(좌석번호),
    아이디      VARCHAR2(20) NOT NULL
                 REFERENCES 회원(아이디),
    상영번호    VARCHAR2(20) NOT NULL
                 REFERENCES 상영정보(상영번호)
);


CREATE TABLE 리뷰 (
    글번호      VARCHAR2(20) PRIMARY KEY,
    글제목      VARCHAR2(20)  NOT NULL,
    글내용      VARCHAR2(200) NOT NULL,
    작성일시    DATE          NOT NULL,
    평점        INT           NOT NULL
                 CHECK (평점 BETWEEN 1 AND 5),
    아이디      VARCHAR2(20)  NOT NULL
                 REFERENCES 회원(아이디),
    영화번호    VARCHAR2(20)  NOT NULL
                 REFERENCES 영화(영화번호)
);


-- 회원
INSERT INTO 회원 VALUES ('hhs01','1234','현혜수','010-1111-2222','basic',0,'user');
INSERT INTO 회원 VALUES ('kjy01','5678','김재영','010-3333-4444','basic',500,'user');

-- 영화
INSERT INTO 영화 VALUES ('a001','주토피아',110,'animation','전체');
INSERT INTO 영화 VALUES ('a002','주토피아2',108,'animation','전체');
INSERT INTO 영화 VALUES ('a003','나우유씨미3',112,'action','12세');

-- 상영관
INSERT INTO 상영관 VALUES ('T01', 100);
INSERT INTO 상영관 VALUES ('T02', 130);

-- 좌석
INSERT INTO 좌석 VALUES ('T01-A1','T01');
INSERT INTO 좌석 VALUES ('T01-A2','T01');
INSERT INTO 좌석 VALUES ('T01-B1','T01');
INSERT INTO 좌석 VALUES ('T01-B2','T01');
INSERT INTO 좌석 VALUES ('T02-A1','T02');
INSERT INTO 좌석 VALUES ('T02-A2','T02');
INSERT INTO 좌석 VALUES ('T02-B1','T02');
INSERT INTO 좌석 VALUES ('T02-B2','T02');

-- 상영정보
INSERT INTO 상영정보 VALUES ('S001', TO_DATE('2025-12-01 19:00','YYYY-MM-DD HH24:MI'),'T01','a001');
INSERT INTO 상영정보 VALUES ('S002', TO_DATE('2025-12-02 11:00','YYYY-MM-DD HH24:MI'),'T01','a003');
INSERT INTO 상영정보 VALUES ('S003', TO_DATE('2025-12-02 11:30','YYYY-MM-DD HH24:MI'),'T02','a002');

-- 예매
INSERT INTO 예매 VALUES ('R001', SYSDATE, 12000,'T01-A1','hhs01','S001');
INSERT INTO 예매 VALUES ('R002', SYSDATE, 12000,'T01-A2','kjy01','S001');
INSERT INTO 예매 VALUES ('R003', SYSDATE, 12000,'T02-B2','kjy01','S003');

-- 리뷰
INSERT INTO 리뷰 VALUES ('RV001','재밌다','닉이랑 주디 너무 귀엽다',SYSDATE,5,'hhs01','a001');
INSERT INTO 리뷰 VALUES ('RV002','꿀잼','속편 기대돼요',SYSDATE,5,'hhs01','a003');


ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI';
select * FROM 리뷰;
