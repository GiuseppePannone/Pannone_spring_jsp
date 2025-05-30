package com.example.demo.DTO;

import com.example.demo.entity.Docente;
import lombok.Data;

import java.util.List;

@Data
public class CorsoDTO {
    private Long id;
    private String nomeCorso;
    private int oreCorso;
    private int annoAccademico;
    private DocenteDTO docente;
    private List<Long> discentiIds;
    private List<DiscenteDTO> discenti;

}
