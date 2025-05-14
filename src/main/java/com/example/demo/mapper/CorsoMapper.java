package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorsoMapper {
    @Autowired
    private DocenteMapper docenteMapper;
    public CorsoDTO convertFromEntitytoDTO(Corso corso){
        CorsoDTO dto = new CorsoDTO();
        dto.setId(corso.getId());
        dto.setNomeCorso(corso.getNomeCorso());
        dto.setOreCorso(corso.getOreCorso());
        dto.setAnnoAccademico(corso.getAnnoAccademico());
        if(corso.getDocente() != null){
            dto.setDocente(  docenteMapper.convertFromEntityToDTO(corso.getDocente()));
        }else {
            dto.setDocente(null);
        }
        return dto;
    }

    public Corso convertFromDTOtoEntity(CorsoDTO dto){
        Corso corso = new Corso();
        corso.setId(dto.getId());
        corso.setNomeCorso(dto.getNomeCorso());
        corso.setOreCorso(dto.getOreCorso());
        corso.setAnnoAccademico(dto.getAnnoAccademico());
        if(corso.getDocente() != null) {
            corso.setDocente(docenteMapper.convertFromDTOtoEntity(dto.getDocente()));
        } else {
            corso.setDocente(null);
        }

        return corso;
    }
}
