package com.stoldo.m223_punchclock.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stoldo.m223_punchclock.shared.util.CommonUtils;

import lombok.Getter;
import lombok.Setter;

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
    
    @Column(nullable = false, unique = true)
    @Email(regexp = CommonUtils.EMAIL_REGEXP, message = CommonUtils.EMAIL_REGEXP_MESSAGE)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> securityRoles;
    
    
    public UserDetails toUserDetails() {
    	Set<SimpleGrantedAuthority> authorities = securityRoles
        		.stream()
        		.map(r -> new SimpleGrantedAuthority(r.getName().name()))
        		.collect(Collectors.toSet());
    	
    	return new User(email, password, authorities);
    }
}
