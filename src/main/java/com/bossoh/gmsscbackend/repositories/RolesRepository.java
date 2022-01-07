package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    @Override
    List<Roles> findAll();
}
