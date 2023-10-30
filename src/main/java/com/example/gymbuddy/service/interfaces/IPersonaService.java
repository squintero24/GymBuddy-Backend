package com.example.gymbuddy.service.interfaces;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IPersonaService {
    /**
     * Obtiene todas las personas que esten almacenadas en la bd
     * @return List<PersonaDto>
     */
    List<PersonaDto> findAllPersonas();

    /**
     * Obtiene todas las personas que esten almacenadas en la bd
     * @return List<PersonaDto>
     */
    PersonaDto createPerson(PersonaDto personaDto) throws Exception;

    /**
     * Obtiene todas las personas que esten almacenadas en la bd
     * @return List<PersonaDto>
     */
    PersonaDto updatePerson(PersonaDto personaDto) throws Exception;

    /**
     * Obtiene todas las personas que esten almacenadas en la bd
     * @return List<PersonaDto>
     */
    Boolean deletePerson(Long idPersona);


}
