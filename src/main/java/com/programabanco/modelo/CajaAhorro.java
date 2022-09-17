package com.programabanco.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor //Constructor con todos los prámetros
public class CajaAhorro extends CuentaBancaria {
    @Getter @Setter boolean habilitacion;
    @Getter @Setter Long nroCuenta;
    @Getter @Setter String titular;
    @Getter @Setter Double saldo;


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
