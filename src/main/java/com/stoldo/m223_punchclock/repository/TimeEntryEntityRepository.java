package com.stoldo.m223_punchclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;

public interface TimeEntryEntityRepository extends JpaRepository<TimeEntryEntity, Long> {
	
}
