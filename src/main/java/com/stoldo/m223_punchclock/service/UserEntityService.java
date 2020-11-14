package com.stoldo.m223_punchclock.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.stoldo.m223_punchclock.model.api.UserRegistrationRequest;
import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.repository.UserEntityRepository;

@Service
public class UserEntityService {

    private UserEntityRepository userEntityRepository;
    private BCryptPasswordEncoder passwordEncoder;

    
    public UserEntityService(UserEntityRepository userEntityRepository, BCryptPasswordEncoder passwordEncoder) {
    	this.userEntityRepository = userEntityRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    
    public UserEntity getById(Long id) {
        return userEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + "not found!"));
    }
    
    public UserEntity getByEmail(String email) {
        return userEntityRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User with email " + email + "not found!"));
    }

    public UserEntity saveUser(UserEntity ue) {
        return userEntityRepository.saveAndFlush(ue);
    }
    
    // TODO return jwt
    public UserEntity register(@Validated UserRegistrationRequest urr) {
    	UserEntity ue = new UserEntity();
    	ue.setEmail(urr.getEmail());
    	ue.setPassword(passwordEncoder.encode(urr.getPassword()));
    	
    	if (urr.getRoles() != null) {
    		List<RoleEntity> fileredRoles = urr.getRoles().stream()
    				.filter(r -> r != null)
    				.filter(r -> r.getRole() != Role.ADMIN) // security reasons
    				.collect(Collectors.toList());
    		
    		ue.setSecurityRoles(fileredRoles);
    	}
    	
        return saveUser(ue);
    }
    
    public UserEntity getLoggedInUserEntity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return getByEmail(authentication.getName());
	}
 }
