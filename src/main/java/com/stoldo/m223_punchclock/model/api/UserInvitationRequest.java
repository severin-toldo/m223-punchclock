package com.stoldo.m223_punchclock.model.api;


import java.util.List;

import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.model.validation.email.Email;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserInvitationRequest {
	
	@Email
	@NotNull
    private String email;

	@NotNull
    private String password;
	
	@NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
	
	private List<Role> roles;

}
