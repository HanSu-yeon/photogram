package com.cos.photogramstart.web;






import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller //1. IoC에 등록이 됐다는 의미 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	

	private final AuthService authService;
	
	//로그인 페이지로 이동
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	//회원가입 페이지로 이동
	@GetMapping("/auth/signup")  //회원가입 폼
	public String signupForm() { 
		return "auth/signup"; 
	}
	
	//회원가입 버튼 클릭 -> /auth/signup -> auth/signin으로 리턴
	//회원가입 기능
	@PostMapping("/auth/signup") //회원가입 진행
	public String signup(@Valid SignupRequestDto signupDto, BindingResult bindingResult) { //form으로 데이터로 날라오면 key=value 형식으로 들어옴 --> x-www-form-urlencoded 방식
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		}else {
			//User <- SignupDto 
			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			
			return "auth/signin"; //회원가입 성공시 이동할 페이지
		}
		
		
	}	


}
