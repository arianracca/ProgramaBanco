package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

public abstract class  CuentaBancaria {
    @Getter
    @Setter
    boolean habilitada;
    @Getter
    @Setter
    Long nroCuenta;
    @Getter
    @Setter
    String titular;
    @Getter
    @Setter
    Double saldo;
    @Getter
    boolean hackeable;
    @Getter String tipoCuenta = "";


    /*Constructor pensado para automatizar la creacion de cuentas con numeros autoasignados
    public CuentaBancaria(String titular) {
        this.habilitacion = true;
        // this.nroCuenta = nroCuenta; TODO SERIAL NUMBER
        this.titular = titular;
    }
    */


    /**Métodos de la clase abstracta */
    public void retirar(Double monto) {
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
    public void transferir(Double monto, CuentaBancaria cuentaDestino) {



        /** Chequea si la cuenta de ORIGEN está habilitada */
        if (isHabilitada()) {

            /** Chequea si la cuenta de DESTINO está habilitada */
            if (cuentaDestino.isHabilitada()) {

                /** Las cuentas de ORIGEN y DESTINO son de distinto tipo y titular: transferencia con cargoAdicional */
                if (!getClass().equals(cuentaDestino.getClass()) && !getTitular().equals(cuentaDestino.getTitular())) {

                    /** Calculo de cargoAdicional según la clase: CajaAhorro o CuentaCorriente */
                    Double cargoAdicional;
                    if (getClass().equals(CajaAhorro.class)) {
                        cargoAdicional = monto*0.015;
                    } else {
                        cargoAdicional = monto*0.03;
                    }

                    if (monto <= getSaldo()) {
                        setSaldo(getSaldo() - (monto + cargoAdicional));
                        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                        System.out.println("Ha transferido exitosamente $" + monto +
                                "\nDesde su " + getTipoCuenta() + " Nº: " + getNroCuenta() +
                                "\na la " + cuentaDestino.getTipoCuenta() + " de " + cuentaDestino.getTitular() +
                                "\nSe ha cobrado un cargo adicional de: $" + cargoAdicional +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");
                    } else {
                        System.out.println("El monto que desea retirar es mayor al disponible" +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");
                    }

                    /** Las cuentas de ORIGEN y DESTINO son de distinto tipo y titular: transferencia sin cargoAdicional */
                } else {

                    /** Chequea si hay saldo suficiente */
                    if (monto <= getSaldo()) {
                        setSaldo(getSaldo() - monto);
                        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                        System.out.println("Ha transferido exitosamente $" + monto +
                                "\nSu saldo actual es de $" + getSaldo() +
                                "\n------------------------------------------------");

                        /** El saldo no es suficiente */
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
    public void depositar(Double monto) {
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
    public void consultarSaldo() {
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
    public boolean chequeoHackeable() {
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


}