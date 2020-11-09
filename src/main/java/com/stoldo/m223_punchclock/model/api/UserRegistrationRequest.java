package com.stoldo.m223_punchclock.model.api;


import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.shared.util.CommonUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
	
	@NotNull
	@Email(regexp = CommonUtils.EMAIL_REGEXP, message = CommonUtils.EMAIL_REGEXP_MESSAGE)
    private String email;

	@NotNull
    private String password;
	
	private Set<RoleEntity> roles;

}
