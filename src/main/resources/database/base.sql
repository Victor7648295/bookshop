CREATE TABLE IF NOT EXISTS users (
  user_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(255)   NOT NULL,
   password VARCHAR(255)   NOT NULL,
   token VARCHAR(255)  ,
  age INTEGER ,
  name VARCHAR(50)   NOT NULL,
   email VARCHAR(255)  NOT NULL,
  address    VARCHAR(255) NOT NULL,
  balance     INTEGER
);
CREATE TABLE IF NOT EXISTS books (
id_book      BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR (100)NOT NULL,
author VARCHAR (50) NOT NULL,
book_genre ENUM('SCIENCE','DETECTIVE','FANTASY','NOVEL'),
price  BIGINT NOT NULL,
raiting  ENUM('noStar','oneStar','twoStar','threeStar','fourStar','fiveStar')
);

CREATE TABLE IF NOT EXISTS basket(
id_basket BIGINT AUTO_INCREMENT PRIMARY KEY ,
id_book BIGINT NOT NULL ,
total_cost BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS orders (
id_order    BIGINT AUTO_INCREMENT PRIMARY KEY,
user_id     BIGINT NOT NULL ,
basket_id     BIGINT NOT NULL,
phone_user    VARCHAR(255) NOT NULL,
total_price  BIGINT
status  ENUM('formed','sent')
);