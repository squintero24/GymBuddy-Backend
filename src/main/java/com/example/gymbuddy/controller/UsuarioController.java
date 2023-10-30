package com.example.gymbuddy.controller;

import com.example.gymbuddy.dto.UsuarioDto;
import com.example.gymbuddy.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> iniciarSesion(
            @RequestBody UsuarioDto usuarioDto
    ) throws Exception {

        var usuarioLogged = this.usuarioService.iniciarSesion(usuarioDto);

        return new ResponseEntity<>(usuarioLogged, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDto> actualizarUsuario(
            @RequestBody UsuarioDto usuarioDto
    ) throws Exception {

        var usuarioUpdate = this.usuarioService.actualizarUsuarioPassword(usuarioDto);

        return new ResponseEntity<>(usuarioUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(
            @PathVariable(name = "id") Long idUser
    ) {
        var deletedPerson = this.usuarioService.eliminarUsuario(idUser);

        return (deletedPerson) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }





}
