package com.stoldo.m223_punchclock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.stoldo.m223_punchclock.model.api.UserChangePasswordRequest;
import com.stoldo.m223_punchclock.model.api.UserEditRequest;
import com.stoldo.m223_punchclock.model.api.UserInvitationRequest;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.service.CurrentSessionService;
import com.stoldo.m223_punchclock.service.UserEntityService;
import com.stoldo.m223_punchclock.shared.util.CommonUtils;


@RestController
@RequestMapping("/users")
public class UserEntityController {

    private UserEntityService userEntityService;
    private CurrentSessionService css;
	
    
    @Autowired
	public UserEntityController(UserEntityService userEntityService, CurrentSessionService css) {
		this.userEntityService = userEntityService;
		this.css = css;
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
	public List<UserEntity> getAll() {
		return userEntityService.getAll();
	}
	
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
	public UserEntity getById(@PathVariable Long id) {
    	CommonUtils.falseThenThrow(css.isCurrentUserAdmin() || css.isCurrentUserSameAsUser(id), new ResponseStatusException(HttpStatus.FORBIDDEN));
		return userEntityService.getById(id);
	}
    
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public UserEntity edit(@PathVariable Long id, @RequestBody @Valid UserEditRequest ur) {
    	CommonUtils.falseThenThrow(css.isCurrentUserAdmin() || css.isCurrentUserSameAsUser(id), new ResponseStatusException(HttpStatus.FORBIDDEN));
		return userEntityService.edit(id, ur);
	}
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
    	CommonUtils.falseThenThrow(css.isCurrentUserAdmin() || css.isCurrentUserSameAsUser(id), new ResponseStatusException(HttpStatus.FORBIDDEN));
    	userEntityService.delete(id);
	}
    
    @RequestMapping(value = "{id}/password", method = RequestMethod.PUT)
   	public UserEntity changePassword(@PathVariable Long id, @RequestBody @Valid UserChangePasswordRequest ucpr) {
       	CommonUtils.falseThenThrow(css.isCurrentUserSameAsUser(id), new ResponseStatusException(HttpStatus.FORBIDDEN));
   		return userEntityService.changePassword(id, ucpr);
   	}
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "invite", method = RequestMethod.POST)
    public UserEntity invite(@RequestBody @Valid UserInvitationRequest uir) {
    	return userEntityService.invite(uir);
    }
}
