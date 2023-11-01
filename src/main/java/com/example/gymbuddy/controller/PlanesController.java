package com.example.gymbuddy.controller;

import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.service.interfaces.IUsuarioPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "*")
public class PlanesController {

    @Autowired
    private IUsuarioPlanService usuarioPlanService;

    @GetMapping("/all")
    public ResponseEntity<List<PlanDto>> findAll() {
        var planes = this.usuarioPlanService.findAllPlans();

        if (planes.isEmpty()) {
            return new ResponseEntity<>(planes, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(planes, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<PlanDto> createPlan(
            @RequestBody PlanDto planDto
    ) {

        var planCreated = this.usuarioPlanService.createPlan(planDto);

        return new ResponseEntity<>(planCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PlanDto> actualizarUsuario(
            @RequestBody PlanDto planDto
    ) throws Exception {

        var entrenamientoUpdated = this.usuarioPlanService.updatePlan(planDto);

        return new ResponseEntity<>(entrenamientoUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable(name = "id") Long idClase
    ) {
        var deletedEntrenamiento = this.usuarioPlanService.deletePlan(idClase);

        return (deletedEntrenamiento) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }


}
