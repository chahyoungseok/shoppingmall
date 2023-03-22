package com.example.shoppingmall.repository.user;

import com.example.shoppingmall.config.TestConfig;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;

@DataJpaTest
@Import(TestConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("유저네임이_DB에_존재하는지_여부")
    class existsByUsername {
        String param;

        existsByUsername() {
            // given
            param = "hwang";
        }

        @Test
        @Transactional
        @DisplayName("유저네임이_DB에_존재하는지_여부_성공_존재할때")
        void 유저네임이_DB에_존재하는지_여부_성공_존재할때(){
            // given
            userRepository.save(returnUser());

            // when
            Boolean result = userRepository.existsByUsername(param);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @Transactional
        @DisplayName("유저네임이_DB에_존재하는지_여부_실패_존재하지않을때")
        void 유저네임이_DB에_존재하는지_여부_실패_존재하지않을때(){
            // when
            Boolean result = userRepository.existsByUsername(param);

            // then
            Assertions.assertEquals(result, false);
        }
    }

    @Nested
    @DisplayName("전화번호가_DB에_존재하는지_여부")
    class existsByTelephone {
        String param;

        existsByTelephone() {
            // given
            param = "010-5161-6612";
        }

        @Test
        @Transactional
        @DisplayName("전화번호가_DB에_존재하는지_여부_성공_존재할때")
        void 전화번호가_DB에_존재하는지_여부_성공_존재할때(){
            //given
            userRepository.save(returnUser());

            // when
            Boolean result = userRepository.existsByTelephone(param);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @Transactional
        @DisplayName("전화번호가_DB에_존재하는지_여부_실패_존재하지않을때")
        void 전화번호가_DB에_존재하는지_여부_실패_존재하지않을때(){
            // when
            Boolean result = userRepository.existsByUsername(param);

            // then
            Assertions.assertEquals(result, false);
        }
    }

    @Nested
    @DisplayName("이메일이_DB에_존재하는지_여부")
    class existsByEmail {
        String param;

        existsByEmail() {
            // given
            param = "hs@gmail.com";
        }

        @Test
        @Transactional
        @DisplayName("이메일이_DB에_존재하는지_여부_성공_존재할때")
        void 이메일이_DB에_존재하는지_여부_성공_존재할때(){
            //given
            userRepository.save(returnUser());

            // when
            Boolean result = userRepository.existsByEmail(param);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @Transactional
        @DisplayName("이메일이_DB에_존재하는지_여부_실패_존재하지않을때")
        void 이메일이_DB에_존재하는지_여부_실패_존재하지않을때(){
            // when
            Boolean result = userRepository.existsByEmail(param);

            // then
            Assertions.assertEquals(result, false);
        }
    }

    @Nested
    @DisplayName("유저삭제")
    class deleteByUser {

        @Test
        @Transactional
        @DisplayName("유저삭제_성공")
        void 유저삭제_성공(){
            //given
            User param = returnUser();
            userRepository.save(param);

            // when
            userRepository.deleteByUser(param.getUsername());
            User result = userRepository.findByUsername(param.getUsername());

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("닉네임으로_유저찾기")
    class findByUsername {

        @Test
        @Transactional
        @DisplayName("닉네임으로_유저찾기_성공")
        void 닉네임으로_유저찾기_성공(){
            //given
            User param = returnUser();
            userRepository.save(param);

            // when
            User result = userRepository.findByUsername(param.getUsername());

            // then
            Assertions.assertEquals(param.toString(), result.toString());
        }

        @Test
        @Transactional
        @DisplayName("닉네임으로_유저찾기_실패_원하는_유저정보가_DB에_없을때")
        void 닉네임으로_유저찾기_실패(){
            //given
            User param = returnUser();

            // when
            User result = userRepository.findByUsername(param.getUsername());

            // then
            Assertions.assertNull(result);
        }
    }

    private User returnUser() {
        return new User(
                null,
                "hwang",
                "hs_good",
                "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                "010-5161-6612",
                "hs@gmail.com",
                "sangmyung university - 123",
                Authority.USER
        );
    }
}