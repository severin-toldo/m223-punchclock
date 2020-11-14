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
	
    private TimeEntryEntityService entryService;
    
    
    @Autowired
    public TimeEntryEntityController(TimeEntryEntityService entryService) {
        this.entryService = entryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TimeEntryEntity> getEntries() {
        return entryService.getEntries();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public TimeEntryEntity createEntry(@Valid @RequestBody TimeEntryEntity entry) {
        return entryService.save(entry);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public TimeEntryEntity updateEntry(@PathVariable Long id, @Valid @RequestBody TimeEntryEntity entry) {
    	// TODO get by id
    	return entryService.save(entry);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable Long id) {
    	entryService.deleteEntry(id);
    }
}
