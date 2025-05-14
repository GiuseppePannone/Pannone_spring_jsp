package com.example.demo.service;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.DiscenteMapper;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository DiscenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;

    public List<Discente> findAll() {
        return DiscenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public DiscenteDTO get(Long id) {
        return discenteMapper.convertFromEntityToDTO(DiscenteRepository.findById(id).orElseThrow());
    }

    public Discente save(DiscenteDTO dto) {
        return DiscenteRepository.save(discenteMapper.convertFromDTOtoEntity(dto));
    }

    public void delete(Long id) {
        DiscenteRepository.deleteById(id);
    }

    public List<Discente> findByNameOrLastname(String keyword){
        return DiscenteRepository.findByNameorLastName(keyword);
    }

    public List<Discente> findPassedStudent() {
        return DiscenteRepository.findPassedStudent(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Discente> findByCity(String citta) {
        return DiscenteRepository.findByCity(citta);
    }
}
