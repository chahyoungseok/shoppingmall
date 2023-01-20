--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01042338110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'ann', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01051235871', 'ROLE_USER', 'annlab98@naver.com', 'sangmyung university')

--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'S', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'M', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'L', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'S', 'A', 2)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'M', 'A', 2)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'L', 'A', 2)

--------------- 상품 데이터 ---------------
INSERT INTO cart(id, user_id, product_id, count) values (null, 1, 2, 1)
INSERT INTO cart(id, user_id, product_id, count) values (null, 2, 4, 1)

--------------- 주문 데이터 ---------------
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 1, '2021-11-08T11:44:30.327959', '배송완료')
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 2, '2022-09-01T22:59:16.115105', '배송완료')

--------------- 주문상품 데이터 ---------------
INSERT INTO order_product(id, product_id, order_id, count) values (null, 2, 1, 1)
INSERT INTO order_product(id, product_id, order_id, count) values (null, 1, 1, 2)
INSERT INTO order_product(id, product_id, order_id, count) values (null, 4, 2, 3)

insert into banner(id, imgKey) values (null, 'A')
insert into banner(id, imgKey) values (null, 'B')
insert into banner(id, imgKey) values (null, 'C')
insert into banner(id, imgKey) values (null, 'D')