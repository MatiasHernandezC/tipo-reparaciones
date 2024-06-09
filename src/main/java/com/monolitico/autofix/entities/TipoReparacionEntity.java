package com.monolitico.autofix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TipoReparaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoReparacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String Tipo_reparacion;
    private String Tipo_motor;
    private Integer Precio;
}
