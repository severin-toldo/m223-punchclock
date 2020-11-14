 package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.repository.TimeEntryEntityRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;


@Service
public class TimeEntryEntityService {
	
    private TimeEntryEntityRepository timeEntryEntityRepository;
    private CurrentSessionService css;
    private UserEntityService userEntityService;
    

    @Autowired
    public TimeEntryEntityService(TimeEntryEntityRepository timeEntryEntityRepository, CurrentSessionService css, UserEntityService userEntityService) {
        this.timeEntryEntityRepository = timeEntryEntityRepository;
        this.css = css;
        this.userEntityService = userEntityService;
    }
    
    public List<TimeEntryEntity> getAll() {
    	UserEntity loggedInUser = css.getLoggedInUserEntity();
    	
    	if (userEntityService.isAdmin(loggedInUser)) {
    		return timeEntryEntityRepository.findAll();
    	}
    	
        return timeEntryEntityRepository.findByUser(loggedInUser);
    }
    
    public TimeEntryEntity create(TimeEntryEntity tee) {
    	UserEntity loggedInUser = css.getLoggedInUserEntity();
    	tee.setUser(loggedInUser);
        return save(tee);
    }
    
    public TimeEntryEntity edit(Long id, TimeEntryEntity tee) {
    	UserEntity loggedInUser = css.getLoggedInUserEntity();
    	TimeEntryEntity existingEntry = null;
    	
    	if (userEntityService.isAdmin(loggedInUser)) {
    		existingEntry = timeEntryEntityRepository
    				.findById(id)
    				.orElseThrow(() -> new EntityNotFoundException("TimeEntry with id " + id + " not found!"));
    	} else {
    		existingEntry = timeEntryEntityRepository
    				.findByIdAndUser(id, loggedInUser)
    				.orElseThrow(() -> new EntityNotFoundException("TimeEntry with id " + id + " and user id " + loggedInUser.getId() + " not found!")); 	
    	}
    	
    	existingEntry.setCategory(tee.getCategory());
    	existingEntry.setCheckIn(tee.getCheckIn());
    	existingEntry.setCheckOut(tee.getCheckOut());
    	
    	return save(tee);
    }
    
    public void delete(Long id) {
    	UserEntity loggedInUser = css.getLoggedInUserEntity();
    	
    	if (userEntityService.isAdmin(loggedInUser)) {
    		timeEntryEntityRepository.deleteById(id);	
    	} else {
    		TimeEntryEntity existingEntry = timeEntryEntityRepository.findByIdAndUser(id, loggedInUser).orElse(null);
    		
    		if (existingEntry != null) {
    			timeEntryEntityRepository.delete(existingEntry);	
    		}
    	}
    }
    
    private TimeEntryEntity save(TimeEntryEntity tee) {
		return timeEntryEntityRepository.saveAndFlush(tee);    
    }
}
