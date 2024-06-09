package com.monolitico.autofix.repositories;

import com.monolitico.autofix.entities.ReparacionEntity;
import com.monolitico.autofix.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReparacionRepository extends JpaRepository<ReparacionEntity, String> {

    @Query("SELECT MAX(e.id) FROM ReparacionEntity e")
    ReparacionEntity ultimoId();
}
