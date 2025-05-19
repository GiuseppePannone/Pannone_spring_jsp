package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository){
        this.corsoRepository = corsoRepository;
    }

    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    private DiscenteService discenteService;


    public List<Corso> findAll() {
        return corsoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public CorsoDTO get(Long id) {
        return corsoMapper.convertFromEntitytoDTO(corsoRepository.findById(id).orElseThrow());
    }

    public Corso save(CorsoDTO c) {
        Corso corso = corsoMapper.convertFromDTOtoEntity(c);
        if (c.getDiscentiIds() != null && !c.getDiscentiIds().isEmpty()) {
            List<Discente> discentiEntities = c.getDiscentiIds().stream()
                    .map(discenteService::getEntityById)  // Assuming this method returns Discente entity
                    .collect(Collectors.toList());
            corso.setDiscenti(discentiEntities);
        }
        return corsoRepository.save(corso);
    }

    public Corso saveEntity(Corso corso) {
        return corsoRepository.save(corso);
    }


    public void delete(Long id) {

        corsoRepository.deleteById(id);
    }


}
