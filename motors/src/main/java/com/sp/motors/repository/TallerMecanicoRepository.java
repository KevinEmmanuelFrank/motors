package com.sp.motors.repository;

import com.sp.motors.model.TallerMecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TallerMecanicoRepository extends JpaRepository<TallerMecanico, Long> {
    List<TallerMecanico> findByNombreContainingIgnoreCaseOrNombreTallerContainingIgnoreCase(String nombre, String nombreTaller);
}
