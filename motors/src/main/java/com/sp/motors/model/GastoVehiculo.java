package com.sp.motors.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Gastos_de_vehículos")
public class GastoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double costo;

    @Column
    private boolean estadoDelPago;

    @Column
    private String formaDePago;

    @Column
    private LocalDate fechaDeEntrega;

    @ManyToMany(mappedBy = "gastos")
    private List<TallerMecanico> talleres;
}
