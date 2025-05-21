package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "discente")
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_Discente", nullable = false)
    private String nome;

    @Column(name = "cognome_Discente", nullable = false)
    private String cognome;

    @Column(nullable = false)
    private Date dataDiNascita;

    @Column
    private String cittaDiResidenza;

    @Column(nullable = false)
    private int voto;

    @ManyToMany(targetEntity = Corso.class, cascade = {CascadeType.DETACH})
    @JoinTable(name = "corso_alunno",
    joinColumns = {@JoinColumn(name = "id_alunno", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "id_corso", referencedColumnName = "id")})
    private List<Corso> corsi;


}
