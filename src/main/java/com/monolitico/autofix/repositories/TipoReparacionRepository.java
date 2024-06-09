package com.monolitico.autofix.repositories;

import com.monolitico.autofix.entities.TipoReparacionEntity;
import com.monolitico.autofix.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TipoReparacionRepository extends JpaRepository<TipoReparacionEntity, String> {

    @Query("select e from TipoReparacionEntity e where e.Tipo_reparacion = :tipo_reparacion")
    ArrayList<TipoReparacionEntity> findByTipo_reparacion(@Param("tipo_reparacion") String tipo_reparacion);
    @Query("select e from TipoReparacionEntity e where e.id = :id")
    TipoReparacionEntity findById1(@Param("id") Long id);
    @Query("select e from TipoReparacionEntity e where e.Tipo_motor = :tipo_motor")
    ArrayList<TipoReparacionEntity> findByTipo_motor(@Param("tipo_motor") String tipo_motor);
    @Query("select e from TipoReparacionEntity e where e.Tipo_motor = :tipo_motor and e.Tipo_reparacion = :tipo_reparacion")
    TipoReparacionEntity findByPar(@Param("tipo_motor") String tipo_motor, @Param("tipo_reparacion") String tipo_reparacion);
}
