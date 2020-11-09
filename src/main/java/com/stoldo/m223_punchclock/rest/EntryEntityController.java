package com.stoldo.m223_punchclock.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.stoldo.m223_punchclock.model.entity.EntryEntity;
import com.stoldo.m223_punchclock.service.EntryEntityService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/entries")
public class EntryEntityController {
	
    private EntryEntityService entryService;
    

    public EntryEntityController(EntryEntityService entryService) {
        this.entryService = entryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EntryEntity> getEntries() {
        return entryService.getEntries();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public EntryEntity createEntry(@Valid @RequestBody EntryEntity entry) {
        return entryService.save(entry);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public EntryEntity updateEntry(@PathVariable Long id, @Valid @RequestBody EntryEntity entry) {
    	// TODO get by id
    	return entryService.save(entry);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable Long id) {
    	entryService.deleteEntry(id);
    }
}
