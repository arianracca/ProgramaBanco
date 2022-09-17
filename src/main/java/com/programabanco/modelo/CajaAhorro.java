package com.programabanco.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CajaAhorro extends CuentaBancaria {
    @Getter @Setter
    boolean habilitacion;
    @Getter @Setter
    Long nroCuenta;
    @Getter @Setter
    String titular;
    @Getter @Setter
    Double saldo;

    //Constructores
    public CajaAhorro(boolean habilitacion, String titular) {
        this.habilitacion = habilitacion;
        // this.nroCuenta = nroCuenta; TODO SERIAL NUMBER
        this.titular = titular;
    }

    //Métodos de la clase
    public String toString() {
        StringBuilder infoCuenta = new StringBuilder();
        infoCuenta.append("\nNúmero de cuenta: " + nroCuenta+
                "\nTitular: " + titular+
                "\nEstado de habilitación: " + habilitacion+
                "\nSaldo: " + saldo+
                "\n------------------------------------------------");
        return infoCuenta.toString();
    }



}
