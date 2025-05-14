package com.example.demo.controller;

import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/docenti")
    public String listDocenti(Model model){
        return "list-docenti";
    }

    @GetMapping("/discenti")
    public String listDiscenti(Model model){
        return "list-discenti";
    }

    @GetMapping("/corsi")
    public String listCorsi(Model model){
        return "list-corsi";
    }

}
