package com.cos.photogramstart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//security 설정파일이 되려면 websecurity를 상속해줘야함. 그리고configuration해서 ioc에 등록
@EnableWebSecurity //해당 파일로 시큐리티를 활성화 (기존 login페이지가 아닌) 2)
@Configuration //IoC  1)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http); //3)얘가 가로잰다 -->지워줌 --> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
		//이제 우리가 낚아챌수 있게 주소 설정을 해주자 --> 인증이 안된사용자는 모두 login페이지로 
		http.csrf().disable(); //csrf 비활성화
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**","/subscribe/**","/comment/**").authenticated() //인증이 필요하다 이 주소로 들어오면 /auth/signin로 리다이렉트
			.anyRequest().permitAll() //나머지 주소는 모두 허용
			.and()
			.formLogin() 
			.loginPage("/auth/signin") //로그인 페이지
			.defaultSuccessUrl("/"); //로그인 정상적으로 처리하면 /로 가라
	}
}
