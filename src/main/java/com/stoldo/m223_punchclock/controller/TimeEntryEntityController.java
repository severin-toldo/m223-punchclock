package com.stoldo.m223_punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.service.TimeEntryEntityService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryEntityController {
	
    private TimeEntryEntityService timeEntryEntityService;
    
    
    @Autowired
    public TimeEntryEntityController(TimeEntryEntityService timeEntryEntityService) {
        this.timeEntryEntityService = timeEntryEntityService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<TimeEntryEntity> getAll() {
        return timeEntryEntityService.getAll();
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public TimeEntryEntity create(@Valid @RequestBody TimeEntryEntity tee) {
        return timeEntryEntityService.create(tee);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public TimeEntryEntity edit(@PathVariable Long id, @Valid @RequestBody TimeEntryEntity tee) {
    	return timeEntryEntityService.edit(id, tee);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	timeEntryEntityService.delete(id);
    }
    
}
