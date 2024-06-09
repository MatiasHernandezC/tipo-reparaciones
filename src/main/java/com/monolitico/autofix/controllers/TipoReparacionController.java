package com.monolitico.autofix.controllers;

import com.monolitico.autofix.entities.TipoReparacionEntity;
import com.monolitico.autofix.services.TipoReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
@RequestMapping
public class TipoReparacionController {
    @Autowired
    TipoReparacionService tipoReparacionService;

    @GetMapping("/listarTipoReparaciones")
    public String listar(Model model) {
        ArrayList<TipoReparacionEntity>tipoReparaciones=tipoReparacionService.obtenerTipoReparaciones();
        model.addAttribute("tipoReparaciones",tipoReparaciones);
        return "index";
    }
    @GetMapping("/nuevo-tipo-reparacion")
    public String reparacion(){
        return "nuevo-tipo-reparacion";
    }
    @PostMapping("/nuevo-tipo-reparacion")
    public String nuevoTipoReparacion(@RequestParam("Tipo_motor") String Tipo_motor,
                                  @RequestParam("Tipo_reparacion") String Tipo_reparacion,
                              @RequestParam("Precio") Integer Precio){
        tipoReparacionService.guardarTipoReparacion(Tipo_motor, Tipo_reparacion, Precio);
        return "redirect:/nuevo-tipo-reparacion";
    }
}