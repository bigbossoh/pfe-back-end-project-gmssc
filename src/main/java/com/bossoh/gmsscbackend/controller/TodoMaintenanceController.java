package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.TodoMaintenanceDto;
import com.bossoh.gmsscbackend.services.impl.TodoMaintenanceServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/actions")
@RequiredArgsConstructor
public class TodoMaintenanceController {
    private  final TodoMaintenanceServiceImpl todoMaintenanceServiceImpl;

    @GetMapping(value=APP_ROOT+"/actions/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TodoMaintenanceDto> getAllActions(){
        return todoMaintenanceServiceImpl.listOfTodoMaintenance();
    }

    @GetMapping(value=APP_ROOT+"/actions/allOrdered",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TodoMaintenanceDto> getAllActionsOrdered(){
        return todoMaintenanceServiceImpl.ListofTodoMaintenanceDtoByDateDotoOrderByDesc();
    }

    @GetMapping(value=APP_ROOT+"/actions/{Idaction}",produces = MediaType.APPLICATION_JSON_VALUE)
    public TodoMaintenanceDto getActionByID(@PathVariable("Idaction") Long Idaction) {
        return todoMaintenanceServiceImpl.getTodoMaintenanceById(Idaction);
    }

    @PostMapping(value =APP_ROOT+"/actions/saveaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TodoMaintenanceDto saveAction(@RequestBody TodoMaintenanceDto todoMaintenanceDto) {
        return todoMaintenanceServiceImpl.saveTodoMaintenance(todoMaintenanceDto);
    }

    @DeleteMapping(value = APP_ROOT+"/actions/delete/{Idaction}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteActiobById(@PathVariable("Idaction") Long Idaction) {
        return todoMaintenanceServiceImpl.deleteTodoMaintenanceDto(Idaction);
    }

}
