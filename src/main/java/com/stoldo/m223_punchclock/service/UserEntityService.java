package com.stoldo.m223_punchclock.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.stoldo.m223_punchclock.model.api.UserChangePasswordRequest;
import com.stoldo.m223_punchclock.model.api.UserInvitationRequest;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.model.enums.ErrorCode;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.model.enums.UserStatus;
import com.stoldo.m223_punchclock.model.exception.ErrorCodeException;
import com.stoldo.m223_punchclock.repository.UserEntityRepository;


@Service
public class UserEntityService {

    private UserEntityRepository userEntityRepository;
    private BCryptPasswordEncoder passwordEncoder;

    
    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository, BCryptPasswordEncoder passwordEncoder) {
    	this.userEntityRepository = userEntityRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    
	public List<UserEntity> getAll() {
		return userEntityRepository.findAll();
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
    
	public UserEntity edit(Long id, @Valid UserEntity ue) {
		UserEntity existingUser = getById(id);
		existingUser.setFirstName(ue.getFirstName());
		existingUser.setLastName(ue.getLastName());
		return saveUser(existingUser);
	}
    
	public void delete(@PathVariable Long id) {
		userEntityRepository.deleteById(id);
	}
    
   	public UserEntity changePassword(Long id, @Valid UserChangePasswordRequest ucpr) {
   		UserEntity existingUser = getById(id);
   		boolean oldMatchesNewPassword = passwordEncoder.matches(ucpr.getOldPassword(), existingUser.getPassword());
   		
   		if (oldMatchesNewPassword) {
   			existingUser.setPassword(passwordEncoder.encode(ucpr.getNewPassword()));	
   			return saveUser(existingUser);
   		}
   		
   		throw new ErrorCodeException(ErrorCode.E1002, HttpStatus.BAD_REQUEST);
   	}
    
    public UserEntity invite(@RequestBody @Valid UserInvitationRequest uir) {
    	UserEntity ue = new UserEntity();
    	ue.setEmail(uir.getEmail());
    	ue.setFirstName(uir.getFirstName());
    	ue.setLastName(uir.getLastName());
    	ue.setPassword(passwordEncoder.encode(uir.getPassword()));
		ue.setRoles(uir.getRoles());
		ue.setStatus(UserStatus.INVITED);
    	
        return saveUser(ue);
    }
    
    public boolean isAdmin(UserEntity ue) {
    	return hasRole(ue, Role.ADMIN);
    }
    
    public boolean hasRole(UserEntity ue, Role role) {
    	if (ue != null && ue.getRoles() != null) {
    		Role adminRole = ue.getRoles()
    				.stream()
    				.map(sr -> sr.getRole())
    				.filter(r -> r == Role.ADMIN)
    				.findFirst()
    				.orElse(null);
    				
    		return adminRole != null;
    	}
    	
    	return false;
    }
 }
