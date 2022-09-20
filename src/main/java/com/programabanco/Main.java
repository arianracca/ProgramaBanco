package com.programabanco;

import com.programabanco.modelo.CajaAhorro;
import com.programabanco.modelo.CuentaBancaria;
import com.programabanco.modelo.CuentaCorriente;
import com.programabanco.repositorio.Banco;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        CajaAhorro cajaAhorro1 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro2 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 2000.0);
        CajaAhorro cajaAhorro3 = new CajaAhorro(false, 999999L, "Pedro Zafiro", 10000.0);

        CuentaCorriente cuentaCorriente1 = new CuentaCorriente(true, 1122L, "123456789123456789123456789", 30000.0, 21000.0);
        CuentaCorriente cuentaCorriente2 = new CuentaCorriente(true, 2233L, "Irene Haven", 5000.0, 5000.0);
        CuentaCorriente cuentaCorriente3 = new CuentaCorriente(false, 5566L, "Lara Growday", 3000.0, 10000.0);
        CuentaCorriente cuentaCorriente4 = new CuentaCorriente(true, 8899L, "Arian Racca", 10000.0, 0.0);

        ArrayList<CuentaBancaria> listaCuentas = new ArrayList<>();
        listaCuentas.add(cajaAhorro1);
        listaCuentas.add(cajaAhorro2);
        listaCuentas.add(cajaAhorro3);
        listaCuentas.add(cuentaCorriente1);
        listaCuentas.add(cuentaCorriente2);
        listaCuentas.add(cuentaCorriente3);
        listaCuentas.add(cuentaCorriente4);

    Banco banco = new Banco();
    banco.obtenerTitularesAptosParaPrestamo(listaCuentas);

        System.out.println(banco.chequeoHackeable(listaCuentas));

        }

}