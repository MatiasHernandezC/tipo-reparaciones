package com.monolitico.autofix.controllers;

import com.monolitico.autofix.entities.VehiculoEntity;
import com.monolitico.autofix.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;

@Controller
@RequestMapping
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;

    @GetMapping("/listarVehiculos")
    public String listar(Model model) {
        ArrayList<VehiculoEntity>vehiculos=vehiculoService.obtenerVehiculos();
        model.addAttribute("vehiculos",vehiculos);
        return "index";
    }
    @GetMapping("/nuevo-vehiculo")
    public String vehiculo(){
        return "nuevo-vehiculo";
    }
    @PostMapping("/nuevo-vehiculo")
    public String nuevoVehiculo(@RequestParam("Patente") String Patente,
                                @RequestParam("Marca") String Marca,
                                @RequestParam("Modelo") String Modelo,
                                @RequestParam("Tipo") String Tipo,
                                @RequestParam("Ano_fabricacion") Date Ano_fabricacion,
                                @RequestParam("Tipo_motor") String Tipo_motor,
                                @RequestParam("N_asientos") Integer N_asientos){
        vehiculoService.guardarVehiculo(Patente, Marca, Modelo, Tipo, Ano_fabricacion, Tipo_motor, N_asientos);
        return "redirect:/nuevo-vehiculo";
    }
}