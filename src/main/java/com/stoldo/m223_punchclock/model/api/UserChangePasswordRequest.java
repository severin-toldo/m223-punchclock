package com.stoldo.m223_punchclock.model.api;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserChangePasswordRequest {
	
	@NotNull
	private String oldPassword;
	
	@NotNull
	private String newPassword;

}
