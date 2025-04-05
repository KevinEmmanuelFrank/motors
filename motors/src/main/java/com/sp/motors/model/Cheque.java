package com.sp.motors.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="cheques")
@Data // Lombok: Genera getters, setters, equals, hashCode, toString
@NoArgsConstructor // Lombok: Genera constructor sin argumentos (requerido por JPA)
@AllArgsConstructor
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Formato esperado del input HTML date
    private LocalDate fechaEmision;

    @NotBlank(message = "El destinatario no puede estar vacío")
    @Size(max = 200, message = "El nombre del destinatario no puede exceder los 200 caracteres")
    @Column(nullable = false, length = 200)
    private String id_cliente; // luego se puede reemplazar por relación con Cliente

    @NotNull(message = "El importe es obligatorio")
    @Positive(message = "El importe debe ser positivo")
    @Digits(integer=10, fraction=2, message = "Formato de importe inválido (máx 10 enteros, 2 decimales)")
    @Column(nullable = false, precision = 12, scale = 2) // precision=total dígitos, scale=decimales
    private BigDecimal importe;

    @NotBlank(message = "El banco no puede estar vacío")
    @Size(max = 100, message = "El nombre del banco no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String banco;

    @NotNull(message = "La fecha de pago es obligatoria")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    @NotNull(message = "La fecha de Deposito es obligatorio")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDeposito;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (PENDIENTE, URGENTE, etc.) en la DB
    @Column(nullable = false, length = 20)
    private EstadoCheque estado;


    @NotNull(message = "El ID del vendedor es obligatorio")
    @Column(name = "vendedor_id", nullable = false)
    private Long vendedorId = 1L; // Valor genérico provisorio
}
