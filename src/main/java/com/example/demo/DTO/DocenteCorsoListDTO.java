package com.example.demo.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class DocenteCorsoListDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
}
