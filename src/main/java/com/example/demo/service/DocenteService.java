package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    @Autowired
    private CorsoService corsoService;
    @Autowired
    private CorsoMapper corsoMapper;

    public List<Docente> findAll() {
        return docenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public DocenteDTO get(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow();
        DocenteDTO dto = docenteMapper.convertFromEntityToDTO(docente);
        if(docente.getCorsi() != null){
            List<CorsoDTO> corsiDTO = docente.getCorsi().stream()
                    .map(corso -> corsoMapper.convertFromEntitytoDTO(corso))
                    .collect(Collectors.toList());
            dto.setCorsi(corsiDTO);
            dto.setCorsiIds(corsiDTO.stream().map(CorsoDTO::getId).collect(Collectors.toList()));
        }
        return dto;

    }

    public Docente save(DocenteDTO d) {
        Docente docente = docenteMapper.convertFromDTOtoEntity(d);
        if(d.getCorsiIds() != null && !d.getCorsiIds().isEmpty()){
            List<Corso> corsi = d.getCorsiIds().stream()
                    .map(corsoService::getEntityById)
                    .collect(Collectors.toList());
            corsi.forEach(corso -> corso.setDocente(docente));
            docente.setCorsi(corsi);
        } else {
            docente.setCorsi(Collections.emptyList());
        }
        return docenteRepository.save(docente);
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow();
        for(Corso corso : docente.getCorsi()) {
            corso.setDocente(null);
        }
        docenteRepository.deleteById(id);
    }


    public Docente getEntityById(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doccente not found with id " + id));
    }
}
