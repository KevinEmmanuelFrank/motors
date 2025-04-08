package com.sp.motors.service;

import com.sp.motors.model.Auto;
import com.sp.motors.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Mark this as a Spring component (Service)
public class AutoServiceImpl implements AutoService {

    @Autowired // Inject the repository
    private AutoRepository autoRepository;

    @Override
    @Transactional(readOnly = true) // Good practice for read operations
    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Auto> getAutoById(Long id) {
        // findById returns Optional<Auto>, perfect for this use case
        return autoRepository.findById(id);
    }

    @Override
    @Transactional // Default read/write transaction
    public Auto crearAuto(Auto auto) {
        // Optionally add validation or business logic before saving
        return autoRepository.save(auto);
    }

    @Override
    @Transactional
    public Optional<Auto> actualizarAuto(Long id, Auto autoDetails) {
        // Find the existing auto first
        Optional<Auto> optionalAuto = autoRepository.findById(id);

        if (optionalAuto.isPresent()) {
            Auto existingAuto = optionalAuto.get();

            // Update fields from autoDetails (avoid overwriting the ID)
            existingAuto.setMarca(autoDetails.getMarca());
            existingAuto.setColor(autoDetails.getColor());
            existingAuto.setAnio(autoDetails.getAnio());
            existingAuto.setCedula(autoDetails.getCedula()); // Be careful updating unique fields
            existingAuto.setModelo(autoDetails.getModelo());
            existingAuto.setEnVenta(autoDetails.isEnVenta());
            existingAuto.setOKm(autoDetails.isOKm()); // Corrected getter name from lombok
            existingAuto.setKilometros(autoDetails.getKilometros());
            existingAuto.setDescripcion(autoDetails.getDescripcion());
            existingAuto.setPrecioEntrada(autoDetails.getPrecioEntrada());
            existingAuto.setPrecioEntradaConGastos(autoDetails.getPrecioEntradaConGastos());
            existingAuto.setPrecioSalida(autoDetails.getPrecioSalida());
            existingAuto.setTransmision(autoDetails.getTransmision());
            existingAuto.setPuertas(autoDetails.getPuertas());
            existingAuto.setCombustible(autoDetails.getCombustible());

            // Save the updated entity
            Auto updatedAuto = autoRepository.save(existingAuto);
            return Optional.of(updatedAuto);
        } else {
            // Auto not found
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean borrarAuto(Long id) {
        if (autoRepository.existsById(id)) {
            autoRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            return false; // Auto with the given ID not found
        }
    }

    @Transactional
    @Override
    public List<Auto> buscarPorMarcaOModello(String filtro) {
        return autoRepository.findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCase(filtro, filtro);
    }
}