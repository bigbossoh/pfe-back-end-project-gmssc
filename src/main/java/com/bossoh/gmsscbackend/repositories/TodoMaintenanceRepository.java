package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.entities.TodoMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoMaintenanceRepository extends JpaRepository<TodoMaintenance, Long> {
    Optional<TodoMaintenance> findById(Long Id);
    List<TodoMaintenance> findAll();
    List<TodoMaintenance> findAllByOrderByDateDotoDesc();
}
