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
}
