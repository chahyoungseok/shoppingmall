--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01042338110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'ann', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01051235871', 'ROLE_USER', 'annlab98@naver.com', 'sangmyung')

--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'S,M,L', 'https://hyuksmallbucket.s3.ap-northeast-2.amazonaws.com/padding1.jpg', '2020-01-08T11:44:30', 100, 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'S,M,L', 'https://hyuksmallbucket.s3.ap-northeast-2.amazonaws.com/coat1.jpg', '2019-04-08T11:44:30', 400, 2)

--------------- 카트 데이터 ---------------
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 1, "S")
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 2, "M")
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 2, 2, 1, "M")

--------------- 주문 데이터 ---------------
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 1, '2021-11-08T11:44:30', '배송완료')
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 2, '2022-09-01T22:59:16', '배송완료')

--------------- 주문상품 데이터 ---------------
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 2, 1, 1, "S")
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 1, 1, 1, "M")
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 1, 2, 2, "L")

insert into banner(id, imgKey) values (null, 'A')
insert into banner(id, imgKey) values (null, 'B')
insert into banner(id, imgKey) values (null, 'C')
insert into banner(id, imgKey) values (null, 'D')