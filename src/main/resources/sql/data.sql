--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '010-4233-8110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'ann', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '010-5123-5871', 'ROLE_USER', 'annlab98@naver.com', 'sangmyung university')

--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'S', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'M', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'padding', 1000, 'outer', '패딩이다', 'L', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'S', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'M', 'A', 1)
INSERT INTO product(id, name, price, category, description, size, imgKey, user_id) values (null, 'coat', 1000, 'outer', '코트이다', 'L', 'A', 1)

--------------- 상품 데이터 ---------------
INSERT INTO cart(id, user_id, product_id, count) values (null, 1, 2, 1)
INSERT INTO cart(id, user_id, product_id, count) values (null, 1, 4, 1)
