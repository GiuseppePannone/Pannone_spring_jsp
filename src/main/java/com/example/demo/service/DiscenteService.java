package com.example.demo.service;

import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository DiscenteRepository;

    public List<Discente> findAll() {return DiscenteRepository.findAll();}

    public Discente get(Long id) { return DiscenteRepository.findById(id).orElseThrow();}

    public Discente save(Discente a) {return DiscenteRepository.save(a);}

    public void delete(Long id) {DiscenteRepository.deleteById(id);}

    public List<Discente> findByNameOrLastname(String keyword){
        return DiscenteRepository.findByNameorLastName(keyword);
    }

    public List<Discente> findPassedStudent() {
        return DiscenteRepository.findPassedStudent();
    }

    public List<Discente> findByCity(String citta) {
        return DiscenteRepository.findByCity(citta);
    }
}
