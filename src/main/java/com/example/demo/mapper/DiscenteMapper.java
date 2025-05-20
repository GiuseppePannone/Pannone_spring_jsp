package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscenteMapper {

    @Autowired
    public void setCorsoMapper(CorsoMapper corsoMapper) {
    }

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
        if (discente.getCorsos() != null) {
            // Just map course IDs or course names, avoid full nested DTO conversion
            List<Long> corsoIds = discente.getCorsos().stream()
                    .map(Corso::getId)
                    .collect(Collectors.toList());
            dto.setCorsiIDs(corsoIds);
        }

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
