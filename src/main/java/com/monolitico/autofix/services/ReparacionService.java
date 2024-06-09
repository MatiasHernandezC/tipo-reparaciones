package com.monolitico.autofix.services;

import com.monolitico.autofix.entities.ReparacionEntity;
import com.monolitico.autofix.repositories.ReparacionRepository;
import com.monolitico.autofix.entities.VehiculoEntity;
import com.monolitico.autofix.repositories.VehiculoRepository;
import com.monolitico.autofix.entities.TipoReparacionEntity;
import com.monolitico.autofix.repositories.TipoReparacionRepository;
import com.monolitico.autofix.entities.Rep_TipoRepEntity;
import com.monolitico.autofix.repositories.Rep_TipoRepRepository;
import com.monolitico.autofix.entities.DescBonoEntity;
import com.monolitico.autofix.repositories.DescBonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;


@Service
public class ReparacionService {
    public static int IVA = 19;
    @Autowired
    ReparacionRepository reparacionRepository;
    VehiculoRepository vehiculoRepository;
    TipoReparacionRepository tipoReparacionRepository;
    Rep_TipoRepRepository rep_TipoRepRepository;
    DescBonoRepository descBonoRepository;
    public ArrayList<ReparacionEntity> obtenerReparaciones(){
        return (ArrayList<ReparacionEntity>) reparacionRepository.findAll();
    }

    public ReparacionEntity guardarReparacion(ReparacionEntity usuario){
        return reparacionRepository.save(usuario);
    }

    public void guardarReparacion(String Patente, String TipoReparacion,Integer Monto_total, Timestamp Fecha_ingreso,
                                  Timestamp Fecha_retiro, Timestamp Fecha_salida, Integer BonoDisp, Integer Kilometraje){
        ReparacionEntity reparacion = new ReparacionEntity();
        Long ultimoId = reparacionRepository.ultimoId().getId();
        if (reparacionRepository.ultimoId().getId() != null){
            reparacion.setId(ultimoId+1L);
        }
        else {
            reparacion.setId(1L);
        }
        reparacion.setPatente(Patente);
        reparacion.setFecha_ingreso(Fecha_ingreso);
        reparacion.setFecha_retiro(Fecha_retiro);
        reparacion.setFecha_salida(Fecha_salida);
        reparacion.setPagado(0);
        VehiculoEntity vehiculo = vehiculoRepository.findByPatente(Patente);
        String motor = vehiculo.getTipo_motor();

        TipoReparacionEntity tipoReparacion = tipoReparacionRepository.findByPar(motor, TipoReparacion);

        if (rep_TipoRepRepository.findByPar(reparacion.getId(),tipoReparacion.getId()) == null){
            Rep_TipoRepEntity rep_TipoRep = new Rep_TipoRepEntity();
            rep_TipoRep.setId_Reparacion(tipoReparacion.getId());
            rep_TipoRep.setId_Reparacion(reparacion.getId());
            rep_TipoRep.setActivo(1);
            rep_TipoRepRepository.save(rep_TipoRep);
        }
        else{
            return;
        }
        Double costoReparaciones = (double) 0;
        ArrayList<Rep_TipoRepEntity> reparaciones = rep_TipoRepRepository.findByReparacion(reparacion.getId());

        for (int i = 0; i < reparaciones.size(); i++) {
            costoReparaciones = costoReparaciones + tipoReparacionRepository.findById1(reparaciones.get(i).getId()).getPrecio();
        }
        //-------------------RECARGOS-------------------
        double descuentos = 0;

        //RECARGO POR KILOMETRAJE
        if (Kilometraje >=0 && Kilometraje <= 5000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.00;
            }
        }
        else if (Kilometraje > 5000 && Kilometraje <= 12000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.03;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.03;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.05;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.05;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.05;
            }
        }
        else if (Kilometraje > 12000 && Kilometraje <= 25000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.07;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.07;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.09;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.09;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.09;
            }
        }
        else if (Kilometraje > 25000 && Kilometraje <= 40000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.12;
            }
        }
        else if (Kilometraje > 40000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.20;
            }
        }

        //RECARGO POR ANTIGUEDAD DEL VEHICULO
        /*if (vehiculo.getAno_fabricacion().getYear() - fechaActual >= 0 && Kilometraje <= 5000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.00;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.00;
            }
        }
        else if (Kilometraje > 5000 && Kilometraje <= 12000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.03;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.03;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.05;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.05;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.05;
            }
        }
        else if (Kilometraje > 12000 && Kilometraje <= 25000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.07;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.07;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.09;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.09;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.09;
            }
        }
        else if (Kilometraje > 25000 && Kilometraje <= 40000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.12;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.12;
            }
        }
        else if (Kilometraje > 40000){
            if (vehiculo.getTipo().equals("Sedán")){
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Hatchback")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("SUV")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Pickup")) {
                descuentos = descuentos - 0.20;
            }
            else if (vehiculo.getTipo().equals("Furgoneta")) {
                descuentos = descuentos - 0.20;
            }
        }
        */

        //-------------------FIN RECARGOS-------------------

        //-------------------DESCUENTOS-------------------
        //Descuento por numero de reparaciones
        if (reparaciones.size() < 3 && !reparaciones.isEmpty()){
            if (motor.equals("Gasolina")){
                descuentos = descuentos + 0.05;
                //costoReparaciones = costoReparaciones * 0.95;//5%
            } else if (motor.equals("Diésel")) {
                descuentos = descuentos + 0.07;
                //costoReparaciones = costoReparaciones * 0.93;//7%
            } else if (motor.equals("Híbrido")) {
                descuentos = descuentos + 0.1;
                //costoReparaciones = costoReparaciones * 0.90;//10%
            } else if (motor.equals("Eléctrico")) {
                descuentos = descuentos + 0.08;
                //costoReparaciones = costoReparaciones * 0.92;//8%
            }
        }
        else if (reparaciones.size() >= 3 && reparaciones.size() < 6) {
            if (motor.equals("Gasolina")){
                descuentos = descuentos + 0.1;
                //costoReparaciones = costoReparaciones * 0.90;//10%
            } else if (motor.equals("Diésel")) {
                descuentos = descuentos + 0.12;
                //costoReparaciones = costoReparaciones * 0.88;//12%
            } else if (motor.equals("Híbrido")) {
                descuentos = descuentos + 0.15;
                //costoReparaciones = costoReparaciones * 0.85;//15%
            } else if (motor.equals("Eléctrico")) {
                descuentos = descuentos + 0.13;
                //costoReparaciones = costoReparaciones * 0.87;//13%
            }
        }
        else if (reparaciones.size() >= 6 && reparaciones.size() < 10) {
            if (motor.equals("Gasolina")){
                descuentos = descuentos + 0.15;
                //costoReparaciones = costoReparaciones * 0.85;//15%
            } else if (motor.equals("Diésel")) {
                descuentos = descuentos + 0.17;
                //costoReparaciones = costoReparaciones * 0.83;//17%
            } else if (motor.equals("Híbrido")) {
                descuentos = descuentos + 0.2;
                //costoReparaciones = costoReparaciones * 0.80;//20%
            } else if (motor.equals("Eléctrico")) {
                descuentos = descuentos + 0.18;
                //costoReparaciones = costoReparaciones * 0.82;//18%
            }
        }
        else if (reparaciones.size() >= 10) {
            if (motor.equals("Gasolina")){
                descuentos = descuentos + 0.2;
                //costoReparaciones = costoReparaciones * 0.80;//20%
            } else if (motor.equals("Diésel")) {
                descuentos = descuentos + 0.22;
                //costoReparaciones = costoReparaciones * 0.78;//22%
            } else if (motor.equals("Híbrido")) {
                descuentos = descuentos + 0.25;
                //costoReparaciones = costoReparaciones * 0.75;//25%
            } else if (motor.equals("Eléctrico")) {
                descuentos = descuentos + 0.23;
                //costoReparaciones = costoReparaciones * 0.77;//23%
            }
        }

        //Descuento por dia de atencion

        // Convertir Timestamp a Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Fecha_ingreso.getTime());
        // Verificar si el día está entre lunes y jueves y la hora está entre 09:00 y 12:00
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if (diaSemana >= Calendar.MONDAY && diaSemana <= Calendar.THURSDAY &&
                hora >= 9 && hora <= 12) {
            descuentos = descuentos + 0.1;
            //costoReparaciones = costoReparaciones*0.90;
        }
        //Aplicar todos los descuentos que sean porcentuales
        costoReparaciones = costoReparaciones * (1-descuentos);
        //Descuento por bono disponible
        //Si BonoDisp == 1, entonces se solicita el bono, si no salta esto
        DescBonoEntity nuevo = new DescBonoEntity();
        if (BonoDisp == 1){
            if (descBonoRepository.findByMarca(vehiculo.getMarca()).getCant_Bonos() > 0){
                costoReparaciones = costoReparaciones - descBonoRepository.findByMarca(vehiculo.getMarca()).getMonto();
                nuevo = descBonoRepository.findByMarca(vehiculo.getMarca());
                nuevo.setCant_Bonos(nuevo.getCant_Bonos()-1);
                descBonoRepository.updateByMarca(nuevo.getCant_Bonos(), vehiculo.getMarca());
            }

        }
        //-------------------Fin DESCUENTOS-------------------

        //Calculo de Monto total
        //Costo Total = [Suma(Reparaciones) + Recargos – Descuentos] + IVA
        reparacion.setMonto_total(costoReparaciones.intValue());

        reparacionRepository.save(reparacion);
    }
    public Optional<ReparacionEntity> obtenerPorId(String id){
        return reparacionRepository.findById(id);
    }

    public boolean eliminarReparacion(String id) {
        try{
            reparacionRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}