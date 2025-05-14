package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocenteMapper {

    public DocenteDTO convertFromEntityToDTO(Docente docente){
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNome(docente.getNome());
        dto.setCognome(docente.getCognome());
        dto.setDataDiNascita(docente.getDataDiNascita());
        return dto;
    }

    public Docente convertFromDTOtoEntity(DocenteDTO dto){
        Docente docente = new Docente();
        docente.setId(dto.getId());
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        docente.setDataDiNascita(dto.getDataDiNascita());
        return docente;
    }
}
