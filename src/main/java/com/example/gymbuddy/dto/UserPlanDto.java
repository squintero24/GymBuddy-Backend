package com.example.gymbuddy.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class UserPlanDto {

    private Long id;

    private Long idPlan;

    private String userName;

    private Long idUser;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

}
