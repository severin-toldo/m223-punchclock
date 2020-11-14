package com.stoldo.m223_punchclock.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stoldo.m223_punchclock.model.enums.UserType;
import com.stoldo.m223_punchclock.shared.util.CommonUtils;

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
    @Email(regexp = CommonUtils.EMAIL_REGEXP, message = CommonUtils.EMAIL_REGEXP_MESSAGE)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    @JsonIgnore
    private String password;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> securityRoles;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<TimeEntryEntity> entries = new ArrayList<>();
    
    
    public UserDetails toUserDetails() {
    	Set<SimpleGrantedAuthority> authorities = securityRoles
        		.stream()
        		.map(r -> new SimpleGrantedAuthority(r.getRole().name()))
        		.collect(Collectors.toSet());
    	
    	return new User(email, password, authorities);
    }
}
