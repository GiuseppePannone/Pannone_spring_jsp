package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.mapper.DiscenteMapper;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    private DiscenteMapper discenteMapper;

    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    @Lazy
    private CorsoService corsoService;


    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow();
        DiscenteDTO dto = discenteMapper.convertFromEntityToDTO(discente);
        if(discente.getCorsos() != null) {
            List<CorsoDTO> corsiDTO = discente.getCorsos().stream()
                    .map(corsoMapper::convertFromEntitytoDTO)
                    .collect(Collectors.toList());
            dto.setCorsos(corsiDTO);
        }
        return dto;
    }

    public Discente save(DiscenteDTO dto) {
        Discente discente = discenteMapper.convertFromDTOtoEntity(dto);
        if(dto.getCorsiIDs() != null && !dto.getCorsiIDs().isEmpty()) {
            List<Corso> corsiEntities = dto.getCorsiIDs().stream()
                    .map(corsoService::getEntityById)
                    .toList();
            discente.setCorsos(corsiEntities);
        } else {
            discente.setCorsos(Collections.emptyList());
        }
        return discenteRepository.save(discente);
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
        List<Discente> allDiscenti = discenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return allDiscenti.stream()
                .map(discente -> {
                    DiscenteDTO dto = discenteMapper.convertFromEntityToDTO(discente);
                    if(discente.getCorsos() != null) {
                        List<CorsoDTO> corsiDTO = discente.getCorsos().stream()
                                .map(corsoMapper::convertFromEntitytoDTO)
                                .collect(Collectors.toList());
                        dto.setCorsos(corsiDTO);
                    }
                    return dto;
                }).collect(Collectors.toList());
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

    public List<CorsoDTO> findAllCorsi(){
        return corsoService.findAll().stream()
                .map(corsoMapper::convertFromEntitytoDTO)
                .collect(Collectors.toList());
    }
}
