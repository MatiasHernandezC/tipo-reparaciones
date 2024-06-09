package com.monolitico.autofix.services;

import com.monolitico.autofix.entities.Rep_TipoRepEntity;
import com.monolitico.autofix.repositories.Rep_TipoRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class Rep_TipoRepService {
    @Autowired
    Rep_TipoRepRepository rep_TipoRepRepository;

    public ArrayList<Rep_TipoRepEntity> obtenerRep_TipoReps(){
        return (ArrayList<Rep_TipoRepEntity>) rep_TipoRepRepository.findAll();
    }

    public Rep_TipoRepEntity guardarRep_TipoRep(Rep_TipoRepEntity usuario){
        return rep_TipoRepRepository.save(usuario);
    }

    public void guardarRep_TipoRep(Long id_TipoReparacion, Long id_Reparacion, Integer Activo){
        Rep_TipoRepEntity nueva = new Rep_TipoRepEntity();
        nueva.setId_TipoReparacion(id_TipoReparacion);
        nueva.setId_Reparacion(id_Reparacion);
        nueva.setActivo(Activo);
        rep_TipoRepRepository.save(nueva);
    }
    public Optional<Rep_TipoRepEntity> obtenerPorId(String id){
        return rep_TipoRepRepository.findById(id);
    }

    public boolean eliminarRep_TipoRep(String id) {
        try{
            rep_TipoRepRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}