package com.example.gymbuddy.controller;

import com.example.gymbuddy.dto.TrainingDto;
import com.example.gymbuddy.service.interfaces.IEntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenamiento")
public class EntrenamientoController {


    @Autowired
    private IEntrenamientoService entrenamientoService;

    @GetMapping("/all")
    public ResponseEntity<List<TrainingDto>> findAll() {
        var entrenamientos = this.entrenamientoService.findAllTrainings();

        if (entrenamientos.isEmpty()) {
            return new ResponseEntity<>(entrenamientos, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entrenamientos, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<TrainingDto> crearEntrenamiento(
            @RequestBody TrainingDto trainingDto
            ) throws Exception {

        var entrenamientoCreated = this.entrenamientoService.crearEntrenamiento(trainingDto);

        return new ResponseEntity<>(entrenamientoCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TrainingDto> actualizarUsuario(
            @RequestBody TrainingDto trainingDto
    ) throws Exception {

        var entrenamientoUpdated = this.entrenamientoService.updateEntrenamiento(trainingDto);

        return new ResponseEntity<>(entrenamientoUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable(name = "id") Long idClase
    ) {
        var deletedEntrenamiento = this.entrenamientoService.eliminarEntrenamiento(idClase);

        return (deletedEntrenamiento) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }


}
