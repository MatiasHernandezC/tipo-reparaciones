package com.monolitico.autofix.services;

import com.monolitico.autofix.entities.DescBonoEntity;
import com.monolitico.autofix.repositories.DescBonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DescBonoService {
    @Autowired
    DescBonoRepository descBonoRepository;

    public ArrayList<DescBonoEntity> obtenerDescBonos(){
        return (ArrayList<DescBonoEntity>) descBonoRepository.findAll();
    }

    public DescBonoEntity guardarDescBono(DescBonoEntity usuario){
        return descBonoRepository.save(usuario);
    }

    public void guardarDescBono(String marca, Integer cant_Bonos, Integer monto){
        DescBonoEntity nueva = new DescBonoEntity();
        nueva.setCant_Bonos(cant_Bonos);
        nueva.setMarca(marca);
        nueva.setMonto(monto);
        descBonoRepository.save(nueva);
    }
    public Optional<DescBonoEntity> obtenerPorId(String id){
        return descBonoRepository.findById(id);
    }

    public boolean eliminarDescBono(String id) {
        try{
            descBonoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}