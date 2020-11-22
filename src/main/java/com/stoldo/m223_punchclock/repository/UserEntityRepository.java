package com.stoldo.m223_punchclock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stoldo.m223_punchclock.model.entity.UserEntity;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    
	public Optional<UserEntity> findByEmail(String email);
	
}
