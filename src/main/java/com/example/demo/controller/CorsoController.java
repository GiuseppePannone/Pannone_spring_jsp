package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
    private final CorsoService corsoService;
    private final DocenteService docenteService;
    private final DiscenteService discenteService;
    private final CorsoMapper corsoMapper;

    @Autowired
    public CorsoController(CorsoService corsoService, DocenteService docenteService, DiscenteService discenteService, CorsoMapper corsoMapper) {
        this.corsoService = corsoService;
        this.docenteService = docenteService;
        this.discenteService = discenteService;
        this.corsoMapper = corsoMapper;
    }

    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<Corso> corsi = corsoService.findAll();
        modelAndView.setViewName("list-corsi");
        modelAndView.addObject("corsi", corsi);

        return modelAndView;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd(){
        ModelAndView modelAndView = new ModelAndView("form-corso");
        Corso corso = new Corso();

        // Set discentiIds as an empty list for the new course
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setDiscentiIds(new ArrayList<>());  // Initialize as empty list to ensure no students are selected

        List<Docente> docenti = docenteService.findAll();
        List<DiscenteDTO> discenti = discenteService.findAll();

        modelAndView.addObject("corso", corsoDTO);  // Use the CorsoDTO here
        modelAndView.addObject("docenti", docenti);
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("isEdit", false);

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute("corso") CorsoDTO corso, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        if (corso.getDiscentiIds() != null && !corso.getDiscentiIds().isEmpty()) {
            List<Discente> discentiEntities = corso.getDiscentiIds().stream()
                    .map(discenteService::getEntityById)
                    .collect(Collectors.toList());
            Corso corsoEntity = corsoMapper.convertFromDTOtoEntity(corso);
            corsoEntity.setDiscenti(discentiEntities);
            corsoService.saveEntity(corsoEntity);
        } else {
            Corso corsoEntity = corsoMapper.convertFromDTOtoEntity(corso);
            corsoService.saveEntity(corsoEntity);
        }
        modelAndView.setViewName("redirect:/corsi/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("form-corso");
        CorsoDTO corsoDTO = corsoService.get(id);
        List<Docente> docenti = docenteService.findAll();
        List<DiscenteDTO> discenti = discenteService.findAll();

        modelAndView.addObject("corso", corsoDTO);
        modelAndView.addObject("docenti", docenti);
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("isEdit", true);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("corso") CorsoDTO corso, BindingResult br){
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-corso");
            return modelAndView;
        }
        corso.setId(id);
        corsoService.save(corso);
        modelAndView.setViewName("redirect:/corsi");
        return modelAndView;

    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        corsoService.delete(id);
        modelAndView.setViewName("redirect:/corsi/lista");
        return modelAndView;
    }
}
