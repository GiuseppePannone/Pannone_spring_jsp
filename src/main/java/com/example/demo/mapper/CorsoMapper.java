package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoMapper {
    @Autowired
    private DocenteMapper docenteMapper;

    @Autowired
    private DiscenteMapper discenteMapper;


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
        if(corso.getDiscenti() != null) {
            List<DiscenteDTO> discenteDTOList = corso.getDiscenti().stream()
                    .map(discenteMapper::convertFromEntityToDTO)
                    .collect(Collectors.toList());
            dto.setDiscenti(discenteDTOList);
            List<Long> discentiIds = discenteDTOList.stream()
                    .map(DiscenteDTO::getId)
                    .collect(Collectors.toList());
            dto.setDiscentiIds(discentiIds);
        }
        return dto;
    }

    public Corso convertFromDTOtoEntity(CorsoDTO dto){
        Corso corso = new Corso();
        corso.setId(dto.getId());
        corso.setNomeCorso(dto.getNomeCorso());
        corso.setOreCorso(dto.getOreCorso());
        corso.setAnnoAccademico(dto.getAnnoAccademico());
        if(dto.getDocente() != null) {
            Docente docente = docenteMapper.convertFromDTOtoEntity(dto.getDocente());
            corso.setDocente(docente);  // Set the Docente for the Corso
        } else {
            corso.setDocente(null);  // If there's no docente, set it as null
        }



        /*if(corso.getDocente() != null) {
            corso.setDocente(docenteMapper.convertFromDTOtoEntity(dto.getDocente()));
        } else {
            corso.setDocente(null);
        }*/

        return corso;
    }
}
