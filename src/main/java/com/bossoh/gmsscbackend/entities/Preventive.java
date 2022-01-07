package com.bossoh.gmsscbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PREVE")
public class Preventive extends Intervention{
    private Boolean programmer;
    private LocalDate dateProgramation;
    private Boolean isDone;
    private Boolean periodique;
}
