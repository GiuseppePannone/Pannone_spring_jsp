package com.example.demo.service;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.DiscenteMapper;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;


    public DiscenteDTO get(Long id) {
        return discenteMapper.convertFromEntityToDTO(discenteRepository.findById(id).orElseThrow());
    }

    public Discente save(DiscenteDTO dto) {
        return discenteRepository.save(discenteMapper.convertFromDTOtoEntity(dto));
    }

    public void delete(Long id) {
        discenteRepository.deleteById(id);
    }


    public String getFilterType(String keyword, String citta) {
        if (keyword != null && !keyword.isEmpty()) return "keyword";
        if (citta != null && !citta.isEmpty()) return "citta";
        return "all";
    }
    public List<DiscenteDTO> filterDiscenti(String keyword, String citta) {
        if (isNotEmpty(keyword)) {
            return findByKeyword(keyword);
        } else if (isNotEmpty(citta)) {
            return findByCity(citta);
        } else {
            return findAll();
        }
    }

    private boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public List<DiscenteDTO> findAllByIds(List<Long> ids) {
        return discenteRepository.findAllById(ids).stream()
                .map(discenteMapper::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }
    public List<DiscenteDTO> findAll() {
        return discenteRepository.findAll(Sort.by(Sort.Order.asc("id")))
                .stream()
                .map(discenteMapper::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<DiscenteDTO> findByKeyword(String keyword) {
        return discenteRepository.findAll()
                .stream()
                .filter(d -> d.getNome().toLowerCase().contains(keyword.toLowerCase()) || d.getCognome().toLowerCase().contains(keyword.toLowerCase()))
                .map(discenteMapper::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<DiscenteDTO> findByCity(String citta) {
        return discenteRepository.findAll()
                .stream()
                .filter(d -> d.getCittaDiResidenza().toLowerCase().contains((citta.toLowerCase())))
                .map(discenteMapper::convertFromEntityToDTO)
                .collect(Collectors.toList());
    }


    public List<DiscenteDTO> findPassedStudent() {
        return discenteMapper.convertFromEntityListToDTOList(discenteRepository.findPassedStudent(Sort.by(Sort.Direction.ASC, "id")));
    }

    public Discente getEntityById(Long id) {
        return discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente not found with id " + id));
    }
}
