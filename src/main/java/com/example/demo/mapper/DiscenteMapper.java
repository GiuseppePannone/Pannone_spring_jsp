package com.example.demo.mapper;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import org.springframework.stereotype.Component;

@Component
public class DiscenteMapper {
    public DiscenteDTO convertFromEntityToDTO(Discente discente){
        DiscenteDTO dto = new DiscenteDTO();
        dto.setId(discente.getId());
        dto.setNome(discente.getNome());
        dto.setCognome(discente.getCognome());
        dto.setDataDiNascita(discente.getDataDiNascita());
        dto.setCittaDiResidenza(discente.getCittaDiResidenza());
        dto.setVoto(discente.getVoto());
        return dto;
    }
    public Discente convertFromDTOtoEntity(DiscenteDTO dto){
        Discente discente = new Discente();
        discente.setId(dto.getId());
        discente.setNome(dto.getNome());
        discente.setCognome(dto.getCognome());
        discente.setDataDiNascita(dto.getDataDiNascita());
        discente.setCittaDiResidenza(dto.getCittaDiResidenza());
        discente.setVoto(dto.getVoto());
        return discente;
    }
}
