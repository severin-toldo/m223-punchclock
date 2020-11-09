package com.stoldo.m223_punchclock.service;

import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.EntryEntity;
import com.stoldo.m223_punchclock.repository.EntryEntityRepository;

import java.util.List;

@Service
public class EntryEntityService {
	
    private EntryEntityRepository entryRepository;
    

    public EntryEntityService(EntryEntityRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<EntryEntity> getEntries() {
        return entryRepository.findAll();
    }
    
    public EntryEntity save(EntryEntity entry) {
    	return entryRepository.saveAndFlush(entry);
    }
    
    public void deleteEntry(Long id) {
    	entryRepository.deleteById(id);
    }
}
