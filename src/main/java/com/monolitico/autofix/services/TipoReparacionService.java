package com.monolitico.autofix.services;

import com.monolitico.autofix.entities.TipoReparacionEntity;
import com.monolitico.autofix.repositories.TipoReparacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TipoReparacionService {
    @Autowired
    TipoReparacionRepository tipoReparacionRepository;

    public ArrayList<TipoReparacionEntity> obtenerTipoReparaciones(){
        return (ArrayList<TipoReparacionEntity>) tipoReparacionRepository.findAll();
    }

    public TipoReparacionEntity guardarTipoReparacion(TipoReparacionEntity usuario){
        return tipoReparacionRepository.save(usuario);
    }

    public void guardarTipoReparacion(String Tipo_motor, String Tipo_reparacion, Integer Precio){
        TipoReparacionEntity nueva = new TipoReparacionEntity();
        nueva.setTipo_motor(Tipo_motor);
        nueva.setTipo_reparacion(Tipo_reparacion);
        nueva.setPrecio(Precio);
        tipoReparacionRepository.save(nueva);
    }
    public Optional<TipoReparacionEntity> obtenerPorId(String id){
        return tipoReparacionRepository.findById(id);
    }

    public boolean eliminarTipoReparacion(String id) {
        try{
            tipoReparacionRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}