package com.stoldo.m223_punchclock.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.stoldo.m223_punchclock.model.SecurityRoleName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "security_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private SecurityRoleName name;
    
}
