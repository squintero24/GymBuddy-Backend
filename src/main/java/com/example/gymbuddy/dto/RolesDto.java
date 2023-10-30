package com.example.gymbuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class RolesDto {

    private Long idUserRol;

    private Date fechaCreacion;

    private String nombreRol;

}
