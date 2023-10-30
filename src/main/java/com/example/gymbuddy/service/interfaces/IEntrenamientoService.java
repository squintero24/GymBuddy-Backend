package com.example.gymbuddy.service.interfaces;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.dto.TrainingDto;

import java.util.List;

public interface IEntrenamientoService {

    List<TrainingDto> findAllTrainings();

    TrainingDto crearEntrenamiento(TrainingDto informacionClase) throws Exception;

    TrainingDto updateEntrenamiento(TrainingDto informacionClase) throws Exception;

    Boolean eliminarEntrenamiento(Long idClase);

}
