package com.example.demo.service;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.DiscenteMapper;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscenteService {
    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;


    public List<DiscenteDTO> findAll() {
        return discenteMapper.entityListToDtoList(discenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }


    public DiscenteDTO findById(Long id) {
        return discenteMapper.toDto(discenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Discente non trovato")
        ));
    }

    public DiscenteDTO creaDiscente(DiscenteDTO discenteDTO) {
        Discente discente = discenteMapper.toEntity(discenteDTO);
        return discenteMapper.toDto(discenteRepository.save(discente));
    }

    public DiscenteDTO update(Long id, DiscenteDTO discenteDTO) {
        this.discenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Discente non trovato")
        );
        discenteDTO.setId(id);
        Discente discente = discenteMapper.toEntity(discenteDTO);
        return discenteMapper.toDto(discenteRepository.save(discente));
    }

    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow();
        discenteRepository.deleteById(id);
    }
}



