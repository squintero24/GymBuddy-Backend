package com.example.gymbuddy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_person")
    private Long idPerson;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "user_valido")
    private Long userValido;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", insertable = false, updatable = false)
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users", orphanRemoval = true)
    private List<UserRoles> userRoles;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "users")
    private UserPlans userPlans;

}
