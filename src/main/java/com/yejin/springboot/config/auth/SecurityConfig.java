package com.yejin.springboot.config.auth;

import com.yejin.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()  //h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests()  //url 별 권한 관리를 설정하는 옵셥의 시작점
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  //권한 관리 대상 지정하는 옵션, 전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  //USER 권한을 가진 사람만 열람 권한
                    .anyRequest().authenticated()  //설정된 값들 외 나머지 url, 모두 인증된 사용자들에게만 허용(로그인한 사용자)
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  //로그아웃 성공 시 이동할 주소
                .and()
                    .oauth2Login()  //로그인 기능에 대한 설정 진입점
                        .userInfoEndpoint()  //로그인 성공 후 사용자 정보 가져올 때 설정들
                            .userService(customOAuth2UserService);  //소셜 로그인 성공 시 진행할 USerService 인터페이스의 구현체 등록
    }
}
