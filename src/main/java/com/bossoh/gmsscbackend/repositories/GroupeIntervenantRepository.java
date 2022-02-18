package com.bossoh.gmsscbackend.repositories;
import com.bossoh.gmsscbackend.entities.GroupeIntervenant;
import com.bossoh.gmsscbackend.entities.SignalerPanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupeIntervenantRepository extends JpaRepository<GroupeIntervenant,Long> {
    //List<Long> findDistinctBySignalerPanne();
    //findDistinctByLastnameAndFirstname

}
