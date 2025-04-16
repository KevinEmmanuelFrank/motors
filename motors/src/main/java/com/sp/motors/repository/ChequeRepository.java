package com.sp.motors.repository;

import com.sp.motors.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque,Long> {
}