package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.GroupeIntervenantDto;

import java.util.List;

public interface GroupeIntervenantService {

    GroupeIntervenantDto saveGroupeIntervenant(GroupeIntervenantDto groupeIntervenantDto);

    GroupeIntervenantDto getGroupeIntervenantById(Long idDto);

    boolean deleteGroupeIntervenant(Long idDto);

    List<GroupeIntervenantDto> listOfGroupeIntervenant();
}
