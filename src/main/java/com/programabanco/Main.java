package com.programabanco;

import com.programabanco.modelo.CajaAhorro;
import com.programabanco.modelo.CuentaBancaria;
import com.programabanco.modelo.CuentaCorriente;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        CajaAhorro cajaAhorro1 = new CajaAhorro(true, 555555L, "Arian", 1000D);
        CajaAhorro cajaAhorro2 = new CajaAhorro(true, 888888L, "Manu", 5000D);

        CuentaCorriente cuenta1 = new CuentaCorriente(true, 1122L, "OLDBAYLEY", 2000D, 1000D);
        CuentaCorriente cuenta2 = new CuentaCorriente(true, 2233L, "NEWHAVEN", 2000D, 2000D);

        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
        System.out.println(cuenta1);
        System.out.println(cuenta2);

        cajaAhorro1.retirar(500.0);
        cajaAhorro2.depositar(1500.0);
        cajaAhorro1.transferir(2000.0, cajaAhorro2);
        cajaAhorro2.transferir(1000.0, cuenta2);

        cuenta1.retirar(1000.0);
        cuenta2.transferir(1000.0, cajaAhorro1);

        /** La idea es hacer una lista en base a todos los objetos instanciados a partir de las clases de Cuentas
         * pero no se bien como hacerlo TODO investigar
         * Como punto extra hacerlo con Streams o Map o algo asi

        ArrayList<CuentaCorriente> listaCuentas = new ArrayList<CuentaCorriente>();
        for (CuentaCorriente cuentaCorriente : listaCuentas) {
            System.out.println(listaCuentas.get(1));
        */
        }
    }