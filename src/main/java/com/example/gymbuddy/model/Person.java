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
@Table(name = "person", schema = "public")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "num_document")
    private String numDocument;

    @Column(name = "photo")
    private String photo;

    @Column(name = "id_type_document")
    private Long idTypeDocument;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person", orphanRemoval = true)
    @JsonIgnore
    private Users user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_document", insertable = false, updatable = false)
    @JsonIgnore
    private DocumentType documentType;

}
