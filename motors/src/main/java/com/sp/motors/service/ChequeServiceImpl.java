
package com.sp.motors.service;

import com.sp.motors.model.Cheque;

import java.util.List;
import java.util.Optional;

public interface ChequeServiceImpl {

    List <Cheque> listarTodos();
    Optional<Cheque> buscarPorId(Long id);

    Cheque guardar(Cheque cheque);

    void eliminarPorId(Long id);
}
