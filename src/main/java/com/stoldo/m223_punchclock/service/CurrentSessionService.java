package com.stoldo.m223_punchclock.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.UserEntity;


@Service
public class CurrentSessionService {
	
	private UserEntityService userEntityService;
	
	
	@Autowired
	public CurrentSessionService(UserEntityService userEntityService) {
		this.userEntityService = userEntityService;
	}
	
	public UserEntity getLoggedInUserEntity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userEntityService.getByEmail(authentication.getName());
	}
	
	public boolean isCurrentUserAdmin() {
		UserEntity loggedInUserEntity = getLoggedInUserEntity();
		return userEntityService.isAdmin(loggedInUserEntity);
	}
	
	public boolean isCurrentUserSameAsUser(Long userId) {
		UserEntity loggedInUserEntity = getLoggedInUserEntity();
		return loggedInUserEntity.getId().equals(userId);
	}
	
	public boolean isCurrentUserSameAsUser(String email) {
		UserEntity loggedInUserEntity = getLoggedInUserEntity();
		return StringUtils.equals(loggedInUserEntity.getEmail(), email);
	}
}
