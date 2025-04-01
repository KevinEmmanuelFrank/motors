package com.sp.motors.service;


import com.sp.motors.model.TallerMecanico;
import com.sp.motors.repository.TallerMecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TallerMecanicoService {

    @Autowired
    private TallerMecanicoRepository tallerMecanicoRepository;

    public List<TallerMecanico> getAllTalleres() {
        return tallerMecanicoRepository.findAll();
    }

    public TallerMecanico getTallerById(Long Id) {
        return tallerMecanicoRepository.findById(Id).orElse(null);
    }

    public TallerMecanico crearTaller(TallerMecanico taller) {
        return tallerMecanicoRepository.save(taller);
    }

    public TallerMecanico actualizarTaller(TallerMecanico taller) {
        return tallerMecanicoRepository.save(taller);
    }

    public void borrarTaller(Long Id) {
        tallerMecanicoRepository.deleteById(Id);
    }

}
