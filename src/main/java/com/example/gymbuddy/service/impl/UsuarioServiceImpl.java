package com.example.gymbuddy.service.impl;

import com.example.gymbuddy.dto.UsuarioDto;
import com.example.gymbuddy.mapper.IUsuarioMapper;
import com.example.gymbuddy.repository.IUserRepository;
import com.example.gymbuddy.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public UsuarioDto actualizarUsuarioPassword(UsuarioDto usuarioDto) throws Exception {
        var user = this.userRepository.findByUserName(usuarioDto.getUsername())
                .orElseThrow(() -> new Exception("NO EXISTE EL USUARIO CON EL USERNAME INGRESADO"));

        if (user.getPassword().equals(usuarioDto.getPasswd())) {
            throw new Exception("LAS CONTRASEÑAS SON IGUALES.");
        }

        user.setUserValido(1L);
        user.setPassword(usuarioDto.getPasswd());



        return IUsuarioMapper.INSTANCE.toUsuarioDto(this.userRepository.save(user));
    }

    @Override
    public Boolean eliminarUsuario(Long idUsuarioAEliminar) {
        var usuarioToDelete = this.userRepository.findById(idUsuarioAEliminar);

        if (usuarioToDelete.isPresent()) {
            this.userRepository.deleteById(idUsuarioAEliminar);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public UsuarioDto iniciarSesion(UsuarioDto usuarioDto) throws Exception {

        var user = this.userRepository.findByUserName(usuarioDto.getUsername())
                .orElseThrow(() -> new Exception("NO EXISTE EL USUARIO CON EL USERNAME INGRESADO"));

        if (user.getPassword().equals(usuarioDto.getPasswd())){
            return IUsuarioMapper.INSTANCE.toUsuarioDto(user);
        }else {
            throw new Exception("LAS CONTRASEÑAS NO COINCIDEN");
        }

    }
}
