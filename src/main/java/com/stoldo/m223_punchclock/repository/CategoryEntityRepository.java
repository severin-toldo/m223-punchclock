package com.stoldo.m223_punchclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {
	
	public Long countById(Long id);
	
}
