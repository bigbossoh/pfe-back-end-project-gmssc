package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;


import java.util.List;


public interface BienImmobilierService {

    BienImmobilierDto saveBienImmobilier(BienImmobilierDto bienDto);

    BienImmobilierDto getBienImmobilierById(Long idDto);

    BienImmobilierDto getBienImmobilierByCode(String codeBienDto);

    boolean deleteBienImmobilier(Long idDto);

    List<BienImmobilierDto> listOfBienImmobiliers();


}
