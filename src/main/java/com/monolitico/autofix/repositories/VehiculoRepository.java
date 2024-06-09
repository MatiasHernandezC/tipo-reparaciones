package com.monolitico.autofix.repositories;

import com.monolitico.autofix.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, String> {

    @Query("select e from VehiculoEntity e where e.Patente = :patente")
    VehiculoEntity findByPatente(@Param("patente") String patente);

}
