package com.stoldo.m223_punchclock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.api.UserRegistrationRequest;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.service.UserEntityService;


@RestController
@RequestMapping("/api/users")
public class UserEntityRestService {

    private UserEntityService userEntityService;
	
    
	public UserEntityRestService(UserEntityService userEntityService) {
		this.userEntityService = userEntityService;
	}
	
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public UserEntity register(@RequestBody @Validated UserRegistrationRequest urr) {
    	return userEntityService.register(urr);
    }
    
    // TODO get by email and remove pre authorize and use isrequestersame or admin -> see pawpals
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "id/{userId}", method = RequestMethod.GET)
	public UserEntity getById(@PathVariable Long userId) {
		return userEntityService.getById(userId);
	}
}
