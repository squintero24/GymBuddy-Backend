package com.example.gymbuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UsuarioDto {

    private Long id;

    private String username;

    private String passwd;

    private Long userValido;

    private Date fechaCreacionUser;

    private List<RolesDto> roles;



}
