package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    public List<Docente> findAll() {
        return docenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public DocenteDTO get(Long id) {
        return docenteMapper.convertFromEntityToDTO(docenteRepository.findById(id).orElseThrow());
    }

    public Docente save(DocenteDTO d) {
        return docenteRepository.save(docenteMapper.convertFromDTOtoEntity(d));
    }

    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }


}
