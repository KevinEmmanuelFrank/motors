package com.sp.motors.dto;

import com.sp.motors.model.GastoVehiculo;
import com.sp.motors.model.TallerMecanico;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Talleres mecánicos - Gastos")
public class TallerMecanico_Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "taller_mecanico_id")
    private TallerMecanico taller;

    @ManyToOne
    @JoinColumn(name = "gasto_vehiculo_id")
    private GastoVehiculo gasto;

}
