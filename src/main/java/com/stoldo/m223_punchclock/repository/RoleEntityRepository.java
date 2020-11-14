package com.stoldo.m223_punchclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.enums.Role;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
	
    public RoleEntity findByRole(Role role);
    
}
