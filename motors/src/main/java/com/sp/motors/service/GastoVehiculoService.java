package com.sp.motors.service;

import com.sp.motors.model.GastoVehiculo;
import com.sp.motors.repository.GastoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastoVehiculoService {

    @Autowired
    private GastoVehiculoRepository gastoVehiculoRepository;

    public List<GastoVehiculo> getAllGastos() {
        return gastoVehiculoRepository.findAll();
    }

    public GastoVehiculo getGastoById(Long Id) {
        return gastoVehiculoRepository.findById(Id).orElse(null);
    }

    public GastoVehiculo crearGasto(GastoVehiculo gasto) {
        return gastoVehiculoRepository.save(gasto);
    }

    public GastoVehiculo actualizarGasto(GastoVehiculo gasto) {
        return gastoVehiculoRepository.save(gasto);
    }

    public void borrarGasto(Long Id) {
        gastoVehiculoRepository.deleteById(Id);
    }
}


