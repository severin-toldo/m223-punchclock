package com.stoldo.m223_punchclock.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.repository.CategoryEntityRepository;

@Service
public class CategoryEntityService {
	
    private CategoryEntityRepository categoryRepository;

    
    @Autowired
    public CategoryEntityService(CategoryEntityRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryEntity> getCategories() {
    	return categoryRepository.findAll();
    }

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.saveAndFlush(category);
    }
    
    public void deleteCategory(Long id) {
    	categoryRepository.deleteById(id);
    }
}
