package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne(fetch= FetchType.LAZY,  cascade= CascadeType.DETACH)
    @JoinColumn(name = "id_docente", referencedColumnName = "id")
    private Docente docente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "corso_alunno",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_alunno"))
    private List<Discente> discenti;

    public String getDiscentiIds() {
        return discenti == null ? "" : discenti.stream()
                .map(discente -> discente.getId().toString())
                .collect(Collectors.joining(","));
    }

    /*public Corso(){}
    public Corso(String nomeCorso, int oreCorso, int annoAccademico, Docente docente){
        this.nomeCorso = nomeCorso;
        this.oreCorso = oreCorso;
        this.annoAccademico = annoAccademico;
        this.docente = docente;
    }*/



    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public int getOreCorso() {
        return oreCorso;
    }

    public void setOreCorso(int oreCorso) {
        this.oreCorso = oreCorso;
    }

    public int getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(int annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }*/
}
