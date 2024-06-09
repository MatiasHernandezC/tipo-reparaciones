package com.monolitico.autofix.repositories;

import com.monolitico.autofix.entities.DescBonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface DescBonoRepository extends JpaRepository<DescBonoEntity, String> {

    @Query("select e from DescBonoEntity e where e.marca = :marca")
    DescBonoEntity findByMarca(@Param("marca") String marca);
    @Query("update DescBonoEntity set cant_Bonos = :nuevoValor where marca = :marca")
    Void updateByMarca(@Param("nuevoValor") Integer nuevoValor, String marca);
}