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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {


    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    public List<DocenteDTO> findAll() {
        return docenteMapper.convertFromEntityListToDTOList(docenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public DocenteDTO findById(Long id) {
        return docenteMapper.convertFromEntityToDTO(docenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Docente non trovato")
        ));
    }

    public DocenteDTO creaDocente(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.convertFromDTOtoEntity(docenteDTO);
        return docenteMapper.convertFromEntityToDTO(docenteRepository.save(docente));
    }

    public DocenteDTO update(Long id, DocenteDTO docenteDTO) {
        this.docenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Docente non trovato")
        );
        Docente docente;
            docente = docenteMapper.convertFromDTOtoEntity(docenteDTO);
            return docenteMapper.convertFromEntityToDTO(docenteRepository.save(docente));
        }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow();
        for(Corso corso : docente.getCorsi()) {
            corso.setDocente(null);
        }
        docenteRepository.deleteById(id);
    }

}
