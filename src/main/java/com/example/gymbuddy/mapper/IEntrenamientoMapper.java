package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.TrainingDto;
import com.example.gymbuddy.dto.UserTrainingDto;
import com.example.gymbuddy.model.Training;
import com.example.gymbuddy.model.UserTraining;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IEntrenamientoMapper {

    IEntrenamientoMapper INSTANCE = Mappers.getMapper(IEntrenamientoMapper.class);


    @Mapping(target = "nombreClase", source = "name")
    @Mapping(target = "descripcionClase", source = "description")
    @Mapping(target = "inicioClase", source = "startDate")
    @Mapping(target = "finClase", source = "endDate")
    @Mapping(target = "foto", source = "photo")
    @Mapping(target = "usuarioInscritos", source = "userTrainings", qualifiedByName = "entrenamientoUsersMapping")
    TrainingDto toTrainingDto(Training entrenamiento);


    @Mapping(target = "name", source = "nombreClase")
    @Mapping(target = "description", source = "descripcionClase")
    @Mapping(target = "startDate", source = "inicioClase")
    @Mapping(target = "endDate", source = "finClase")
    @Mapping(target = "photo", source = "foto")
    @Mapping(target = "userTrainings", ignore = true)
    Training toTrainingEntity(TrainingDto entrenamiento);

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idPersona", source = "user.person.id")
    @Mapping(target = "nombreUser", source = "user.userName")
    @Mapping(target = "nombre", source = "user.person.name")
    @Mapping(target = "apellido", source = "user.person.lastName")
    UserTrainingDto toUserTrainingDto(UserTraining userTraining);


    @Named("entrenamientoUsersMapping")
    default List<UserTrainingDto> entrenamientoUsersMapping(List<UserTraining> userTrainings) {

        return (userTrainings == null) ? null :  userTrainings.stream()
                .map(IEntrenamientoMapper.INSTANCE::toUserTrainingDto)
                .collect(Collectors.toList());
    }


}
