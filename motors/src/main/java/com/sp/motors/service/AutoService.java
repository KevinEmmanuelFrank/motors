package com.sp.motors.service;


import com.sp.motors.model.Auto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public interface AutoService {

    List<Auto> getAllAutos();

    Optional<Auto> getAutoById(Long Id);

    Auto crearAuto(Auto auto);

    Optional<Auto> actualizarAuto(Long id, Auto autoDetails);

    boolean borrarAuto(Long id);

    @Transactional
    List<Auto> buscarPorMarcaOModello(String filtro);
}
