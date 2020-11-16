package com.stoldo.m223_punchclock.model.api;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@Getter
@Setter
public class UserChangePasswordRequest {
	
	@NotNull
	private String oldPassword;
	
	@NotNull
	private String newPassword;

}
