package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.ContratDto;
import com.bossoh.gmsscbackend.Dto.SignalerPanneDto;

import java.util.List;

public interface SignalerPanneService {

    List<SignalerPanneDto> listOfSignalerPanne();

    SignalerPanneDto saveSignalerPanne(SignalerPanneDto Dto);

    SignalerPanneDto getSignalerPanneId(Long id);

    boolean deleteSignalerPanne(Long id);
}
