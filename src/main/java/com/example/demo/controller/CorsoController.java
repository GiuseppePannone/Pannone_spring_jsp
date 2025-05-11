package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
    private final CorsoService corsoService;

    @Autowired
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
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
        modelAndView.addObject("corso", corso);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute("corso") Corso corso, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-corso");
            return modelAndView;
        }
        corsoService.save(corso);
        modelAndView.setViewName("redirect:/corsi/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corsoService.get(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("corso") Corso corso, BindingResult br){
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
