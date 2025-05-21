package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private Date dataDiNascita;

    @OneToMany(mappedBy = "docente", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private List<Corso> corsi;


}
