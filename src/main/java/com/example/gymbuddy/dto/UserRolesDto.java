package com.example.gymbuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class UserRolesDto {

    private Long id;

    private Long idUsuario;

    private Long idPlan;

    private Date fechaDesde;

    private Date fechaHasta;

}
