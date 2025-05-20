package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private CorsoMapper corsoMapper;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Docente> docenti = new ArrayList<>();
        docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteDTO());
        model.addAttribute("isEdit", false);

        List<CorsoDTO> corsi = corsoService.findAll().stream()
                .map(corsoMapper::convertFromEntitytoDTO)
                .collect(Collectors.toList());
        model.addAttribute("corsi", corsi);
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping("/add")
    public String create(@ModelAttribute("docente") DocenteDTO docente,
                         BindingResult br, Model model) {
        if(br.hasErrors()) {
            List<CorsoDTO> corsi = corsoService.findAll().stream()
                    .map(corsoMapper::convertFromEntitytoDTO)
                    .collect(Collectors.toList());
            model.addAttribute("corsi", corsi);
            model.addAttribute("isEdit", false);
            return "form-docente";
        }
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DocenteDTO docenteDTO = docenteService.get(id);
        model.addAttribute("docente", docenteDTO);
        model.addAttribute("isEdit", true);
        List<CorsoDTO> corsi = corsoService.findAll().stream()
                .map(corsoMapper::convertFromEntitytoDTO)
                .collect(Collectors.toList());
        model.addAttribute("corsi", corsi);
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") DocenteDTO docente,
                         BindingResult br, Model model) {
        if (br.hasErrors()) {
            List<CorsoDTO> corsi = corsoService.findAll().stream()
                    .map(corsoMapper::convertFromEntitytoDTO)
                    .collect(Collectors.toList());
            model.addAttribute("corsi", corsi);
            model.addAttribute("isEdit", true);
            return "form-docente";
        }
        docente.setId(id);
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }








}

