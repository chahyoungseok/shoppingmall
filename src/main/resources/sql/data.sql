--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '010-4233-8110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, user_id) values (null, 'coffee', '1000', 1)
INSERT INTO product(id, name, price, user_id) values (null, 'book', '8000', 1)
