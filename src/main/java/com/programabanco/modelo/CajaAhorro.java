package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

public class CajaAhorro extends CuentaBancaria {
    @Getter @Setter private boolean habilitada;
    @Getter @Setter private Long nroCuenta;
    @Getter @Setter private String titular;
    @Getter @Setter private Double saldo;
    @Getter private final String tipoCuenta = "Caja de Ahorro";

    public CajaAhorro(boolean habilitada, Long nroCuenta, String titular, Double saldo) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }


}
