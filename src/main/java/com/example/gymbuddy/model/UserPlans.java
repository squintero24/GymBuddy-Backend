package com.example.gymbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "user_plans", schema = "public")
@Entity
public class UserPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "id_prorroga")
    private Long idProrroga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", updatable = false,insertable = false)
    @JsonIgnore
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", updatable = false,insertable = false)
    @JsonIgnore
    private Plans plans;
}
