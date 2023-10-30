package com.example.gymbuddy.service.impl;

import com.example.gymbuddy.dto.PlanDto;
import com.example.gymbuddy.mapper.IPlanMapper;
import com.example.gymbuddy.repository.IPlanRepository;
import com.example.gymbuddy.service.interfaces.IUsuarioPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioPlanServiceImpl implements IUsuarioPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public List<PlanDto> findAllPlans() {
        return this.planRepository.findAll().stream()
                .map(IPlanMapper.INSTANCE::toPlanDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDto createPlan(PlanDto planDto) {

        var planToCreate = IPlanMapper.INSTANCE.toPlanEntity(planDto);

        planToCreate.setCreationDate(new Date());

        return IPlanMapper.INSTANCE.toPlanDto(this.planRepository.save(planToCreate));
    }

    @Override
    public PlanDto updatePlan(PlanDto planDto) throws Exception {

        var planToUpdate = this.planRepository.findById(planDto.getId()).orElseThrow(() -> new Exception("NO EXISTE UN PLAN CON LOS CRITERIOS INGRESADOS."));

        planToUpdate.setUpdateDate(new Date());
        planToUpdate.setName(planDto.getName());
        planToUpdate.setDescription(planDto.getDescription());
        planToUpdate.setValue(planDto.getValue());

        return IPlanMapper.INSTANCE.toPlanDto(this.planRepository.save(planToUpdate));
    }

    @Override
    public Boolean deletePlan(Long idPlan) {

        var planToDelete = this.planRepository.findById(idPlan);

        if (planToDelete.isPresent()) {
            this.planRepository.deleteById(idPlan);
            return true;
        } else {
            return false;
        }

    }


}
