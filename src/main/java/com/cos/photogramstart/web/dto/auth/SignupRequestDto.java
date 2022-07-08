package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data //Getter, Setter, toString ...
public class SignupRequestDto {
	
	@Size(min=2, max=20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder() //User객체가 만들어짐
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
	
}
