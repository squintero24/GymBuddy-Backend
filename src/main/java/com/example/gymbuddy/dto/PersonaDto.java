package com.example.gymbuddy.dto;

import com.example.gymbuddy.model.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PersonaDto {

    private Long id;

    private String name;

    private String lastName;

    private String address;

    private String neighborhood;

    private Date birthDate;

    private String email;

    private String phoneNumber;

    private String numDocument;

    private Long idTipoDocumento;

    private String photo;

    private Long idUsuario;

    private String nombreUsuario;

    private Long userValido;

    private List<RolesDto> rolPersona;

    private Long idRolUsuario;

    private Long idRol;

    private Long idPlanUsuario;

    private Long idPlan;

    private Date fechaDesdePlan;

    private Date fechaHastaPlan;
}
