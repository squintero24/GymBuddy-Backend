package com.example.gymbuddy.mapper;

import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.model.Plans;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPlanMapper {

    IPlanMapper INSTANCE = Mappers.getMapper(IPlanMapper.class);

    Plans toPlanEntity(PlanDto planDto);

    PlanDto toPlanDto(Plans plans);
}
