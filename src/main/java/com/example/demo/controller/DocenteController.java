package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;


    // LISTA
    @GetMapping("/lista")
    public List<DocenteDTO> findDocenti() {
        return docenteService.findAll();
    }
    // SALVA NUOVO
    @PostMapping
    public ResponseEntity<DocenteDTO> create(@RequestBody DocenteDTO docenteDTO) {
        DocenteDTO docenteSalvato = docenteService.creaDocente(docenteDTO);
        return new ResponseEntity<>(docenteSalvato, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> findById(@PathVariable Long id) {
        try{
            DocenteDTO docenteDTO = docenteService.findById(id);
            return ResponseEntity.ok(docenteDTO);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> update(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO docenteSalvato = docenteService.update(id, docenteDTO);
        return ResponseEntity.ok(docenteSalvato);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocente(@PathVariable Long id) {
        docenteService.delete(id);
        return ResponseEntity.ok("Docente eliminato.");
    }
    }

