package com.example.gymbuddy.controller;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonaDto>> findAll() {
        var personas = this.personaService.findAllPersonas();

        if (personas.isEmpty()) {
            return new ResponseEntity<>(personas, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonaDto> createPerson(
            @RequestBody PersonaDto personaDto
    ) throws Exception {

        var personaCreated = this.personaService.createPerson(personaDto);

        return new ResponseEntity<>(personaCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<PersonaDto> updatePerson(
            @RequestBody PersonaDto personaDto
    ) throws Exception {
        var personaUpdated = this.personaService.updatePerson(personaDto);

        return new ResponseEntity<>(personaUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePerson(
            @PathVariable(name = "id") Long idPersona
    ) {
        var deletedPerson = this.personaService.deletePerson(idPersona);

        return (deletedPerson) ? new ResponseEntity<>(true , HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }

}
