package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.model.Plans;
import com.example.gymbuddy.model.UserPlans;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPlanMapper {

    IPlanMapper INSTANCE = Mappers.getMapper(IPlanMapper.class);

    Plans toPlanEntity(PlanDto planDto);

    @Mapping(target = "numeroUsuarioPlan", source = "userPlans", qualifiedByName = "countUsersByPlan")
    PlanDto toPlanDto(Plans plans);

    @Named("countUsersByPlan")
    default Long countUsersByPlan(List<UserPlans> userPlans){
        return userPlans != null ? Long.valueOf(userPlans.size()) : 0;
    }
}
