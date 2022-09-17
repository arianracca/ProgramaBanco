package com.programabanco;

import com.programabanco.modelo.CajaAhorro;

public class Main {
    public static void main(String[] args) {

        CajaAhorro cajaAhorro = new CajaAhorro(true, 150205L, "Arian", 12500D);
        CajaAhorro cajaAhorro2 = new CajaAhorro(true, 120555L, "Manu", 8000D);

        System.out.println(cajaAhorro);
        cajaAhorro.setSaldo(cajaAhorro.getSaldo()+200);
        System.out.println(cajaAhorro.getSaldo());
        cajaAhorro.retirar(cajaAhorro.getNroCuenta(), cajaAhorro.getSaldo(), 5000.0);
        System.out.println(cajaAhorro.getSaldo());
        cajaAhorro.transferir(cajaAhorro.getNroCuenta(), cajaAhorro.getSaldo(), 1500.0D, cajaAhorro2);
        cajaAhorro2.consultarSaldo();

    }
}