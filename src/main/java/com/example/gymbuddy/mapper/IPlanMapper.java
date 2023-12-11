package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.dto.UserPlanDto;
import com.example.gymbuddy.dto.UserTrainingDto;
import com.example.gymbuddy.model.Plans;
import com.example.gymbuddy.model.UserPlans;
import com.example.gymbuddy.model.UserTraining;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface IPlanMapper {

    IPlanMapper INSTANCE = Mappers.getMapper(IPlanMapper.class);

    Plans toPlanEntity(PlanDto planDto);

    @Mapping(target = "numeroUsuarioPlan", source = "userPlans", qualifiedByName = "countUsersByPlan")
    @Mapping(target="usuariosPlan", source = "userPlans", qualifiedByName = "planUserMapping")
    PlanDto toPlanDto(Plans plans);

    @Mapping(target = "userName", source = "users.userName")
    UserPlanDto toUserPlanDto(UserPlans userPlans);

    @Named("countUsersByPlan")
    default Long countUsersByPlan(List<UserPlans> userPlans){
        return userPlans != null ? Long.valueOf(userPlans.size()) : 0;
    }

    @Named("planUserMapping")
    default List<UserPlanDto> planUserMapping(List<UserPlans> userPlans) {

        return (userPlans == null) ? null :  userPlans.stream()
                .map(IPlanMapper.INSTANCE::toUserPlanDto)
                .collect(Collectors.toList());
    }
}
