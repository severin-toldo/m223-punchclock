package com.stoldo.m223_punchclock.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.service.CategoryEntityService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryEntityController {
	
    private CategoryEntityService categoryService;
    

    public CategoryEntityController(CategoryEntityService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getAllEntries() {
        return categoryService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public CategoryEntity createCategory(@Valid @RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public CategoryEntity updateCategory(@Valid @RequestBody CategoryEntity category) {
    	return categoryService.save(category);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void updateCategory(@PathVariable Long id) {
    	categoryService.deleteCategory(id);
    }
}
