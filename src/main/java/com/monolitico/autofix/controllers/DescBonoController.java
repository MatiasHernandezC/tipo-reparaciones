package com.monolitico.autofix.controllers;

import com.monolitico.autofix.entities.DescBonoEntity;
import com.monolitico.autofix.services.DescBonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping
public class DescBonoController {
    @Autowired
    DescBonoService descBonoService;

    @GetMapping("/listarDescBonos")
    public String listar(Model model) {
        ArrayList<DescBonoEntity>descBonos=descBonoService.obtenerDescBonos();
        model.addAttribute("descBonos",descBonos);
        return "index";
    }
    @GetMapping("/nuevoDescBono")
    public String reparacion(){
        return "nuevoDescBono";
    }
    @PostMapping("/nuevoDescBono")
    public String nuevoDescBono(@RequestParam("marca") String marca,
                                @RequestParam("cant_Bonos") Integer cantBonos,
                                @RequestParam("monto") Integer monto){
        descBonoService.guardarDescBono(marca, cantBonos, monto);
        return "redirect:/nuevoDescBono";
    }
}