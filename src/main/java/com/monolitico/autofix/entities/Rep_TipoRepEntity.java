package com.monolitico.autofix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Rep_TipoRep")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rep_TipoRepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long id_TipoReparacion;
    private Long id_Reparacion;
    private Integer Activo;//1 si, 0 no
}
