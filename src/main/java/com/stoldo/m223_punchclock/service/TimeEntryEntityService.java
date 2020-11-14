 package com.stoldo.m223_punchclock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.repository.TimeEntryEntityRepository;

import java.util.List;

@Service
public class TimeEntryEntityService {
	
    private TimeEntryEntityRepository entryRepository;
    

    @Autowired
    public TimeEntryEntityService(TimeEntryEntityRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<TimeEntryEntity> getEntries() {
        return entryRepository.findAll();
    }
    
    public TimeEntryEntity save(TimeEntryEntity entry) {
    	return entryRepository.saveAndFlush(entry);
    }
    
    public void deleteEntry(Long id) {
    	entryRepository.deleteById(id);
    }
}
