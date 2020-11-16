package com.stoldo.m223_punchclock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.model.entity.UserEntity;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public interface TimeEntryEntityRepository extends JpaRepository<TimeEntryEntity, Long> {
	
	public List<TimeEntryEntity> findByUser(UserEntity ue);
	
	public Optional<TimeEntryEntity> findByIdAndUser(Long id, UserEntity ue);
	
}
