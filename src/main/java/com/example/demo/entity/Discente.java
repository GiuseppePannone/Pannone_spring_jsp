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
    private List<Corso> corsos;

    /*public Discente(){}
    public Discente(String nome, String cognome, Date dataDiNascita, String cittaDiResidenza, int voto){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.cittaDiResidenza = cittaDiResidenza;
        this.voto = voto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setCittaDiResidenza(String cittaDiResidenza) {
        this.cittaDiResidenza = cittaDiResidenza;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public void setCorsos(List<Corso> corsos) {
        this.corsos = corsos;
    }*/
}
