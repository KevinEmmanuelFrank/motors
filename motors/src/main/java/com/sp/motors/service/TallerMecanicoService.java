// src/main/java/com/sp/motors/service/TallerMecanicoService.java
package com.sp.motors.service;

import com.sp.motors.model.TallerMecanico;
import java.util.List;
import java.util.Optional;

public interface TallerMecanicoService {

    List<TallerMecanico> getAllTalleres();

    Optional<TallerMecanico> getTallerById(Long id);

    TallerMecanico crearTaller(TallerMecanico taller);

    Optional<TallerMecanico> actualizarTaller(Long id, TallerMecanico tallerDetails);

    boolean borrarTaller(Long id);


    List<TallerMecanico> buscarPorNombre(String filtro);
}