--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01042338110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
INSERT INTO user(id, username, nickname, password, telephone, authority, e_mail, address) values (null, 'ann', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01051235871', 'ROLE_USER', 'annlab98@naver.com', 'sangmyung')

--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 1',139000,'outer','스타디움 자켓식의 가죽 점퍼','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-01T09:32',100,10,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 2',49000,'outer',' 활동성 좋은 후드집업','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ea22c5b0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-02T09:32',200,20,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 3',69000,'outer','램스울 소재의 따뜻한 가득한','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/eec43a40a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-03T09:32',300,30,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 4',59000,'outer','브라운 계열의 가디건','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f1cf0940a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-04T09:32',400,40,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 5',89000,'outer','호피 무늬의 가디건','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f6c8aff0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-05T09:32',500,50,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 6',119000,'outer','오버사이징된 블레이저','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f9d5a1d0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-06T09:32',600,60,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 7',59000,'outer','버튼형식의 플리스','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fdb76fe0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-07T09:32',700,70,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 8',239000,'outer','구스다운 형식의 롱패딩','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/01a1f080a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-08T09:32',800,80,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 9',399000,'outer','헤비 코튼 원단을 사용한 롱코트','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/051d0d80a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-09T09:32',900,90,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'OUTER 10',179000,'outer','부드러운 촉감의 패딩','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/0815b410a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-10T09:32',1000,100,1)

INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 1',48000,'pants','멋진 바지1 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-11T09:32',110,11,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 2',48000,'pants','멋진 바지2 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-12T09:32',120,12,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 3',39000,'pants','멋진 바지3 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/bd78b190a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-13T09:32',130,13,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 4',39000,'pants','멋진 바지4 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c53aee20a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-14T09:32',140,14,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 5',39000,'pants','멋진 바지5 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c9e22f10a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-15T09:32',150,15,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 6',59000,'pants','라이트 블루 색상의 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/9ca8fb50a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-16T09:32',160,16,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 7',59000,'pants','베이지 치노 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a0213220a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-17T09:32',170,17,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 8',49000,'pants','스트레이트 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a36623f0a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-18T09:32',180,18,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 9',49000,'pants','스웨트 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a5adf110a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-19T09:32',190,19,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'PANTS 10',49000,'pants','베이지 스트레이트 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a98b7960a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-20T09:32',200,20,1)

INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 1',35000,'shirts','멋진 상의1 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ce1662e0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-21T09:32',111,100,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 2',35000,'shirts','멋진 상의2 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d11ac940a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-22T09:32',222,200,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 3',35000,'shirts','멋진 상의3 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d6242760a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-23T09:32',333,300,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 4',44000,'shirts','멋진 상의4 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/e21c4570a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-24T09:32',444,800,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 5',44000,'shirts','멋진 상의5 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/edbccda0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-25T09:32',555,900,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 6',44000,'shirts','멋진 상의6 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f1c13ee0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-26T09:32',666,400,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 7',29000,'shirts','멋진 상의7 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f4716ac0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-27T09:32',777,500,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 8',39000,'shirts','멋진 상의8 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f75c2ea0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-28T09:32',888,600,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 9',39000,'shirts','멋진 상의9 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fa41c260a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-03-29T09:32',999,700,1)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id) values (null,'TOP 10',39000,'shirts','멋진 상의10 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fdecc900a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-03-30T09:32',1111,1000,1)
--------------- 카트 데이터 ---------------
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 1, 'S')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 2, 'M')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 2, 2, 1, 'M')

--------------- 주문 데이터 ---------------
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 1, '2021-11-08T11:44:30', '배송완료')
INSERT INTO orders(id, user_id, orderdate, orderstatus) values (null, 2, '2022-09-01T22:59:16', '배송완료')

--------------- 주문상품 데이터 ---------------
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 2, 1, 1, 'S')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 1, 1, 8, 'M')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 4, 2, 2, 'L')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 3, 2, 2, 'L')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 7, 2, 2, 'L')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 7, 1, 2, 'L')
INSERT INTO order_product(id, product_id, order_id, count, size) values (null, 7, 1, 2, 'S')

INSERT INTO  banner(id, imgKey) values (null, 'A')
INSERT INTO  banner(id, imgKey) values (null, 'B')
INSERT INTO  banner(id, imgKey) values (null, 'C')
INSERT INTO  banner(id, imgKey) values (null, 'D')