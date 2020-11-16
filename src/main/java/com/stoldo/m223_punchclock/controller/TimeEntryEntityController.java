package com.stoldo.m223_punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.service.TimeEntryEntityService;

import javax.validation.Valid;
import java.util.List;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@RestController
@RequestMapping("/time-entries")
public class TimeEntryEntityController {
	
    private TimeEntryEntityService timeEntryEntityService;
    
    
    @Autowired
    public TimeEntryEntityController(TimeEntryEntityService timeEntryEntityService) {
        this.timeEntryEntityService = timeEntryEntityService;
    }
    
    /**
     * @return list of all time entries
     * @requestMethod GET
     * @responseStatus OK
     * @access ADMIN
     * */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<TimeEntryEntity> getAll() {
        return timeEntryEntityService.getAll();
    }
    
    /**
     * @return list of all entries of the logged in user entity
     * @path /mine
     * @requestMethod GET
     * @responseStatus OK
     * @access ALL
     * */
    @RequestMapping(value = "mine", method = RequestMethod.GET)
    public List<TimeEntryEntity> getAllByLoggedInUser() {
        return timeEntryEntityService.getAllByLoggedInUser();
    }
    
    /**
     * @return create time entry
     * @param time entry to create
     * @requestMethod POST
     * @responseStatus CREATED
     * @access ALL
     * */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public TimeEntryEntity create(@RequestBody @Valid TimeEntryEntity tee) {
        return timeEntryEntityService.create(tee);
    }
    
    /**
     * @return time entry with given id
     * @param id of desired time entry
     * @path /{id}
     * @requestMethod GET
     * @responseStatus OK
     * @access ALL
     * */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public TimeEntryEntity getById(@PathVariable Long id) {
    	return timeEntryEntityService.getByIdWithAccessCheck(id);
    }
    
    /**
     * @return edited time entry
     * @param time entry to edit and its id
     * @path /{id}
     * @requestMethod PATCH
     * @responseStatus OK
     * @access ALL
     * */
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public TimeEntryEntity edit(@PathVariable Long id, @RequestBody @Valid TimeEntryEntity tee) {
    	return timeEntryEntityService.edit(id, tee);
    }
    
    /**
     * @param id of time entry to delete
     * @path /{id}
     * @requestMethod DELETE
     * @responseStatus NO_CONTENT
     * @access ALL
     * */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
    	timeEntryEntityService.delete(id);
    }
    
}
