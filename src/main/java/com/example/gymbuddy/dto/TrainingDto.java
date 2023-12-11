package com.example.gymbuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainingDto {

    private Long id;

    private String nombreClase;

    private String descripcionClase;

    private Date inicioClase;

    private Date finClase;

    private String foto;

    private Boolean canUpdateOrAdd;

    private Boolean canDelete;


    private List<UserTrainingDto> usuarioInscritos;

}
