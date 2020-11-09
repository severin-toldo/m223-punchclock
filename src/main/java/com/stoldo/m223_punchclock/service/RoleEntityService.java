package com.stoldo.m223_punchclock.service;

import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.repository.RoleEntityRepository;

import java.util.List;


@Service
public class RoleEntityService {

    private RoleEntityRepository roleEntityRepository;
	
	
	public RoleEntityService(RoleEntityRepository roleEntityRepository) {
		this.roleEntityRepository = roleEntityRepository;
	}

    public RoleEntity getByName(String name) {
        return roleEntityRepository.findByName(name);
    }

    public List<RoleEntity> getRoles() {
        return roleEntityRepository.findAll();
    }
}
