package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Societe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BienImmobilierService {



    BienImmobilierDto saveBienImmobilier(BienImmobilierDto bienDto);

    BienImmobilierDto getBienImmobilierById(Long idDto);

    BienImmobilierDto getBienImmobilierByCode(String codeBienDto);

    boolean deleteBienImmobilier(Long idDto);

    List<BienImmobilierDto> listOfBienImmobiliers();


}
