package com.stoldo.m223_punchclock.model.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.shared.util.CommonUtils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
	
	@NotNull
	@Email(regexp = CommonUtils.EMAIL_REGEXP, message = CommonUtils.EMAIL_REGEXP_MESSAGE)
	private String email;
	
	@NotNull
	private String password;

}
