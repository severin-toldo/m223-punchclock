package com.stoldo.m223_punchclock.model.api;

import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.validation.email.Email;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserLoginRequest {
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String password;

}
