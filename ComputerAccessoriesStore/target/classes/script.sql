create table Account(
idUser SERIAL primary key,
first_name VARCHAR(50) NOT NULL ,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
password VARCHAR(200) NOT NULL,
created_at DATE NOT NULL ,
role VARCHAR(50) NOT NULL
);
create table product(
  idProduct SERIAL PRIMARY KEY ,
  product_cost FLOAT NOT NULL ,
  product_name VARCHAR(50) NOT NULL ,
  idSeller INT NOT NULL ,
  FOREIGN KEY (idSeller) references Account(idUser) ON DELETE CASCADE ON UPDATE CASCADE
);

create table act(
    idAct SERIAL PRIMARY KEY ,
    count INT NOT NULL,
    idSeller INT NOT NULL,
    idBuyer INT NOT NULL,
    idProduct INT NOT NULL,
    created_at DATE NOT NULL,
    FOREIGN KEY (idSeller) REFERENCES Account(idUser) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (idBuyer) REFERENCES Account(idUser) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (idProduct) REFERENCES product(idProduct) ON UPDATE CASCADE ON UPDATE CASCADE
);

create table comment(
    idComment SERIAL PRIMARY KEY ,
    message VARCHAR NOT NULL,
    rating INT NOT NULL ,
    created_at timestamp NOT NULL,
    idSeller INT NOT NULL,
    idBuyer INT NOT NULL,
    FOREIGN KEY (idSeller) REFERENCES Account(idUser) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (idBuyer) REFERENCES Account(idUser) ON DELETE CASCADE ON UPDATE CASCADE
);

create table credit_card(
  idCard SERIAL PRIMARY KEY ,
  first_name VARCHAR(50) NOT NULL ,
  last_name VARCHAR(50) NOT NULL,
  cvv VARCHAR(5) NOT NULL ,
  month_year VARCHAR(10) NOT NULL ,
  card_code FLOAT NOT NULL ,
  balance FLOAT NOT NULL DEFAULT (0),
  idBuyer INT NOT NULL,
  FOREIGN KEY (idBuyer) REFERENCES Account(idUser) ON DELETE CASCADE
);


insert into Account (first_name, last_name, email, username, password, created_at, role)
VALUES ('Maks','Titok','admin@mail.ru', 'admin','admin','2020-12-02','ROLE_ADMIN') ;

insert into Account (first_name, last_name, email, username, password, created_at, role)
VALUES ('Rita','Titok','rita@mail.ru', 'rita','rita','2020-12-02','ROLE_BUYER') ;

insert into Account (first_name, last_name, email, username, password, created_at, role)
VALUES ('Miha','Pashkevich','miha@mail.ru', 'miha','miha','2020-12-02','ROLE_SELLER') ;

insert into Account (first_name, last_name, email, username, password, created_at, role)
VALUES ('Vlad','Praskov','Praskov@mail.ru', 'qwerty','qwerty','2020-12-02','ROLE_BLOCKED');

insert into Account (first_name, last_name, email, username, password, created_at, role)
VALUES ('Inessa','Khlud','Inkhl369@mail.ru', 'inessa','inessa','2020-04-15','ROLE_BUYER');

insert into product(product_cost, product_name, idSeller)
 VALUES (21.56,'Мышь logitech',3);

insert into product(product_cost, product_name, idSeller)
VALUES (115.63,'Монитор Acer',3);

insert into product(product_cost, product_name, idSeller)
VALUES (75.90,'Механическая клавиатура HP',3);

insert into product(product_cost, product_name, idSeller)
VALUES (259.99,'SSD 256ГБ Samsung',3);

insert into act(count,idSeller, idBuyer, idProduct, created_at)
 VALUES (10,1,2,1,'03-12-2020');

insert into act(count,idSeller, idBuyer, idProduct, created_at)
VALUES (5,2,1,2,'03-12-2020');

insert into comment(message, rating, idSeller, idBuyer, created_at)
VALUES ('Балдежный продавец',5,3,2, '09-01-2020 04:05:06');

insert into comment(message, rating, idSeller, idBuyer, created_at)
VALUES ('Брала мышь для себя, настолько хорошая, что взяла и маме!',5,3,2,'09-01-2020 04:05:06');

insert into credit_card(FIRST_NAME, LAST_NAME, CVV, MONTH_YEAR, CARD_CODE, IDBUYER, balance)
VALUES ('RITA','PROKHOZHAYA','123','12/23',1234567890123456,2,100);

insert into credit_card(FIRST_NAME, LAST_NAME, CVV, MONTH_YEAR, CARD_CODE, IDBUYER, balance)
VALUES ('MISHA','PASHKEVICH','312','12/23',1548121839498494,3,34);

insert into credit_card(FIRST_NAME, LAST_NAME, CVV, MONTH_YEAR, CARD_CODE, IDBUYER, balance)
VALUES ('ADMIN','ADMIN','000','01/24',0101010101010101,1,500);

drop table account, product, act, comment, credit_card;
drop table credit_card;