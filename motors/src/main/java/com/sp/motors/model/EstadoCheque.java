package com.sp.motors.model;

public enum EstadoCheque {
    PENDIENTE("Pendiente"),
    URGENTE("Urgente"),
    VENCIDO("Vencido"),
    DEPOSITADO("Depositado");

    private final String displayName;

    EstadoCheque(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    @Override
    public String toString() {
        return this.displayName;
    }
}
