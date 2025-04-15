package com.sp.motors.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Talleres_mecánicos")
public class TallerMecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, name = "nombre_taller")
    private String nombreTaller;

    @Column(nullable = false)
    private String domicilio;

    @Column(nullable = false)
    private String email;

    @Column(length = 500)
    private String descripcion;

    @Column
    private String banco;

    @Column(nullable = false)
    private Long telefono;

    @ManyToMany
    @JoinTable(
            name = "taller_gasto", // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "taller_id"),
            inverseJoinColumns = @JoinColumn(name = "gasto_id")
    )
    private List<GastoVehiculo> gastos;

}
