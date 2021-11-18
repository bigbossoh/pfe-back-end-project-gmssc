package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Societe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BienImmobilierService {

    List<BienImmobilier> listOfBienImmobiliers();

    BienImmobilier saveBienImmobilier(BienImmobilier bien);

    BienImmobilier updateBienImmobilier(BienImmobilier bien);

    BienImmobilier getBienImmobilierById(Long id);

    BienImmobilier getBienImmobilierByCode(String codeBien);

    boolean deleteBienImmobilier(Long id);


}
