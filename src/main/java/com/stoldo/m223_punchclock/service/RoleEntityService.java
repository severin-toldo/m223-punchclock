package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.repository.RoleEntityRepository;



@Service
public class RoleEntityService {

    private RoleEntityRepository roleEntityRepository;
	
	
    @Autowired
	public RoleEntityService(RoleEntityRepository roleEntityRepository) {
		this.roleEntityRepository = roleEntityRepository;
	}

    public RoleEntity getByRole(Role role) {
        return roleEntityRepository.findByRole(role);
    }

}
