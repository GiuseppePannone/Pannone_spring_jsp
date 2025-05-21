package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;


    @GetMapping("/lista")
    public List<CorsoDTO> findCorsi(){
        return corsoService.findAll();
    }


    @PostMapping
    public ResponseEntity<CorsoDTO> creaCorso(@RequestBody CorsoDTO corsoDTO){
        CorsoDTO corsoSalvato = corsoService.creaCorso(corsoDTO);
        return new ResponseEntity<>(corsoSalvato, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CorsoDTO> getById(@PathVariable Long id){
        try{
            CorsoDTO corsoDTO = corsoService.findById(id);
            return ResponseEntity.ok(corsoDTO);
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorsoDTO> update(@PathVariable Long id, @RequestBody CorsoDTO corsoDTO) {
        CorsoDTO corsoSalvato = corsoService.update(id, corsoDTO);
        return ResponseEntity.ok(corsoSalvato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            corsoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
