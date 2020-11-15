package com.stoldo.m223_punchclock.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stoldo.m223_punchclock.model.enums.UserStatus;
import com.stoldo.m223_punchclock.model.validation.email.Email;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;
    
    @NotNull
    @Column(name = "firstname")
    private String firstName;
    
    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @NotNull
    @Column(name = "password")
    @JsonIgnore
    private String password;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoleEntity> roles;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TimeEntryEntity> entries = new ArrayList<>();
    
    
    public UserDetails toUserDetails() {
    	Set<SimpleGrantedAuthority> authorities = roles
        		.stream()
        		.map(r -> new SimpleGrantedAuthority(r.getRole().name()))
        		.collect(Collectors.toSet());
    	
    	return new User(email, password, authorities);
    }
}
