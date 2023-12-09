package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.RolesDto;
import com.example.gymbuddy.dto.UsuarioDto;
import com.example.gymbuddy.model.Roles;
import com.example.gymbuddy.model.UserRoles;
import com.example.gymbuddy.model.Users;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IUsuarioMapper {

    IUsuarioMapper INSTANCE = Mappers.getMapper(IUsuarioMapper.class);

    @Mapping(target = "fechaCreacionUser", source = "creationDate")
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "passwd", source = "password")
    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "userRolMapping")
    @Mapping(target = "name", source = "person.name")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "email", source = "person.email")
    @Mapping(target = "photo", source = "person.photo")
    @Mapping(target = "phoneNumber", source = "person.phoneNumber")
    UsuarioDto toUsuarioDto(Users users);

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "password", source = "passwd")
    @Mapping(target = "userValido", source = "userValido")
    Users toUsuarioEntity(UsuarioDto usuarioDto);

    @Mapping(target = "nombreRol", source = "roles.name")
    @Mapping(target = "fechaCreacion", source = "creationDate")
    @Mapping(target = "idUserRol", source = "id")
    RolesDto toRolesDto(UserRoles userRoles);

    @Named("userRolMapping")
    default List<RolesDto> userRolMapping(List<UserRoles> userRoles) {

        return (userRoles == null) ? null :  userRoles.stream()
                .map(IUsuarioMapper.INSTANCE::toRolesDto)
                .collect(Collectors.toList());
    }

}
