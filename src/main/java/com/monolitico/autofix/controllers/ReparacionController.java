package com.monolitico.autofix.controllers;

import com.monolitico.autofix.entities.ReparacionEntity;
import com.monolitico.autofix.services.ReparacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
@RequestMapping
public class ReparacionController {
    @Autowired
    ReparacionService reparacionService;

    @GetMapping("/listarReparaciones")
    public String listar(Model model) {
        ArrayList<ReparacionEntity>reparaciones=reparacionService.obtenerReparaciones();
        model.addAttribute("reparaciones",reparaciones);
        return "index";
    }
    @GetMapping("/nuevo-reparacion")
    public String reparacion(){
        return "nuevo-reparacion";
    }
    @PostMapping("/nuevo-reparacion")
    public String nuevoReparacion(@RequestParam("Patente") String Patente,
                                  @RequestParam("Tipo_reparacion") String Tipo_reparacion,
                              @RequestParam("Monto_total") Integer Monto_total,
                              @RequestParam("Fecha_ingreso") Timestamp Fecha_ingreso,
                              @RequestParam("Fecha_retiro") Timestamp Fecha_retiro,
                              @RequestParam("Fecha_salida") Timestamp Fecha_salida,
                                  @RequestParam("BonoDisp") Integer BonoDisp,
                                  @RequestParam("Kilometraje") Integer Kilometraje){
        reparacionService.guardarReparacion(Patente, Tipo_reparacion, Monto_total, Fecha_ingreso, Fecha_retiro, Fecha_salida, BonoDisp, Kilometraje);
        return "redirect:/nuevo-reparacion";
    }
}