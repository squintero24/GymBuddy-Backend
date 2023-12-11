package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.dto.RolesDto;
import com.example.gymbuddy.dto.UserPlanDto;
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
    @Mapping(target = "idRol", source = "user.userRoles", qualifiedByName = "getLastUserRol")
    @Mapping(target = "idPlan", source = "user.userPlans", qualifiedByName = "getLastUserPlan")
    @Mapping(target = "plansDto", source = "user.userPlans", qualifiedByName = "userPlanMapping")
    @Mapping(target = "idTipoDocumento", source = "user.person.idTypeDocument")
    PersonaDto toPersonaDto(Person person);

    @Named("userRolMapping")
    default List<RolesDto> userRolMapping(List<UserRoles> userRoles) {

        return (userRoles == null) ? null :  userRoles.stream()
                .map(IUsuarioMapper.INSTANCE::toRolesDto)
                .collect(Collectors.toList());
    }

    @Named("userPlanMapping")
    default List<UserPlanDto> userPlanMapping(List<UserPlans> userPlans) {
        return (userPlans == null) ? null :  userPlans.stream()
                .map(IPlanMapper.INSTANCE::toUserPlanDto)
                .collect(Collectors.toList());
    }

    @Named("getLastUserRol")
    default Long getLastUserRol(List<UserRoles> userRoles){
        return (userRoles == null) ? null : userRoles.stream()
                .filter(
                        userRol -> userRol.getEndDate() == null
                ).collect(Collectors.toList()).get(0).getIdRol();
    }

    @Named("getLastUserPlan")
    default Long getLastUserPlan(List<UserPlans> userPlans){
        return (userPlans == null) ? null : userPlans.stream()
                .sorted(Comparator.comparing(UserPlans::getEndDate).reversed())
                .collect(Collectors.toList()).get(0).getIdPlan();
    }
}
