package com.monolitico.autofix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String Patente;
    private String Marca;
    private String Modelo;
    private String Tipo;
    private Date Ano_fabricacion;
    private String Tipo_motor;
    private Integer N_asientos;
}
