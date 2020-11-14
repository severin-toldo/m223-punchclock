package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.repository.RoleEntityRepository;

import java.util.Arrays;

import javax.annotation.PostConstruct;


@Service
public class RoleEntityService {

    private RoleEntityRepository roleEntityRepository;
	
	
    @Autowired
	public RoleEntityService(RoleEntityRepository roleEntityRepository) {
		this.roleEntityRepository = roleEntityRepository;
	}
	
	@PostConstruct
	private void init() {
		Arrays.asList(Role.values()).forEach(role -> {
			RoleEntity re = new RoleEntity();
			re.setRole(role);
			roleEntityRepository.saveAndFlush(re);	
		});
	}

    public RoleEntity findByRole(Role role) {
        return roleEntityRepository.findByRole(role);
    }

}
