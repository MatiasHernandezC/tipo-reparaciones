package com.monolitico.autofix.repositories;

import com.monolitico.autofix.entities.Rep_TipoRepEntity;
import com.monolitico.autofix.entities.TipoReparacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface Rep_TipoRepRepository extends JpaRepository<Rep_TipoRepEntity, String> {

    @Query("select e from Rep_TipoRepEntity e where e.id_Reparacion = :id_Reparacion and e.id_TipoReparacion = :id_TipoReparacion " +
            "and e.Activo = 1")
    Rep_TipoRepEntity findByPar(@Param("id_Reparacion") Long id_Reparacion, @Param("id_TipoReparacion") Long id_TipoReparacion);
    @Query("select e from Rep_TipoRepEntity e where e.id_Reparacion = :id_Reparacion and e.Activo = 1")
    ArrayList<Rep_TipoRepEntity> findByReparacion(@Param("id_Reparacion") Long id_Reparacion);

}
