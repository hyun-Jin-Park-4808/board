package com.example.board.config;

import com.example.board.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration // Bean 등록
@EnableWebSecurity // Security filter에 추가하기 위함.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationFailureHandler customFailureHandler;
    private final PrincipalDetailService principalDetailService;

    private static final String[] PERMIT_URL_ARRAY = {
            "/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**",
            "/h2-console/**"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // 부모의 생성자 메서드를 호출
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); // 패스워드를 평문으로 넘겨받고, 암호화해주거나, 비밀번호 값을 비교할 때 사용
    }

    // 시큐리티가 대신 로그인 해 주는데 비밀번호 해쉬 비교를 위한 로직
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin(); // h2-db를 웹 콘솔로 접근하기 위한 설정
        http.csrf().disable() // csrf token disable => form tag를 이용해 요청이 아닌 ajax call을 하면 토큰이 없어서 block 당함
                .authorizeRequests() // 특정 경로에 권한을 가진 사용자만 접근 가능
                .antMatchers(PERMIT_URL_ARRAY) // 패턴
                .permitAll() // 허용
                .anyRequest() // 이외 모든 요청
                .authenticated() // 인증 필요
                .and()
                .formLogin() // 로그인 폼 사용
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 해당 url로 프로세스가 처리된다.
                .defaultSuccessUrl("/") // 로그인 성공시, "/" url 페이지로 보내준다.
                .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                .and()
                .logout() // logout 누르면 logout 메서드 동작
                .and()
                .userDetailsService(principalDetailService); // userDetailService로 커스텀한 서비스를 사용하겠다.
        // intercept by spring security
    }
}
