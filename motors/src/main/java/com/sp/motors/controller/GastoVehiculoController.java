package com.sp.motors.controller;

import com.sp.motors.model.GastoVehiculo;
import com.sp.motors.service.GastoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gastos")
public class GastoVehiculoController {

    @Autowired
    private GastoVehiculoService gastoVehiculoService;

    @GetMapping("/{id}")
    public GastoVehiculo getGastoById(@PathVariable Long id) {
        return gastoVehiculoService.getGastoById(id);
    }

    @PostMapping
    public GastoVehiculo crearGasto(@RequestBody GastoVehiculo gasto) {
        return gastoVehiculoService.crearGasto(gasto);
    }

    @PutMapping("/{id}")
    public GastoVehiculo actualizarGasto(@PathVariable Long id, @RequestBody GastoVehiculo gasto) {
        if (gastoVehiculoService.getGastoById(id) != null) {
            return gastoVehiculoService.actualizarGasto(gasto);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void borrarGasto(@PathVariable Long id) {
        gastoVehiculoService.borrarGasto(id);
    }

}



