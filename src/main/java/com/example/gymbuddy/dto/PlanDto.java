package com.example.gymbuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PlanDto {

    private Long id;

    private String name;

    private String description;

    private Double value;

    private Date creationDate;

    private Date updateDate;

    private Long numeroUsuarioPlan;

    private List<UserPlanDto> usuariosPlan;

}
