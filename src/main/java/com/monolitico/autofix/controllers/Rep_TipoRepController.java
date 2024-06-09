package com.monolitico.autofix.controllers;

import com.monolitico.autofix.entities.Rep_TipoRepEntity;
import com.monolitico.autofix.services.Rep_TipoRepService;
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
public class Rep_TipoRepController {
    @Autowired
    Rep_TipoRepService rep_TipoRepService;

    @GetMapping("/listarRepTipoReps")
    public String listar(Model model) {
        ArrayList<Rep_TipoRepEntity>rep_TipoReps=rep_TipoRepService.obtenerRep_TipoReps();
        model.addAttribute("rep_TipoReps",rep_TipoReps);
        return "index";
    }
    @GetMapping("/nuevorepTipoRep")
    public String reparacion(){
        return "nuevorepTipoRep";
    }
    @PostMapping("/nuevorepTipoRep")
    public String nuevoRep_TipoRep(@RequestParam("id_TipoReparacion") Long id_TipoReparacion,
                                  @RequestParam("id_Reparacion") Long id_Reparacion,
                              @RequestParam("Activo") Integer Activo){
        rep_TipoRepService.guardarRep_TipoRep(id_TipoReparacion, id_Reparacion, Activo);
        return "redirect:/nuevorepTipoRep";
    }
}