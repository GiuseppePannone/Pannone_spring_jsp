package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository){
        this.corsoRepository = corsoRepository;
    }

    @Autowired
    private CorsoMapper corsoMapper;

    public List<Corso> findAll() {
        return corsoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public CorsoDTO get(Long id) {
        return corsoMapper.convertFromEntitytoDTO(corsoRepository.findById(id).orElseThrow());
    }

    public Corso save(CorsoDTO c) {
        return corsoRepository.save(corsoMapper.convertFromDTOtoEntity(c));
    }

    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }


}
