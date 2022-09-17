package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

public abstract class  CuentaBancaria {
    @Getter @Setter
    boolean habilitacion;
    @Getter @Setter
    Long nroCuenta = null;
    @Getter @Setter
    String titular = "";
    @Getter @Setter
    Double saldo = null;

    //Constructor
    public CuentaBancaria() {
    }
    //Métodos de la clase abstracta
    public void retirar(Long nroCuenta, Double saldo, Double monto) {
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

    public void transferir(Long nroCuenta, Double saldo, double monto, CuentaBancaria cuentaDestino) {
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