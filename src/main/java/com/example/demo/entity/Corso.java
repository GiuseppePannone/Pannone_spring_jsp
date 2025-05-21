package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "corso")
@Data
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_corso", nullable = false)
    private String nomeCorso;

    @Column(nullable = false)
    private int oreCorso;

    @Column(nullable = false)
    private int annoAccademico;

    @ManyToOne(fetch= FetchType.LAZY,  cascade= {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "id_docente", referencedColumnName = "id")
    private Docente docente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "corso_alunno",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_alunno"))
    private List<Discente> discenti;



}
