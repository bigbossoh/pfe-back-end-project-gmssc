package com.bzada.gestimowebapi.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="token")
public class VerificationToken extends AbstractEntity {

    private String token;
    private Instant expiryDate;
    @OneToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;


}
