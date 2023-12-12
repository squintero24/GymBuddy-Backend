package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.*;
import com.example.gymbuddy.model.Person;
import com.example.gymbuddy.model.UserPlans;
import com.example.gymbuddy.model.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IPersonaMapper {

    IPersonaMapper INSTANCE = Mappers.getMapper(IPersonaMapper.class);

    @Mapping(target = "idTypeDocument", source = "idTipoDocumento")
    Person toPersonaEntity(PersonaDto personaDto);

    @Mapping(target = "idUsuario", source = "user.id")
    @Mapping(target = "nombreUsuario", source = "user.userName")
    @Mapping(target = "userValido", source = "user.userValido")
    @Mapping(target = "userRol", source = "user.userRoles", qualifiedByName = "getLastUserRol")
    @Mapping(target = "userPlan", source = "user.userPlans", qualifiedByName = "getLastUserPlan")
    @Mapping(target = "idTipoDocumento", source = "user.person.idTypeDocument")
    PersonaDto toPersonaDto(Person person);

    @Named("getLastUserRol")
    default UserRoles getLastUserRol(List<UserRoles> userRoles){
        return (userRoles == null) ? null : userRoles.stream()
                .filter(
                        userRol -> userRol.getEndDate() == null
                ).collect(Collectors.toList()).get(0);
    }

    @Named("getLastUserPlan")
    default UserPlans getLastUserPlan(List<UserPlans> userPlans){
        return (userPlans == null) ? null : userPlans.stream()
                .sorted(Comparator.comparing(UserPlans::getId).reversed())
                .collect(Collectors.toList()).get(0);
    }
}
