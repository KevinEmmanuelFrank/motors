package com.sp.motors.service;

import com.sp.motors.model.TallerMecanico;
import com.sp.motors.repository.TallerMecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TallerMecanicoServiceImpl implements TallerMecanicoService {

    @Autowired
    private TallerMecanicoRepository tallerMecanicoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TallerMecanico> getAllTalleres() {
        return tallerMecanicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TallerMecanico> getTallerById(Long id) {
        return tallerMecanicoRepository.findById(id);
    }

    @Override
    @Transactional
    public TallerMecanico crearTaller(TallerMecanico taller) {
        return tallerMecanicoRepository.save(taller);
    }

    @Override
    @Transactional
    public Optional<TallerMecanico> actualizarTaller(Long id, TallerMecanico tallerDetails) {
        Optional<TallerMecanico> optionalTaller = tallerMecanicoRepository.findById(id);

        if (optionalTaller.isPresent()) {
            TallerMecanico existingTaller = optionalTaller.get();

            // Actualizar los campos necesarios desde tallerDetails
            existingTaller.setNombre(tallerDetails.getNombre());
            existingTaller.setApellido(tallerDetails.getApellido());
            existingTaller.setNombreTaller(tallerDetails.getNombreTaller()); // Usar camelCase
            existingTaller.setDomicilio(tallerDetails.getDomicilio());
            existingTaller.setEmail(tallerDetails.getEmail());
            existingTaller.setDescripcion(tallerDetails.getDescripcion());
            existingTaller.setBanco(tallerDetails.getBanco());
            existingTaller.setTelefono(tallerDetails.getTelefono());
            // NO actualizamos la lista de gastos aquí directamente, es más complejo

            TallerMecanico updatedTaller = tallerMecanicoRepository.save(existingTaller);
            return Optional.of(updatedTaller);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean borrarTaller(Long id) {
        if (tallerMecanicoRepository.existsById(id)) {
            tallerMecanicoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<TallerMecanico> buscarPorNombre(String filtro) {
        return tallerMecanicoRepository.findByNombreContainingIgnoreCaseOrNombreTallerContainingIgnoreCase(filtro, filtro);

    }

}
