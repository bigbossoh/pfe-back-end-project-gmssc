package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.CorrectiveDto;

import java.util.List;

public interface CorrectiveService {

    CorrectiveDto saveCorrective(CorrectiveDto correctiveDto);

    CorrectiveDto getCorrectiveById(Long idDto);

    boolean deleteCorrective(Long idDto);

    List<CorrectiveDto> listOfCorrectives();
}
