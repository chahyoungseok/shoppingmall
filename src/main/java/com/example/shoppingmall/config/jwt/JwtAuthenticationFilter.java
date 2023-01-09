package com.example.shoppingmall.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.shoppingmall.config.auth.PrincipalDetails;
import com.example.shoppingmall.data.dto.request.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음.
// /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작을 함.
// formLogin().disable()에 의해 위의 과정이 막힘.
// 동작을 원한다면 아래의 필터를 SecurityFilter에 등록해줘야함.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication : 로그인 시도함.");

        ObjectMapper om = new ObjectMapper();
        RequestLogin loginRequestDto = null;

        // 1. username, password 받아서
        try {
            loginRequestDto = om.readValue(request.getInputStream(), RequestLogin.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 알아서 @Bean에 등록시킨 BCryptPasswordEncoder를 찾아서 암호화를 거침
        // 클라이언트로 부터 온 request를 이용해 토큰을 만들고
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        System.out.println("authenticationToken : " + authenticationToken);
        // 2. 정상인지 로그인 시도. authenticationManager로 로그인 시도하면 PrincipalDetailsService가 호출.
        // 토큰을 이용해 PrincipalDetailsService의 loadUserByUsername() 함수를 실행시켜
        // DB에 있는 username과 password가 일치한다.
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 3. PrincipalDetails를 세션에 담고 (권한관리를 하기위함)
        // authentication 객체가 session 영역에 저장됨.
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        // 4. jwt 토큰을 만들어서 응답해준다.
        // 리턴의 이유는 권한관리를 security가 대신 해주기 때문에 편하려고 하는것.
        // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유는 없습니다. 근데 단지 권한 처리때문에 session 넣어 줍니다.
        return authentication; // 세션에 저장이됨.
    }


    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 jwt 토큰을 response 해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행 : 정상적으로 로그인 됨.");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // HMAC512 방식
        String jwtToken = JWT.create()
                .withSubject("cos 토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET)); // SECRET은 내서버의 고유한 값

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
