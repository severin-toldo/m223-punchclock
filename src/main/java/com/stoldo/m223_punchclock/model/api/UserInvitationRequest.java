package com.stoldo.m223_punchclock.model.api;


import java.util.List;

import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.model.validation.email.Email;

import lombok.Getter;
import lombok.Setter;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
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
