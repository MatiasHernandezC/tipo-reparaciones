package com.monolitico.autofix.services;

import com.monolitico.autofix.entities.VehiculoEntity;
import com.monolitico.autofix.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;

    public ArrayList<VehiculoEntity> obtenerVehiculos(){
        return (ArrayList<VehiculoEntity>) vehiculoRepository.findAll();
    }

    public VehiculoEntity guardarVehiculo(VehiculoEntity usuario){
        return vehiculoRepository.save(usuario);
    }

    public void guardarVehiculo(String Patente, String Marca, String Modelo, String Tipo, Date Ano_fabricacion, String Tipo_motor, Integer N_asientos){
        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPatente(Patente);
        vehiculo.setMarca(Marca);
        vehiculo.setModelo(Modelo);
        vehiculo.setTipo(Tipo);
        vehiculo.setAno_fabricacion(Ano_fabricacion);
        vehiculo.setTipo_motor(Tipo_motor);
        vehiculo.setN_asientos(N_asientos);
        vehiculoRepository.save(vehiculo);
    }
    public Optional<VehiculoEntity> obtenerPorId(String id){
        return vehiculoRepository.findById(id);
    }
    public VehiculoEntity obtenerPorPatente(String patente){
        return vehiculoRepository.findByPatente(patente);
    }
    public boolean eliminarVehiculo(String id) {
        try{
            vehiculoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}