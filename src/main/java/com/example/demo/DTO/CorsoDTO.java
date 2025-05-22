package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CorsoDTO {
    private Long id;
    private String nomeCorso;
    private int oreCorso;
    private int annoAccademico;
    private DocenteCorsoListDTO docente;
    private List<DiscenteCorsoListDTO> discenti;

}
