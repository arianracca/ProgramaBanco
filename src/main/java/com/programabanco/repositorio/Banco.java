package com.programabanco.repositorio;

import com.programabanco.modelo.CuentaBancaria;

import java.util.List;
import java.util.stream.Collectors;

public class Banco {

    /** Método para obtener los Titulares que cumplen con los requisitos para obtener un préstamo */
    public synchronized void obtenerTitularesAptosParaPrestamo(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasBancariasAptasParaPrestamo = cuentasBancarias.stream()
                .filter(CuentaBancaria::saldoPrestamoSuficiente)    /** Filtra las cuentas con saldo suficiente */
                .collect(Collectors.toList());                      /** Convierte a lista el resultado */

        cuentasBancariasAptasParaPrestamo
                .forEach(cuentaBancaria -> System.out.println(cuentaBancaria.getTitular().toUpperCase()));  /** Imprime lista de titulares con letras en Mayúsculas */
    }

    /** Método a implementar para ver si existen cuentas hackeables en la base de datos*/
    public synchronized boolean chequeoHackeable(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasHackeables = cuentasBancarias.stream()
                .filter(cuentaBancaria -> cuentaBancaria.getNroCuenta() %2 == 0  /** Chequeo de numero de cuenta par  */
                        && cuentaBancaria.getTitular().length() > 15            /** Chequeo de Titular de más de 15 caracteres */
                        && cuentaBancaria.getSaldo() > 50000.0)                /** Chequeo de saldo mayor a 50000 */
                .collect(Collectors.toList());                                /** Arma lista con cuentas hackeables */

        return cuentasHackeables.size() > 0;          /** Responde si hay posibilidades de hackeo */
    }
}
