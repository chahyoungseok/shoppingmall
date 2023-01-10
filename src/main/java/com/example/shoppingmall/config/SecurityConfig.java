package com.example.shoppingmall.config;

import com.example.shoppingmall.config.jwt.JwtAuthenticationFilter;
import com.example.shoppingmall.config.jwt.JwtAuthorizationFilter;
import com.example.shoppingmall.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않아 Stateless로 만들겠다.
                .and()
                .formLogin().disable() // jwt를 사용하므로 로그인을 안한다.
                .httpBasic().disable() // http header에 Anthorization : {ID, PW}를 담아 보낸다. 하지만 암호화가 안되있기 때문에 보안상 너무 위험.
                .apply(new MyCustomDsl()) // FilterChain으로 사용해야하므로 addFilter대신 apply사용 또한 authenticationManager를 사용해야하기 때문에
                // 아래와같이 Custom으로 클래스를 만들어 사용
                // beraer 방식 : 위의 Basic 방식과 다른 방식으로 Anthorization에 토큰을 담으므로 노출이 되도 위험부담이 적은 방식입니다.
                .and()
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_REGISTER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/register/**")
                .access("hasRole('ROLE_REGISTER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter()) // cors 요청처리 // @CrossOrigin(인증 x), 시큐리티 필터에 등록 인증(o)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }
}
