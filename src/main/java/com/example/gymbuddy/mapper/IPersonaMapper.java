package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.PersonaDto;
import com.example.gymbuddy.dto.RolesDto;
import com.example.gymbuddy.model.Person;
import com.example.gymbuddy.model.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

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
    @Mapping(target = "rolPersona", source = "user.userRoles", qualifiedByName = "userRolMapping")
    @Mapping(target = "idPlan", source = "user.userPlans.plans.id")
    @Mapping(target = "idTipoDocumento", source = "user.person.idTypeDocument")
    @Mapping(target = "fechaDesdePlan", source = "user.userPlans.startDate")
    @Mapping(target = "fechaHastaPlan", source = "user.userPlans.endDate")
    PersonaDto toPersonaDto(Person person);

    @Named("userRolMapping")
    default List<RolesDto> userRolMapping(List<UserRoles> userRoles) {

        return (userRoles == null) ? null :  userRoles.stream()
                .map(IUsuarioMapper.INSTANCE::toRolesDto)
                .collect(Collectors.toList());
    }
}
