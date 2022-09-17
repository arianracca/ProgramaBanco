package com.programabanco.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public abstract class  CuentaBancaria {
    @Getter @Setter boolean habilitacion;
    @Getter @Setter Long nroCuenta;
    @Getter @Setter String titular;
    @Getter @Setter Double saldo;
    @Getter @Setter boolean hackeable;

    //Constructor
    public CuentaBancaria() {
    }

    /*Constructor pensado para automatizar la creacion de cuentas con numeros autoasignados
    public CuentaBancaria(boolean habilitacion, String titular) {
        this.habilitacion = habilitacion;
        // this.nroCuenta = nroCuenta; TODO SERIAL NUMBER
        this.titular = titular;
    }
    */



    //Métodos de la clase abstracta
    public void retirar(Double saldo, Double monto) {
        if(isHabilitacion()) {
            if(monto <= getSaldo()) {
                setSaldo(saldo-monto);
                System.out.println("Ha retirado exitosamente $"+monto+"" +
                        "\nSu saldo actual es de $"+getSaldo());
            } else {
                System.out.println("El monto que desea retirar es mayor al disponible"+
                        "\nSu saldo actual es de $"+getSaldo());
            }
        } else {
            System.out.println("Su cuenta no se encuentra habilitada.");
        }
    }

    public void transferir(Double saldo, double monto, CuentaBancaria cuentaDestino) {
        if (isHabilitacion()) {
            if (monto <= getSaldo()) {
                setSaldo(saldo - monto);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                System.out.println("Ha transferido exitosamente $" + monto +
                        "\nSu saldo actual es de $" + getSaldo());
            } else {
                System.out.println("El monto que desea retirar es mayor al disponible"+
                        "\nSu saldo actual es de $"+getSaldo());
            }
        } else {
            System.out.println("Su cuenta no se encuentra habilitada.");
        }
    }

    public void depositar(Long nroCuenta, Double saldo, Double monto) {
        if (isHabilitacion()) {
            //Double deposito = monto - getSaldoDescubierto(); //Override para Cuenta Crriente
            setSaldo(saldo + monto);
            System.out.println("Ha depositado exitosamente $" + monto + "" +
                    "\nSu saldo actual es de $" + getSaldo());
        } else {
            System.out.println("Su cuenta no se encuentra habilitada.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Su saldo es de $" +getSaldo());
    }


}