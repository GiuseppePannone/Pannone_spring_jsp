package com.example.demo.DTO;

import com.example.demo.entity.Docente;
import lombok.Data;

@Data
public class CorsoDTO {
    private Long id;
    private String nomeCorso;
    private int oreCorso;
    private int annoAccademico;
    private DocenteDTO docente;
}
