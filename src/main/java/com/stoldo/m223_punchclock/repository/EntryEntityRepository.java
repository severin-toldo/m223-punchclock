package com.stoldo.m223_punchclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.EntryEntity;

public interface EntryEntityRepository extends JpaRepository<EntryEntity, Long> {
	
}
