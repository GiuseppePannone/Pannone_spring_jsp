package com.example.demo.mapper;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiscenteMapper {

    public List<DiscenteDTO> convertFromEntityListToDTOList(List<Discente> discentes) {
        List<DiscenteDTO> discenteDTOList = new ArrayList<>();
        for (Discente discente : discentes) {
            DiscenteDTO discenteDTO = new DiscenteDTO();
            discenteDTO.setId(discente.getId());
            discenteDTO.setNome(discente.getNome());
            discenteDTO.setCognome(discente.getCognome());
            discenteDTO.setDataDiNascita(discente.getDataDiNascita());
            discenteDTO.setCittaDiResidenza(discente.getCittaDiResidenza());
            discenteDTO.setVoto(discente.getVoto());
            discenteDTOList.add(discenteDTO);
        }
        return discenteDTOList;
    }

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
