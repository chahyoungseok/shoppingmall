--------------- 유저 데이터 ---------------
INSERT INTO user(id, username, nickname, password, telephone, authority, email, address) values (null, 'hwang', 'admin', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01042338110', 'ROLE_ADMIN', 'weihyuk39@naver.com', 'sangmyung university')
INSERT INTO user(id, username, nickname, password, telephone, authority, email, address) values (null, 'annann', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01051235871', 'ROLE_USER', 'annlab98@naver.com', 'sangmyung')
INSERT INTO user(id, username, nickname, password, telephone, authority, email, address) values (null, 'jinjin', 'user', '$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq', '01026223326', 'ROLE_REGISTER', 'pool5468@naver.com', 'sangmyung go')

--------------- 상품 데이터 ---------------
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'리사이클 오버사이즈 후디드 스웨트 집업',49000,'outer',' 활동성 좋은 후드집업','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ea22c5b0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-02T03:32',46,26,3, 40)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'모헤어 가디건 [틸그린]',109900,'outer','램스울 소재의 따뜻한 가득한','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/eec43a40a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-03T09:32',512,35,3, 50)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'모헤어 가디건 [텐저린]',109900,'outer','브라운 계열의 가디건','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f1cf0940a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-03T09:33',89,78,3, 11)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'모헤어 가디건 [레오파드]',119900,'outer','호피 무늬의 가디건','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f6c8aff0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-03T09:34',75,50,3, 16)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'올 블렌드 멜톤 대디 숄더 오버사이즈 블레이저',139900,'outer','오버사이징된 블레이저','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f9d5a1d0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-06T08:32',27,32,3, 42)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'칼라리스 폴라 플리스 재킷',59000,'outer','버튼형식의 플리스','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fdb76fe0a2ae-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-07T14:32',71,64,3, 20)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'몬스터 다운 파카',239000,'outer','구스다운 형식의 롱패딩','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/01a1f080a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-08T22:32',279,36,3, 20)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'캐시미어 블렌드 오버사이즈 핸드메이드 세미 더블 로브 코트',399000,'outer','헤비 코튼 원단을 사용한 롱코트','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/051d0d80a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-09T05:32',87,64,3, 26)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'몬스터 다운 숏 패딩 재킷',279000,'outer','부드러운 촉감의 패딩','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/0815b410a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-10T09:32',413,79,3, 27)

INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'리얼 와이드 히든 밴딩 슬랙스',43900,'pants','멋진 바지1 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ba77a690a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-11T01:32',642,19,3, 20)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'와이드 데님 팬츠 [라이트 인디고]',49900,'pants','멋진 바지2 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c1574b50a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-12T03:32',76,22,3, 14)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'스웨트 팬츠 [멜란지 그레이]',31900,'pants','멋진 바지3 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/bd78b190a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-13T08:12',81,42,3, 22)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'라이트웨이트 세미 와이드 히든 밴딩 슬랙스 [블랙]',39900,'pants','멋진 바지4 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c53aee20a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-14T12:42',140,94,3, 16)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'슬림 크롭 데님 팬츠 [라이트 인디고]',51900,'pants','멋진 바지5 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/c9e22f10a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-15T15:52',79,35,3, 41)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'코튼 린넨 테이퍼드 이지 팬츠 [라이트 그레이]',31900,'pants','라이트 블루 색상의 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/9ca8fb50a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-16T03:22',56,62,3, 44)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'테이퍼드 히든 밴딩 크롭 슬랙스 [더스티 베이지]',39900,'pants','베이지 치노 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a0213220a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-17T02:31',170,43,3, 21)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'레귤러 핏 치노 팬츠 [라이트 그레이]',32900,'pants','스트레이트 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a36623f0a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-18T08:30',97,28,3, 20)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'기모 스웨트 팬츠 [미디엄 그레이]',37900,'pants','스웨트 팬츠','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/a5adf110a2af-11ed-bad0-9d4f844a9fa0.jpeg','2023-02-19T14:12',51,24,3, 40)


INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'오버사이즈 칼라드 스웨트셔츠 [피스타치오]',43900,'top','멋진 상의1 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/ce1662e0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-21T09:32',46,16,3, 40)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'메리노 울 블렌드 하이게이지 칼라드 니트',53900,'top','멋진 상의2 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d11ac940a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-22T09:32',76,11,3, 50)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'미니멀 크루 넥 니트',69900,'top','멋진 상의3 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/d6242760a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-23T09:32',127,34,3, 60)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'쉐기 독 크루 넥 니트',79900,'top','멋진 상의4 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/e21c4570a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-24T09:32',56,52,3, 20)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'플랫 테리 오버사이즈 스웨트셔츠',38900,'top','멋진 상의5 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/edbccda0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-25T09:32',78,18,3, 89)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'알파카 리브드 크루 넥 니트',79900,'top','멋진 상의6 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f1c13ee0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-26T09:32',54,16,3, 11)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'플랫 테리 오버사이즈 스웨트셔츠 [마룬]',38900,'top','멋진 상의7 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f4716ac0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-27T09:32',738,23,3, 124)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'플랫 테리 오버사이즈 스웨트셔츠 [졸리 그린]',38900,'top','멋진 상의8 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/f75c2ea0a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-27T09:33',453,25,3, 44)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'플랫 테리 오버사이즈 후디드 스웨트 셔츠 [졸리 그린]',47900,'top','멋진 상의9 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fa41c260a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-27T09:42',786,65,3, 55)
INSERT INTO product(id, name, price, category, description, size, imgKey, date, hits, favorite, user_id, stock) values (null,'플랫 테리 오버사이즈 후디드 스웨트 셔츠 [블루]',47900,'top','멋진 상의10 입니다.','S,M,L','https://mallimageupload.s3.ap-northeast-2.amazonaws.com/image/fdecc900a2b4-11ed-96f9-4f762f37fb59.jpeg','2023-02-27T09:43',123,35,3, 61)
--------------- 카트 데이터 ---------------
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 1, 'S')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 1, 2, 2, 'M')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 2, 2, 1, 'M')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 3, 1, 2, 'M')
INSERT INTO cart(id, user_id, product_id, count, size) values (null, 3, 3, 1, 'M')

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

INSERT INTO  banner(id, imgKey) values (null, 'https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_1.jpg')
INSERT INTO  banner(id, imgKey) values (null, 'https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_2.jpg')
INSERT INTO  banner(id, imgKey) values (null, 'https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_3.jpg')
INSERT INTO  banner(id, imgKey) values (null, 'https://mallimageupload.s3.ap-northeast-2.amazonaws.com/banner/carousel_sample_4.jpg')