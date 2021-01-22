package com.stoldo.m223_punchclock.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stoldo.m223_punchclock.model.entity.CategoryEntity;
import com.stoldo.m223_punchclock.model.entity.RoleEntity;
import com.stoldo.m223_punchclock.model.entity.TimeEntryEntity;
import com.stoldo.m223_punchclock.model.entity.UserEntity;
import com.stoldo.m223_punchclock.model.enums.Role;
import com.stoldo.m223_punchclock.model.enums.UserStatus;
import com.stoldo.m223_punchclock.repository.CategoryEntityRepository;
import com.stoldo.m223_punchclock.repository.RoleEntityRepository;
import com.stoldo.m223_punchclock.repository.TimeEntryEntityRepository;
import com.stoldo.m223_punchclock.repository.UserEntityRepository;


@Service
public class SeedDataService {
	
	@Value("${insert.seed.data}")
	private boolean insertSeedData;

	private UserEntityRepository userEntityRepository;
	private RoleEntityRepository roleEntityRepository;
	private TimeEntryEntityRepository timeEntryEntityRepository;
	private CategoryEntityRepository categoryEntityRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	public SeedDataService(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository, TimeEntryEntityRepository timeEntryEntityRepository, CategoryEntityRepository categoryEntityRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userEntityRepository = userEntityRepository;
		this.roleEntityRepository = roleEntityRepository;
		this.timeEntryEntityRepository = timeEntryEntityRepository;
		this.categoryEntityRepository = categoryEntityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	private void init() throws Exception {
		if (insertSeedData) {
			saveAdminUser();
			saveActiveUser();
			saveInvitedUser();
			saveCategories();
			saveAdminTimeEntries();
			saveActiveUserTimeEntries();
		}
	}

	private void saveAdminUser() {
		RoleEntity adminRole = new RoleEntity();
		adminRole.setRole(Role.ADMIN);
		
		roleEntityRepository.saveAndFlush(adminRole);
		
		UserEntity ue = new UserEntity();
		ue.setFirstName("Admin");
		ue.setLastName("Admin");
		ue.setEmail("admin@admin.com");
		ue.setPassword(passwordEncoder.encode("5sH!sl£u"));
		ue.setRoles(Arrays.asList(adminRole));
		ue.setStatus(UserStatus.ACTIVE);
		
		userEntityRepository.saveAndFlush(ue);
	}
	
	private void saveActiveUser() {
		UserEntity ue = new UserEntity();
		ue.setFirstName("Active");
		ue.setLastName("User");
		ue.setEmail("active@user.com");
		ue.setPassword(passwordEncoder.encode("5JäS!2iJ"));
		ue.setStatus(UserStatus.ACTIVE);
		
		userEntityRepository.saveAndFlush(ue);
	}
	
	private void saveInvitedUser() {
		UserEntity ue = new UserEntity();
		ue.setFirstName("Invited");
		ue.setLastName("User");
		ue.setEmail("invited@user.com");
		ue.setPassword(passwordEncoder.encode("1234"));
		ue.setStatus(UserStatus.INVITED);
		
		userEntityRepository.saveAndFlush(ue);
	}
	
	private void saveCategories() {
		List<String> categoryNames = Arrays.asList("Ferien", "Krankheit", "Unfall", "Schule / Kurs", "Militär", "Mutterschaft 80%", "Bezahlte Absenz", "Unbezahlter Urlaub");
		
		int id = 1;
		for (String cn : categoryNames) {
			CategoryEntity ce = new CategoryEntity();
			ce.setId(Long.valueOf(id));
			ce.setName(cn);
			categoryEntityRepository.saveAndFlush(ce);
			id++;
		}
	}
	
	private void saveAdminTimeEntries() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		TimeEntryEntity tee1 = new TimeEntryEntity();
		tee1.setCheckIn(sdf.parse("2020-11-15 08:00:00"));
		tee1.setCheckOut(sdf.parse("2020-11-15 12:00:00"));
		tee1.setUser(userEntityRepository.findByEmail("admin@admin.com").orElseThrow());
		
		TimeEntryEntity tee2 = new TimeEntryEntity();
		tee2.setCheckIn(sdf.parse("2020-11-15 13:00:00"));
		tee2.setCheckOut(sdf.parse("2020-11-15 17:00:00"));
		tee2.setUser(userEntityRepository.findByEmail("admin@admin.com").orElseThrow());
		
		timeEntryEntityRepository.save(tee1);
		timeEntryEntityRepository.save(tee2);
	}
	
	private void saveActiveUserTimeEntries() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		TimeEntryEntity tee1 = new TimeEntryEntity();
		tee1.setCheckIn(sdf.parse("2020-11-15 08:00:00"));
		tee1.setCheckOut(sdf.parse("2020-11-15 12:00:00"));
		tee1.setUser(userEntityRepository.findByEmail("active@user.com").orElseThrow());
		
		TimeEntryEntity tee2 = new TimeEntryEntity();
		tee2.setCheckIn(sdf.parse("2020-11-15 13:00:00"));
		tee2.setCheckOut(sdf.parse("2020-11-15 17:00:00"));
		tee2.setUser(userEntityRepository.findByEmail("active@user.com").orElseThrow());
		
		TimeEntryEntity tee3 = new TimeEntryEntity();
		tee3.setCheckIn(sdf.parse("2020-11-15 08:00:00"));
		tee3.setCheckOut(sdf.parse("2020-11-15 17:00:00"));
		tee3.setCategory(categoryEntityRepository.findById(1L).orElseThrow());
		tee3.setUser(userEntityRepository.findByEmail("active@user.com").orElseThrow());
		
		TimeEntryEntity tee4 = new TimeEntryEntity();
		tee4.setCheckIn(sdf.parse("2020-11-15 08:00:00"));
		tee4.setCheckOut(sdf.parse("2020-11-15 17:00:00"));
		tee4.setCategory(categoryEntityRepository.findById(2L).orElseThrow());
		tee4.setUser(userEntityRepository.findByEmail("active@user.com").orElseThrow());
		
		timeEntryEntityRepository.save(tee1);
		timeEntryEntityRepository.save(tee2);
		timeEntryEntityRepository.save(tee3);
		timeEntryEntityRepository.save(tee4);
	}
}
