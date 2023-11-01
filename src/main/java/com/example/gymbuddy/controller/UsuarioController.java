package com.example.gymbuddy.controller;

import com.example.gymbuddy.dto.UsuarioDto;
import com.example.gymbuddy.model.DocumentType;
import com.example.gymbuddy.model.Roles;
import com.example.gymbuddy.repository.IRolesRepository;
import com.example.gymbuddy.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolesRepository rolesRepository;

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


    @GetMapping("/roles/all")
    public ResponseEntity<List<Roles>> findAllTypeDocument() {

        var roles = this.rolesRepository.findAll();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(roles, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);

    }





}
