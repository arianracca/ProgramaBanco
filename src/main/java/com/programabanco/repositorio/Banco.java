package com.programabanco.repositorio;

import com.programabanco.modelo.CuentaBancaria;

import java.util.List;
import java.util.stream.Collectors;

public class Banco {

    public void obtenerTitularesAptosParaPrestamo(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasBancariasAptasParaPrestamo = cuentasBancarias.stream()
                .filter(cuentaBancaria -> cuentaBancaria.saldoPrestamoSuficiente())    /** Filtra las cuentas con saldo suficiente */
                .collect(Collectors.toList());    /** Convierte a lista el resultado */

        cuentasBancariasAptasParaPrestamo
                .forEach(cuentaBancaria -> System.out.println(cuentaBancaria.getTitular().toUpperCase()));  /** Imprime lista de titulares con letras en Mayúsculas */
    }

    /** Método a implementar para ver si existen cuentas hackeables en la base de datos*/
    public synchronized boolean chequeoHackeable(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasHackeables = cuentasBancarias.stream()
                .filter(cuentaBancaria -> cuentaBancaria.getNroCuenta() %2 == 0  /**  */
                        && cuentaBancaria.getTitular().length() > 15
                        && cuentaBancaria.getSaldo() > 50000)
                .collect(Collectors.toList());

        return cuentasHackeables.size() > 0;
    }

}
