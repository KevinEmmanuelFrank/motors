package com.sp.motors.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Autos")
public class Auto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String color;

    @Column
    private Integer anio;

    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private boolean enVenta;

    @Column(nullable = false)
    private boolean oKm;

    @Column(nullable = false)
    private Integer kilometros;

    @Column(length = 500)
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioEntrada;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioEntradaConGastos;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioSalida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmision transmision;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Puertas puertas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Combustible combustible;

    public enum Transmision {
        AUTOMATICA, SEMIAUTOMATICA, MANUAL
    }

    public enum Puertas {

        DOS, TRES, CUATRO, CINCO

    }

    public enum Combustible {
        GASOLINA, DIESEL, ELECTRICO, HIBRIDO
    }

}
