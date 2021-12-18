package com.bossoh.gmsscbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CORREC")
public class Corrective extends Intervention{
    private String actionsMener;
    private Boolean reparation;
    private Boolean depannage;
    private double coutMaintenance;


}
