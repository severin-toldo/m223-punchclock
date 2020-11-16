package com.stoldo.m223_punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.service.CategoryEntityService;

import javax.validation.Valid;
import java.util.List;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@RestController
@RequestMapping("/categories")
public class CategoryEntityController {
	
    private CategoryEntityService categoryEntityService;
    
    
    @Autowired
    public CategoryEntityController(CategoryEntityService categoryEntityService) {
        this.categoryEntityService = categoryEntityService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getAll() {
        return categoryEntityService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CategoryEntity create(@RequestBody @Valid CategoryEntity ce) {
        return categoryEntityService.create(ce);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public CategoryEntity edit(@PathVariable Long id, @RequestBody @Valid CategoryEntity ce) {
    	return categoryEntityService.edit(id, ce);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	categoryEntityService.delete(id);
    }
    
}
