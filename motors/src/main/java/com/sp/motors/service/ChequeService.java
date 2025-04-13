package com.sp.motors.service;

import com.sp.motors.model.Cheque;
import com.sp.motors.repository.ChequeRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeService implements IChequeService{

    private final ChequeRepository chequeRepository;

    public ChequeService(ChequeRepository chequeRepository) {

        this.chequeRepository = chequeRepository;
    }

    @Override
    public List<Cheque> listarTodos() {

        return chequeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cheque> buscarPorId(Long id) {

        return chequeRepository.findById(id);
    }

    @Override
    @Transactional // Transacción de escritura (commit/rollback automático)
    public Cheque guardar(Cheque cheque) {
        // Aquí podrías añadir lógica de negocio antes de guardar,
        // aunque muchas validaciones ya están en la entidad con annotations.
        return chequeRepository.save(cheque);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        // Podrías verificar si existe antes de borrar si es necesario
        if (chequeRepository.existsById(id)) {
            chequeRepository.deleteById(id);
        } else {
            // Opcional: Lanzar una excepción si el cheque no existe
            // throw new ChequeNotFoundException("No se encontró el cheque con ID: " + id);
            System.err.println("Intento de eliminar cheque no existente con ID: " + id);
        }

    }
}
