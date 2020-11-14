package com.stoldo.m223_punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.service.CategoryEntityService;

import javax.validation.Valid;
import java.util.List;


//TODO dependecy stuff -> wenn noch zeit eintrag mit kategire und kategrie löschen dann fehler etc. does this make sense tho?
//-> think about it


@RestController
@RequestMapping("/categories")
public class CategoryEntityController {
	
    private CategoryEntityService categoryService;
    
    
    @Autowired
    public CategoryEntityController(CategoryEntityService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getCategories() {
        return categoryService.getCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CategoryEntity createCategory(@Valid @RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public CategoryEntity updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryEntity category) {
    	// TODO get by id
    	return categoryService.save(category);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void updateCategory(@PathVariable Long id) {
    	categoryService.deleteCategory(id);
    }
}
