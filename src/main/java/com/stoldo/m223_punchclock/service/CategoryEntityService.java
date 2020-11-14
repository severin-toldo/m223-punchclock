package com.stoldo.m223_punchclock.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.model.enums.ErrorCode;
import com.stoldo.m223_punchclock.model.exception.ErrorCodeException;
import com.stoldo.m223_punchclock.repository.CategoryEntityRepository;


@Service
public class CategoryEntityService {
	
    private CategoryEntityRepository categoryEntityRepository;
    private TimeEntryEntityService timeEntryEntityService;

    
    @Autowired
    public CategoryEntityService(CategoryEntityRepository categoryEntityRepository, TimeEntryEntityService timeEntryEntityService) {
        this.categoryEntityRepository = categoryEntityRepository;
        this.timeEntryEntityService = timeEntryEntityService;
    }
    
    public List<CategoryEntity> getAll() {
        return categoryEntityRepository.findAll();
    }

    public CategoryEntity create(CategoryEntity ce) {
    	ce.setId(null);
        
    	return save(ce);
    }
    
    public CategoryEntity edit(Long id, CategoryEntity ce) {
    	CategoryEntity existingCategory = categoryEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found!"));
    	existingCategory.setName(ce.getName());
    	
    	return save(ce);
    }
    
    public void delete(Long id) {
    	CategoryEntity existingCategory = categoryEntityRepository.findById(id).orElse(null);
    	
    	if (timeEntryEntityService.countByCategory(existingCategory) == 0) {
    		categoryEntityRepository.deleteById(id);	
    	} else {
    		throw new ErrorCodeException(ErrorCode.E1003, HttpStatus.BAD_REQUEST);
    	}
    }
    
    private CategoryEntity save(CategoryEntity ce) {
    	return categoryEntityRepository.saveAndFlush(ce);	
    }
}
