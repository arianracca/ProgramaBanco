package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

public class CajaAhorro extends CuentaBancaria {

    @Getter private final String tipoCuenta = "Caja de Ahorro";

    public CajaAhorro(boolean habilitada, Long nroCuenta, String titular, Double saldo) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    @Override /** Metodo para ver si el saldo se adecua al prestamo */
    public boolean saldoPrestamoSuficiente() {
        boolean saldoPrestamoSuficiente;
        if (isHabilitada() && getSaldo() >= 10000.0){
            saldoPrestamoSuficiente = true;
        } else{
            saldoPrestamoSuficiente = false;
        }
        return saldoPrestamoSuficiente;
    }
}
