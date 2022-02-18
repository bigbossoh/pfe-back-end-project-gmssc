package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.TodoMaintenanceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface TodoMaintenanceService {
    TodoMaintenanceDto saveTodoMaintenance(TodoMaintenanceDto Dto);

    TodoMaintenanceDto getTodoMaintenanceById(Long id);

    List<TodoMaintenanceDto> listOfTodoMaintenance();

    List<TodoMaintenanceDto> ListofTodoMaintenanceDtoByDateDotoOrderByDesc();

    boolean deleteTodoMaintenanceDto(Long id);
}
