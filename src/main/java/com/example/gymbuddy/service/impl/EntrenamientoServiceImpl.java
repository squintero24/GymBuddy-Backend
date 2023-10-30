package com.example.gymbuddy.service.impl;

import com.example.gymbuddy.dto.TrainingDto;
import com.example.gymbuddy.mapper.IEntrenamientoMapper;
import com.example.gymbuddy.repository.IEntrenamientoRepository;
import com.example.gymbuddy.service.interfaces.IEntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrenamientoServiceImpl implements IEntrenamientoService {

    @Autowired
    private IEntrenamientoRepository entrenamientoRepository;

    @Override
    public List<TrainingDto> findAllTrainings() {
        return this.entrenamientoRepository.findAll().stream()
                .map(IEntrenamientoMapper.INSTANCE::toTrainingDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TrainingDto crearEntrenamiento(TrainingDto informacionClase) throws Exception {

        if (fechaAcomparacionFechaB(informacionClase.getInicioClase(), informacionClase.getFinClase(), ">=")
                || fechaAcomparacionFechaB(informacionClase.getInicioClase(), new Date(), "<=")) {
            throw new Exception("La fecha inicio no puede ser mayor a la fecha fin, al mismo tiempo no se pueden crear clases antes de la fecha actual.");
        }


        var claseToSave = IEntrenamientoMapper.INSTANCE.toTrainingEntity(informacionClase);

        return IEntrenamientoMapper.INSTANCE.toTrainingDto(this.entrenamientoRepository.save(claseToSave));
    }

    @Override
    @Transactional
    public TrainingDto updateEntrenamiento(TrainingDto informacionClase) throws Exception {

        var claseBD = this.entrenamientoRepository.findById(informacionClase.getId()).orElseThrow(() -> new Exception("NO EXISTE LA CLASE QUE SE QUIERE ACTUALIZAR"));

        if (fechaAcomparacionFechaB(informacionClase.getInicioClase(), informacionClase.getFinClase(), ">=")
                || fechaAcomparacionFechaB(informacionClase.getInicioClase(), new Date(), "<=")) {
            throw new Exception("La fecha inicio no puede ser mayor a la fecha fin, al mismo tiempo no se pueden crear o actualizar clases antes de la fecha actual.");
        }

        if (
                (fechaAcomparacionFechaB(claseBD.getStartDate(), new Date(), ">=")
                        && fechaAcomparacionFechaB(claseBD.getEndDate(), new Date(), "<="))
                        || fechaAcomparacionFechaB(claseBD.getEndDate(), new Date(), "<=")

        ) {
            throw new Exception("NO SE PUEDE ACTUALIZAR UNA CLASE QUE ESTÁ EN CURSO O QUE YA TERMINO.");
        }

        claseBD.setName(informacionClase.getNombreClase());
        claseBD.setDescription(informacionClase.getDescripcionClase());
        claseBD.setPhoto(informacionClase.getFoto());
        claseBD.setStartDate(informacionClase.getInicioClase());
        claseBD.setEndDate(informacionClase.getFinClase());

        return IEntrenamientoMapper.INSTANCE.toTrainingDto(this.entrenamientoRepository.save(claseBD));
    }

    @Override
    @Transactional
    public Boolean eliminarEntrenamiento(Long idClase) {

        var trainingToDelete = this.entrenamientoRepository.findById(idClase);

        if (trainingToDelete.isPresent()) {
            this.entrenamientoRepository.deleteById(idClase);
            return true;
        } else {
            return false;
        }
    }

    static Boolean fechaAcomparacionFechaB(Date fechaA, Date fechaB, String typeComparation) throws Exception {

        if (typeComparation.equals(">=")) {
            return fechaA.compareTo(fechaB) >= 0;
        } else if (typeComparation.equals("<=")) {
            return fechaA.compareTo(fechaB) <= 0;
        } else if (typeComparation.equals(">")) {
            return fechaA.compareTo(fechaB) > 0;
        } else if (typeComparation.equals("<")) {
            return fechaA.compareTo(fechaB) < 0;
        } else if (typeComparation.equals("==")) {
            return fechaA.compareTo(fechaB) == 0;
        } else {
            throw new Exception("NO ELIGIO UN OPERADOR LOGICO VALIDO");
        }


    }
}
