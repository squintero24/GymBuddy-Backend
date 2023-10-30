package com.example.gymbuddy.service.interfaces;

import com.example.gymbuddy.dto.UsuarioDto;
import org.springframework.stereotype.Service;

public interface IUsuarioService {

    UsuarioDto actualizarUsuarioPassword(UsuarioDto usuarioDto) throws Exception;

    Boolean eliminarUsuario(Long idUsuarioAEliminar);

    UsuarioDto iniciarSesion(UsuarioDto usuarioDto) throws Exception;

}
