package com.stoldo.m223_punchclock.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.stoldo.m223_punchclock.model.validation.check_in_check_out.ValidCheckInCheckOut;

import java.util.Date;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
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
