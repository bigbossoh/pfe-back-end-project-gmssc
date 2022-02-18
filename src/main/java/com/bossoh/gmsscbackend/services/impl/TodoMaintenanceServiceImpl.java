package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.TodoMaintenanceDto;
import com.bossoh.gmsscbackend.Validator.TodoMaintenanceValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.*;
import com.bossoh.gmsscbackend.services.TodoMaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TodoMaintenanceServiceImpl implements TodoMaintenanceService {

    private final TodoMaintenanceRepository todoMaintenanceRepository;
    private final CorrectiveRepository correctiveRepository;

    @Override
    public TodoMaintenanceDto saveTodoMaintenance(TodoMaintenanceDto dto) {
        log.info("We are going to save a new actiona mener {}",dto);
        List<String> errors= TodoMaintenanceValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("l'objet action amener n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object action a mener sont null.",
                    ErrorCodes.TODOACTION_NOT_VALID,errors);
        }
        Optional<Corrective> corrective = correctiveRepository.findById(dto.getCorrectiveDto().getId());
        if (!corrective.isPresent()) {
            log.warn("La maintenance corrective with ID {} was not found in the DB", dto.getCorrectiveDto().getId());
            throw new EntityNotFoundException("Aucune maintenance corrective  avec l'ID" + dto.getCorrectiveDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.CORRECTIVE_NOT_FOUND);
        }

        TodoMaintenance saveTodoMaintenance=todoMaintenanceRepository.save(TodoMaintenanceDto.toEntity(dto));
        return TodoMaintenanceDto.fromEntity(saveTodoMaintenance);
    }

    @Override
    public TodoMaintenanceDto getTodoMaintenanceById(Long id) {
        log.info("We are going to get back the Action a mener en fonction de l'ID {} du bien", id);
        if(id==null){
            log.error("you are provided a null ID for the action a mener");
            return null;
        }
        return todoMaintenanceRepository.findById(id)
                .map(TodoMaintenanceDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucune action has been found with ID "+id,
                        ErrorCodes.INTERVENANT_NOT_FOUND));
    }

    @Override
    public List<TodoMaintenanceDto> listOfTodoMaintenance() {
        log.info("We are going to take back all the actions" );

        return todoMaintenanceRepository.findAll().stream()
                .map(TodoMaintenanceDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<TodoMaintenanceDto> ListofTodoMaintenanceDtoByDateDotoOrderByDesc() {
        log.info("We are going to take back all the actions" );

        return todoMaintenanceRepository.findAllByOrderByDateDotoDesc().stream()
                .map(TodoMaintenanceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteTodoMaintenanceDto(Long idDto) {
        log.info("We are going to delete a Action {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the Action");
            return false;
        }
        boolean exist=todoMaintenanceRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun Action avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.TODOACTION_NOT_FOUND);
        }
        todoMaintenanceRepository.deleteById(idDto);
        return true;
    }
}
