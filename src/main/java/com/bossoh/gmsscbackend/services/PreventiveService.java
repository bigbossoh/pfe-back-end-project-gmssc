package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.PreventiveDto;

import java.util.List;

public interface PreventiveService {
    PreventiveDto savePreventive(PreventiveDto preventiveDto);

    PreventiveDto getPreventiveById(Long idDto);

    boolean deletePreventive(Long idDto);

    List<PreventiveDto> listOfPreventives();
}
