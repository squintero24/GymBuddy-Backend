package com.example.gymbuddy.service.interfaces;

import com.example.gymbuddy.dto.PlanDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUsuarioPlanService {

    List<PlanDto> findAllPlans();

    PlanDto createPlan(PlanDto planDto);

    PlanDto updatePlan(PlanDto planDto) throws Exception;


    Boolean deletePlan(Long idPlan);


}
