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
@Table(name = "user_role", schema = "public")
@Entity
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_role")
    private Long idRol;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    @JsonIgnore
    private Roles roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", updatable = false,insertable = false)
    @JsonIgnore
    private Users users;





}
