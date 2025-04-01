package com.sp.motors.model;

import com.sp.motors.dto.TallerMecanico_Gasto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Talleres_mecánicos")
public class TallerMecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String nombre_taller;

    @Column(nullable = false)
    private String domicilio;

    @Column(nullable = false)
    private String email;

    @Column
    private String descripcion;

    @Column
    private String banco;

    @Column(nullable = false)
    private Long telefono;

    @OneToMany(mappedBy = "taller")
    private List<TallerMecanico_Gasto> gasto;

}
