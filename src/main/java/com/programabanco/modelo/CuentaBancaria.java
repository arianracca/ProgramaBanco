package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Abstracta CuentaBancaria
 * contiene la mayoría de atributos y métodos que serán heredados por las clases
 * CajaAhorro y CuentaCorriente
 */
public abstract class  CuentaBancaria {

    /**
     * Atributos de la clase abstracta
     */
    @Getter
    @Setter
    protected boolean habilitada;
    @Getter
    @Setter
    protected Long nroCuenta;
    @Getter
    @Setter
    protected String titular;
    @Getter
    @Setter
    protected Double saldo;
    @Getter
    protected boolean hackeable;
    @Getter protected String tipoCuenta;



    /*Constructor pensado para automatizar la creacion de cuentas con numeros autoasignados
    public CuentaBancaria(String titular) {
        this.habilitacion = true;
        this.nroCuenta = nroCuenta; TODO SERIAL NUMBER
        this.titular = titular;
    }
    */


    /** Métodos de la clase abstracta */
    public synchronized void retirar(Double monto) {
        if (isHabilitada())
            if (monto <= getSaldo()) {
                setSaldo(getSaldo() - monto);
                System.out.println("Ha retirado exitosamente $" + monto + "" +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\n------------------------------------------------");
            } else {
                System.out.println("El monto que desea retirar es mayor al disponible" +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\n------------------------------------------------");
        } else {
            System.out.println("Su cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
        }
}

    /** Métodoa para transferir */
    public  synchronized void transferir(Double monto, CuentaBancaria cuentaDestino) {

        /** Chequea si la cuenta de ORIGEN está habilitada */
        if (isHabilitada()) {

            /** Chequea si la cuenta de DESTINO está habilitada */
            if (cuentaDestino.isHabilitada()) {

                /** Las cuentas de ORIGEN y DESTINO son de distinto tipo y titular: transferencia con cargo adicional */
                if (!getClass().equals(cuentaDestino.getClass()) && !getTitular().equals(cuentaDestino.getTitular())) {

                    /** Calculo de cargoAdicional según la clase: CajaAhorro o CuentaCorriente */
                    Double cargoAdicional;
                    if (getClass().equals(CajaAhorro.class)) {
                        cargoAdicional = monto*0.015;
                    } else {
                        cargoAdicional = monto*0.03;
                    }
                    /** Opera la transferencia con cargo adicional */
                    if (monto <= getSaldo()) {
                        setSaldo(getSaldo() - (monto + cargoAdicional));
                        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                        System.out.println("Ha transferido exitosamente $" + monto +
                                "\nDesde su " + getTipoCuenta() + " Nº: " + getNroCuenta() +
                                "\na la " + cuentaDestino.getTipoCuenta() + " de " + cuentaDestino.getTitular() +
                                "\nSe ha cobrado un cargo adicional de: $" + cargoAdicional +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");

                    /** El saldo no es suficiente para realizar la transferencia */
                    } else {
                        System.out.println("El monto que desea transferir es mayor al disponible" +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");
                    }

                    /** Las cuentas de ORIGEN y DESTINO son de distinto tipo y titular: transferencia sin cargo adicional */
                } else {

                    /** Chequea si hay saldo suficiente */
                    if (monto <= getSaldo()) {
                        setSaldo(getSaldo() - monto);
                        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                        System.out.println("Ha transferido exitosamente $" + monto +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");

                        /** El saldo no es suficiente para realizar la transferencia */
                    } else {
                        System.out.println("El monto que desea transferir es mayor al disponible" +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");
                    }
                }
                /** Cuenta de DESTINO inhabilitada */
            } else {
                System.out.println("La cuenta de destino no se encuentra habilitada." +
                        "\n------------------------------------------------");
            }

            /** Cuenta de ORIGEN inhabilitada */
        } else {
            System.out.println("Su cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
        }
    }

    /** Método para depositar */
    public synchronized void depositar(Double monto) {
        if (isHabilitada()) {
            setSaldo(getSaldo() + monto);
            System.out.println("Ha depositado exitosamente $" + monto + "" +
                    "\nSu saldo actual es de $" + getSaldo() +
                    "\n------------------------------------------------");
        } else {
            System.out.println("Su cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
        }
    }
    /** Método de Consultar el Saldo */
    public synchronized void consultarSaldo() {
        System.out.println("Su saldo es de $" + getSaldo() +
                "\n------------------------------------------------");
    }

    /** Permite la impresion de escritura en texto de los datos del objeto instanciado */
    public String toString() {
        StringBuilder infoCuenta = new StringBuilder();
        infoCuenta.append("\nNúmero de cuenta: " + getNroCuenta() +
                "\nTipo de cuenta: " + getTipoCuenta() +
                "\nTitular: " + getTitular()+
                "\nEstado de habilitación: " + isHabilitada() +
                "\nSaldo: " + getSaldo() +
                "\n------------------------------------------------");
        return infoCuenta.toString();
    }

    /** Método a implementar para ver si existen cuentas hackeables en la base de datos*/
    public synchronized boolean chequeoHackeable() {
        if(getNroCuenta() % 2 == 0) {
            if(getTitular().length() > 15) {
                if(getSaldo() > 50000)
                    hackeable = true;
            }
        } else {
            hackeable = false;
        }
        return hackeable;
    }

    /** Metodo para ver si el saldo se adecua al prestamo */
    public abstract boolean saldoPrestamoSuficiente();

    /** Metodo obtener los TITULARES en mayusculas */
    public void titularMayusculas() {
        System.out.println(); getTitular().toUpperCase();
    }


}