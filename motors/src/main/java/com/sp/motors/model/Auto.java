package com.sp.motors.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Autos")
public class Auto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String color;


    private String anio;

    @Column(nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private boolean enVenta;

    @Column(nullable = false)
    private boolean oKm;

    @Column(nullable = false)
    private String kilometros;

    @Column
    private String descripcion;

    @Column
    private Double precioEntrada;

    @Column
    private Double precioEntradaConGastos;

    @Column
    private Double precioSalida;

    @Enumerated(EnumType.STRING)
    private transmision transmision;

    @Enumerated(EnumType.STRING)
    private Puertas puertas;

    @Enumerated(EnumType.STRING)
    private Combustible combustible;

    public enum transmision {
        AUTOMATICA, SEMIAUTOMATICA, MANUAL
    }

    public enum Puertas {
        UNA, DOS, TRES, CUATRO, CINCO, SEIS
    }

    public enum Combustible {
        GASOLINA, DIESEL, ELECTRICO
    }

}
