package com.programabanco.repositorio;

import com.programabanco.modelo.CuentaBancaria;

import java.util.List;
import java.util.stream.Collectors;
/**Clase Banco
 * Aqui se han generado los metodos de elavuacion y chequeo a nivel banco.
 * Filtrado de listas de cuentas según criterios:
 * Titulares aptos para prestamo.
 * Chequeo de riesos de hackeo
 * */
public class Banco {

    /** Metodo para obtener los Titulares que cumplen con los requisitos para obtener un prestamo
     * @param cuentasBancarias una lista de cuentas bancarias que van a ser filtradas con los requisitos del préstamo
     * Imprime lista de titulares aptos.
     * */
    public synchronized void obtenerTitularesAptosParaPrestamo(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasBancariasAptasParaPrestamo = cuentasBancarias.stream()
                .filter(CuentaBancaria::saldoPrestamoSuficiente)    // Filtra las cuentas con saldo suficiente
                .collect(Collectors.toList());

        // Imprime lista de titulares con letras en Mayúsculas
        cuentasBancariasAptasParaPrestamo
                .forEach(cuentaBancaria -> System.out.println(cuentaBancaria.getTitular().toUpperCase()));
    }

    /** Metodo a ejecutar para ver si existen cuentas hackeables
     * @param cuentasBancarias una lista de cuentas bancarias que se filtran con las condiciones de riesgo
     *                         (Nº cuenta par, Titular con más de 15 caracteres, saldo total mayor a 50000.0)
     * @return true si hay riesgo de hackeo en el sistema (si al menos una cuenta cumple las condiciones de riesgo)
     * false si no se encontraron cuentas bancarias con dichas caracteristicas.
     * */
    public synchronized boolean algunaCuentaPuedeSerHackeda(List<CuentaBancaria> cuentasBancarias) {
        List<CuentaBancaria> cuentasHackeables = cuentasBancarias.stream()
                .filter(cuentaBancaria -> cuentaBancaria.getNroCuenta() %2 == 0 // Chequeo de numero de cuenta par
                        && cuentaBancaria.getTitular().length() > 15   // Chequeo de Titular de más de 15 caracteres
                        && cuentaBancaria.saldoTotal() > 50000.0)     // Chequeo de saldo mayor a 50000
                .collect(Collectors.toList());

        return cuentasHackeables.size() > 0;          // Retorna si hay posibilidades de hackeo
    }
}
