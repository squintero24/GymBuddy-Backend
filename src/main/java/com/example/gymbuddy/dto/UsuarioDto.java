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

    private Long idPerson;

    private String username;

    private String passwd;

    private Long userValido;

    private Date fechaCreacionUser;

    private List<RolesDto> roles;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String photo;




}
