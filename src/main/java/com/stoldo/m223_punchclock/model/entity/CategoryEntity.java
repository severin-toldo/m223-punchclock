package com.stoldo.m223_punchclock.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    private List<EntryEntity> entries = new ArrayList<>();
    
}
