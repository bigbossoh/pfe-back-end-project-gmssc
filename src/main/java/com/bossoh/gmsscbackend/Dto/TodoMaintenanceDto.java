package com.bossoh.gmsscbackend.Dto;


import com.bossoh.gmsscbackend.entities.TodoMaintenance;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
@Data
@Builder
public class TodoMaintenanceDto {
    private Long id;
    private String titre;
    private String description;
    private Integer quantite;
    private String unite;
    private LocalDate dateDoto;
    private String lieuGeographique;
    private double coutMaintenance;
    private CorrectiveDto correctiveDto;

    public static TodoMaintenance toEntity(TodoMaintenanceDto dto){
        if(dto==null){
            return null;
        }
        TodoMaintenance newTodoMaintenance= new TodoMaintenance();
        newTodoMaintenance.setId(dto.getId());
        newTodoMaintenance.setTitre(dto.getTitre());
        newTodoMaintenance.setDescription(dto.getDescription());
        newTodoMaintenance.setQuantite(dto.getQuantite());
        newTodoMaintenance.setUnite(dto.getUnite());
        newTodoMaintenance.setDateDoto(dto.getDateDoto());
        newTodoMaintenance.setLieuGeographique(dto.getLieuGeographique());
        newTodoMaintenance.setCoutMaintenance(dto.getCoutMaintenance());
       newTodoMaintenance.setCorrective(CorrectiveDto.toEntity(dto.getCorrectiveDto()));
        return newTodoMaintenance;
    }
    public static TodoMaintenanceDto fromEntity(TodoMaintenance todoMaintenance) {
        if (todoMaintenance == null) {
            return null;
        }
        return TodoMaintenanceDto.builder()
                .id(todoMaintenance.getId())
                .titre(todoMaintenance.getTitre())
                .description(todoMaintenance.getDescription())
                .quantite(todoMaintenance.getQuantite())
                .unite(todoMaintenance.getUnite())
                .dateDoto(todoMaintenance.getDateDoto())
                .lieuGeographique(todoMaintenance.getLieuGeographique())
                .coutMaintenance(todoMaintenance.getCoutMaintenance())
                .correctiveDto(CorrectiveDto.fromEntity(todoMaintenance.getCorrective()))
                .build();
    }
}
