package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.mapper.DiscenteMapper;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiscenteService {
    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;


    public List<DiscenteDTO> findAll() {
        return discenteMapper.convertFromEntityListToDTOList(discenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }


    public DiscenteDTO findById(Long id) {
        return discenteMapper.convertFromEntityToDTO(discenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Discente non trovato")
        ));
    }

    public DiscenteDTO creaDiscente(DiscenteDTO discenteDTO) {
        Discente discente = discenteMapper.convertFromDTOtoEntity(discenteDTO);
        return discenteMapper.convertFromEntityToDTO(discenteRepository.save(discente));
    }

    public DiscenteDTO update(Long id, DiscenteDTO discenteDTO) {
        this.discenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Discente non trovato")
        );
        Discente discente = discenteMapper.convertFromDTOtoEntity(discenteDTO);
        return discenteMapper.convertFromEntityToDTO(discenteRepository.save(discente));
    }

    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow();
        discenteRepository.deleteById(id);
    }
}



