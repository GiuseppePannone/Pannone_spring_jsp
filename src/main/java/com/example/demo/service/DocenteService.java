package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {


    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private DocenteMapper docenteMapper;

    @Autowired
    private CorsoRepository corsoRepository;

    public List<DocenteDTO> findAll() {
        return docenteMapper.entityListToDtoList(docenteRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    public DocenteDTO findById(Long id) {
        return docenteMapper.toDto(docenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Docente non trovato")
        ));
    }

    public DocenteDTO creaDocente(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        return docenteMapper.toDto(docenteRepository.save(docente));
    }

    public DocenteDTO update(Long id, DocenteDTO docenteDTO) {
        docenteDTO.setId(id);
        Docente oldDocente = docenteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Docente non trovato")
        );
        Docente docente = docenteMapper.toEntity(docenteDTO);

       List<Corso> corsi =  docente.getCorsi().stream()
               .map(corse -> corsoRepository.findById(corse.getId()).orElseThrow())
               .collect(Collectors.toList());

        for(Corso corso : oldDocente.getCorsi()) {
            if(!corsi.contains(corso)) {
                corso = corsoRepository.findById(corso.getId()).orElseThrow();
                corso.setDocente(docente);
                corsoRepository.save(corso);

            }
        }

        for(Corso corso : corsi) {
            if(!oldDocente.getCorsi().contains(corso)) {
                corso = corsoRepository.findById(corso.getId()).orElseThrow();
                corso.setDocente(null);
                corsoRepository.save(corso);
            }
        }


        oldDocente.setCorsi(corsi);
            return docenteMapper.toDto(docenteRepository.save(oldDocente));
        }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id).orElseThrow();
        for(Corso corso : docente.getCorsi()) {
            corso.setDocente(null);
        }
        docenteRepository.deleteById(id);
    }

}
