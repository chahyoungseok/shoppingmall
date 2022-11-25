--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail) values (null, 'hwang', 'admin', 'asdf1234', '010-4233-8110', 'ROLE_ADMIN', 'weihyuk39@naver.com')
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail) values (null, 'cha', 'admin', '123123', '010-4233-8110', 'ROLE_ADMIN', 'weihyuk39@naver.com')
--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, user_id) values (null, 'coffee', '1000', 1)
INSERT INTO product(id, name, price, user_id) values (null, 'book', '8000', 1)
