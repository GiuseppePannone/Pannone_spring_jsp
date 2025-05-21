package com.example.demo.controller;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/discenti")
public class DiscenteController {

    @Autowired
    DiscenteService discenteService;

    @GetMapping("/lista")
   public List<DiscenteDTO> findDiscenti(){
        return discenteService.findAll();
    }


    @PostMapping
    public ResponseEntity<DiscenteDTO> create(@RequestBody DiscenteDTO discenteDTO){
        return new ResponseEntity<>(discenteService.creaDiscente(discenteDTO), HttpStatus.CREATED);
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
