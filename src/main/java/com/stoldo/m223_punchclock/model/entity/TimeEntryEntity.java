package com.stoldo.m223_punchclock.model.entity;

import com.stoldo.m223_punchclock.model.validation.ValidCheckInCheckOut;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
@Entity(name = "time_entry")
@ValidCheckInCheckOut
public class TimeEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "check_in")
    private Date checkIn;

    @NotNull
    @Column(name = "check_out")
    private Date checkOut;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id_fk")
    private CategoryEntity category;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id_fk")
    private UserEntity user;
   
}
