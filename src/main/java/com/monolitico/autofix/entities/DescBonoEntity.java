package com.monolitico.autofix.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DescBono")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescBonoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String marca;
    private Integer cant_Bonos;
    private Integer monto;
}
