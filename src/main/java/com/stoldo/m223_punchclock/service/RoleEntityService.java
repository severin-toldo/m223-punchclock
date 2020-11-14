package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.repository.RoleEntityRepository;

import java.util.List;

import javax.annotation.PostConstruct;

// TODO fix this shit
//TODO fox tthe role shit.

@Service
public class RoleEntityService {

    private RoleEntityRepository roleEntityRepository;
	
	
    @Autowired
	public RoleEntityService(RoleEntityRepository roleEntityRepository) {
		this.roleEntityRepository = roleEntityRepository;
	}
	
	@PostConstruct
	private void init() {
		for (Role role : Role.values()) {
			RoleEntity re = new RoleEntity();
			re.setRole(role);
			roleEntityRepository.saveAndFlush(re);
		}
	}

    public RoleEntity findByRole(Role role) {
        return roleEntityRepository.findByRole(role);
    }

    public List<RoleEntity> getRoles() {
        return roleEntityRepository.findAll();
    }
}
