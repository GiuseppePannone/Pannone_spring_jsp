package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository){
        this.corsoRepository = corsoRepository;
    }

    @Autowired
    private CorsoMapper corsoMapper;


    public List<CorsoDTO> findAll() {
        return corsoMapper.convertFromEntityListToDTOList(corsoRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));

    }

    public CorsoDTO findById(Long id) {
        return corsoMapper.convertFromEntitytoDTO(corsoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Corso non trovato")
        ));
    }

    public CorsoDTO creaCorso(CorsoDTO corsoDTO) {
       Corso corso = corsoMapper.convertFromDTOtoEntity(corsoDTO);
       return corsoMapper.convertFromEntitytoDTO(corsoRepository.save(corso));
    }

    public CorsoDTO update(Long id, CorsoDTO corsoDTO) {
        this.corsoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Corso non trovato")
        );
        Corso corso;
       corso = corsoMapper.convertFromDTOtoEntity(corsoDTO);
       return corsoMapper.convertFromEntitytoDTO(corsoRepository.save(corso));
    }


    public void delete(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();
        corsoRepository.deleteById(id);
    }



}
