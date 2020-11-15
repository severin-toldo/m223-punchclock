 package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
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
    private CategoryEntityService categoryEntityService;
    

    @Autowired
    public TimeEntryEntityService(TimeEntryEntityRepository timeEntryEntityRepository, CurrentSessionService css, UserEntityService userEntityService, CategoryEntityService categoryEntityService) {
        this.timeEntryEntityRepository = timeEntryEntityRepository;
        this.css = css;
        this.userEntityService = userEntityService;
        this.categoryEntityService = categoryEntityService;
    }
    
    public List<TimeEntryEntity> getAll() {
    	return timeEntryEntityRepository.findAll();
    }
    
    public List<TimeEntryEntity> getAllByLoggedInUser() {
    	return timeEntryEntityRepository.findByUser(css.getLoggedInUserEntity());
    }
    
    public TimeEntryEntity create(TimeEntryEntity tee) {
    	UserEntity loggedInUser = css.getLoggedInUserEntity();
    	tee.setUser(loggedInUser);
    	tee.setId(null);
    	sanitizeCategory(tee);
    	
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
    	sanitizeCategory(existingEntry);
    	
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
    
    /**
     * Ensures that you can't create or edit categories by modifying the POST body
     * */
    private void sanitizeCategory(TimeEntryEntity tee) {
    	if (tee.getCategory() != null) {
    		if (tee.getCategory().getId() == null) {
    			tee.setCategory(null);	
    		} else {
    			CategoryEntity existingCategory = categoryEntityService.getById(tee.getCategory().getId());
    			tee.setCategory(existingCategory);
    		}
    	}
    }
}
