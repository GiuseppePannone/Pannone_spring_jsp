package com.example.demo.controller;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/discenti")
public class DiscenteController {

    @Autowired
    DiscenteService discenteService;

    @GetMapping("/lista")
   public List<DiscenteDTO> list(){
        return discenteService.findAll();
    }


    @PostMapping
    public ResponseEntity<DiscenteDTO> create(@RequestBody DiscenteDTO discenteDTO){
        DiscenteDTO discenteSalvato = discenteService.creaDiscente(discenteDTO);
        return new ResponseEntity<>(discenteSalvato, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DiscenteDTO> findById(@PathVariable Long id){
        try {
            DiscenteDTO discenteDTO = discenteService.findById(id);
            return ResponseEntity.ok(discenteDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscenteDTO> update(@PathVariable Long id, @RequestBody DiscenteDTO discenteDTO) {
       DiscenteDTO discenteSalvato = discenteService.update(id, discenteDTO);
       return ResponseEntity.ok(discenteSalvato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiscente(@PathVariable Long id){
        discenteService.delete(id);
        return ResponseEntity.ok("Discente eliminato");
    }
}
