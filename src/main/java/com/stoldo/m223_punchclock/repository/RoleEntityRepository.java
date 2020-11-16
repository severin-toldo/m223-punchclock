package com.stoldo.m223_punchclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.enums.Role;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
	
    public RoleEntity findByRole(Role role);
    
}
