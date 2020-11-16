package com.stoldo.m223_punchclock.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.enums.Role;

import lombok.Getter;
import lombok.Setter;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
@Getter
@Setter
@Entity(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Role role;
    
}
