package com.sp.motors.service;


import com.sp.motors.model.Auto;
import com.sp.motors.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    public Auto getAutoById(Long Id) {
        return autoRepository.findById(Id).orElse(null);
    }

    public Auto crearAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public Auto actualizarAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public void borrarAuto(Long Id) {
        autoRepository.deleteById(Id);
    }
}
