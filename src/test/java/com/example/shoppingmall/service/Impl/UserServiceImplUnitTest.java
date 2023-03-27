package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestChangePWD;
import com.example.shoppingmall.data.dto.request.RequestJoin;
import com.example.shoppingmall.data.dto.request.RequestModify;
import com.example.shoppingmall.data.dto.response.ResponseUser;
import com.example.shoppingmall.data.entity.Authority;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;

class UserServiceImplUnitTest extends BaseServiceImplUnitTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Nested
    @DisplayName("회원가입")
    class create {
        RequestJoin param;
        User user;

        create(){
            // given
            bCryptPasswordEncoder = new BCryptPasswordEncoder();

            param = new RequestJoin(
                    "seokseok",
                    "qwer1234",
                    "hs_good",
                    "010-5161-6611",
                    "hs@gmail.com",
                    "sangmyung university - 123");

            user = param.toEntity(bCryptPasswordEncoder.encode(param.getPassword()));
        }

        @Test
        @DisplayName("회원가입_성공")
        @Transactional
        void 회원가입_성공(){
            // mocking
            BDDMockito.given(userRepository.save(any()))
                            .willReturn(user);
            BDDMockito.given(userRepository.existsByUsername(any()))
                            .willReturn(false);
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);

            // when
            Boolean result = userService.create(param);

            // then
            Assertions.assertEquals(true, result);
        }

        @Test
        @DisplayName("회원가입_실패_아이디가_형식에_맞지않을때")
        @Transactional
        void 회원가입_실패_아이디가_형식에_맞지않을때(){
            // mocking
            BDDMockito.given(userRepository.existsByUsername(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);

            // when
            Boolean result = userService.create(param);

            // then
            Assertions.assertEquals(false, result);
        }

        @Test
        @DisplayName("회원가입_실패_이메일이_형식에_맞지않을때")
        @Transactional
        void 회원가입_실패_이메일이_형식에_맞지않을때(){
            // mocking
            BDDMockito.given(userRepository.existsByUsername(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);

            // when
            Boolean result = userService.create(param);

            // then
            Assertions.assertEquals(false, result);
        }

        @Test
        @DisplayName("회원가입_실패_전화번호가_형식에_맞지않을때")
        @Transactional
        void 회원가입_실패_전화번호가_형식에_맞지않을때(){
            // mocking
            BDDMockito.given(userRepository.existsByUsername(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(true);

            // when
            Boolean result = userService.create(param);

            // then
            Assertions.assertEquals(false, result);
        }
    }

    @Nested
    @DisplayName("회원정보수정")
    class update {
        RequestModify param;

        User user;

        update(){
            // given
            param = new RequestModify(
                    "hs_good",
                    "010-5161-6611",
                    "hs1@gmail.com",
                    "sangmyung university - 123");
        }

        @Test
        @DisplayName("회원정보수정_성공_전화번호와_이메일_둘다_변경한_상황일때")
        @Transactional
        void 회원정보수정_성공_전화번호와_이메일_둘다_변경한_상황일때(){
            // given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6612",
                    "hs2@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.save(any()))
                    .willReturn(user);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertEquals(ResponseUser.builder().user(user).build().toString(), result.toString());
        }

        @Test
        @DisplayName("회원정보수정_성공_전화번호는_변경하지않고_이메일은_변경한_상황일때")
        @Transactional
        void 회원정보수정_성공_전화번호는_변경하지않고_이메일은_변경한_상황일때(){
            // given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6611",
                    "hs2@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.save(any()))
                    .willReturn(user);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertEquals(ResponseUser.builder().user(user).build().toString(), result.toString());
        }

        @Test
        @DisplayName("회원정보수정_성공_전화번호는_변경하고_이메일은_변경하지않은_상황일때")
        @Transactional
        void 회원정보수정_성공_전화번호는_변경하고_이메일은_변경하지않은_상황일때(){
            // given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6612",
                    "hs1@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.save(any()))
                    .willReturn(user);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertEquals(ResponseUser.builder().user(user).build().toString(), result.toString());
        }

        @Test
        @DisplayName("회원정보수정_성공_전화번호와_이메일_둘다_변경하지않은_상황일때")
        @Transactional
        void 회원정보수정_성공_전화번호와_이메일_둘다_변경하지않은_상황일때(){
            // given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6611",
                    "hs1@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.save(any()))
                    .willReturn(user);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertEquals(ResponseUser.builder().user(user).build().toString(), result.toString());
        }

        @Test
        @DisplayName("회원정보수정_실패_전화번호가_이미있는_상태일때")
        @Transactional
        void 회원정보수정_실패_전화번호가_이미있는_상태일때(){
            //given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6619",
                    "hs1@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(false);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(true);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertNull(result);
        }

        @Test
        @DisplayName("회원정보수정_실패_이메일이_이미있는_상태일때")
        @Transactional
        void 회원정보수정_실패_이메일이_이미있는_상태일때(){
            //given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6611",
                    "hs9@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(false);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertNull(result);
        }

        @Test
        @DisplayName("회원정보수정_실패_전화번호와_이메일이_모두있는_상태일때")
        @Transactional
        void 회원정보수정_실패_전화번호와_이메일이_모두있는_상태일때(){
            //given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6619",
                    "hs9@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            // mocking
            BDDMockito.given(userRepository.existsByEmail(any()))
                    .willReturn(true);
            BDDMockito.given(userRepository.existsByTelephone(any()))
                    .willReturn(true);

            // when
            ResponseUser result = userService.updateUser(param, user);

            // then
            Assertions.assertNull(result);
        }
    }

    @Nested
    @DisplayName("회원정보삭제")
    class delete {
        String param;

        @Test
        @DisplayName("회원정보삭제_성공_삭제쿼리가_제대로_동작했을때")
        @Transactional
        void 회원정보삭제_성공_삭제쿼리가_제대로_동작했을때() {
            // given
            param = "hwang";

            // mocking
            BDDMockito.given(userRepository.existsByUsername(any()))
                    .willReturn(false);

            // when
            Boolean result = userService.deleteUser(param);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @DisplayName("회원정보삭제_성공_삭제쿼리가_제대로_동작하지않았을때")
        @Transactional
        void 회원정보삭제_성공_삭제쿼리가_제대로_동작하지않았을때() {
            // given
            param = "seokseok";

            // mocking
            BDDMockito.given(userRepository.existsByUsername(any()))
                    .willReturn(true);

            // when
            Boolean result = userService.deleteUser(param);

            // then
            Assertions.assertEquals(result, false);
        }
    }

    @Nested
    @DisplayName("비밀번호변경")
    class change_pwd {
        RequestChangePWD param;

        User user;

        change_pwd(){
            // given
            user = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6619",
                    "hs9@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );
        }

        @Test
        @DisplayName("비밀번호변경_성공_비밀번호가_일치할때")
        @Transactional
        void 비밀번호변경_성공_비밀번호가_일치할때() {
            // given
            param = new RequestChangePWD("qwer1234","qwer4321");

            // mocking
            BDDMockito.given(bCryptPasswordEncoder.matches(any(), any()))
                    .willReturn(true);

            // when
            Boolean result = userService.change_pwd(param, user);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @DisplayName("비밀번호변경_실패_비밀번호가_일치하지않을때")
        @Transactional
        void 비밀번호변경_실패_비밀번호가_일치하지않을때() {
            // given
            param = new RequestChangePWD("qwer1239","qwer4321");

            // mocking
            BDDMockito.given(bCryptPasswordEncoder.matches(any(), any()))
                    .willReturn(false);

            // when
            Boolean result = userService.change_pwd(param, user);

            // then
            Assertions.assertEquals(result, false);
        }
    }

    @Nested
    @DisplayName("권한업그레이드")
    class upgradeAuth {
        String param;

        User user_USER;

        User user_REGISTER;

        upgradeAuth(){
            // given
            param = "seokseok";

            user_USER = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6619",
                    "hs9@gmail.com",
                    "sangmyung university - 123",
                    Authority.USER
            );

            user_REGISTER = new User(
                    null,
                    "seokseok",
                    "hs_good",
                    "$2a$10$vG6NvQImCGrMOq78yQiLxuvBt/O7qF7LAH./w9h20WoXoau7EfDgq",
                    "010-5161-6619",
                    "hs9@gmail.com",
                    "sangmyung university - 123",
                    Authority.REGISTER
            );
        }

        @Test
        @DisplayName("권한업그레이드_성공")
        @Transactional
        void 권한업그레이드_성공() {
            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(user_USER);

            BDDMockito.given(userRepository.save(any()))
                    .willReturn(user_REGISTER);

            // when
            Boolean result = userService.upgradeAuth(param);

            // then
            Assertions.assertEquals(result, true);
        }

        @Test
        @DisplayName("권한업그레이드_실패_권한이_ROLE_USER가_아닐때")
        @Transactional
        void 권한업그레이드_실패_권한이_ROLE_USER가_아닐때() {
            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(user_REGISTER);

            // when
            Boolean result = userService.upgradeAuth(param);

            // then
            Assertions.assertEquals(result, false);
        }

        @Test
        @DisplayName("권한업그레이드_실패_user가_null일때")
        @Transactional
        void 권한업그레이드_실패_user가_null일때() {
            // mocking
            BDDMockito.given(userRepository.findByUsername(any()))
                    .willReturn(null);

            // when
            Boolean result = userService.upgradeAuth(param);

            // then
            Assertions.assertEquals(result, false);
        }
    }
}


