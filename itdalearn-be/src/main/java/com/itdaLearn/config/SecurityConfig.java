package com.itdaLearn.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다
    @Bean
   public BCryptPasswordEncoder encodePwd() {
       return new BCryptPasswordEncoder();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

                .mvcMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight Request 허용해주기

                .anyRequest().permitAll() // 나머지 주소는 모든 권한
                .and()
                .formLogin()
                .loginPage("/loginForm") // 로그인을 안하면 어디를 가든 로그인페이지로 이동하게 함
                .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다
                // 이렇게하면 컨트롤러에 /login을 만들지 않아도 됨
                .defaultSuccessUrl("/"); // 로그인이 완료되면..
        // 처음 페이지에서 user로 가려햇을 때 login을 하도록하는데 거기서 회원가입을 하고 로그인을 하면 내가 원했던 페이지 user페이지로 보내준다


    }
}
