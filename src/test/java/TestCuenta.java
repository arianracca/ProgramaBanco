import static org.junit.jupiter.api.Assertions.*;

import com.programabanco.modelo.CajaAhorro;
import com.programabanco.modelo.CuentaBancaria;
import com.programabanco.modelo.CuentaCorriente;
import com.programabanco.repositorio.Banco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/** TESTS
 * Se ejecutan todas las pruebas contempladas de la casuistica del ejercicio en esta clase.
 * Se generaron una serie de cuentas bancarias de ejemplo para poder generar el testeo de los
 * metodos. En el caso de los metodos de chequeo del banco se generaron distintas listas para
 * ejemplificar cada caso de respuesta de los metodos.
 * Se puede ejecutar individualmente cada test, mostrando que el programa funciona de la manera esperada.
 * En cada caso se imprime primero todos los datos completos de las cuentas o listas con las que
 * se va a estar trabajando en el test, posteriormente se reimprimen los valores afectados
 * por los métodos aplicados.
 * Finalmente se vuelven a mostrar los datos completos para verificar las modificaciones
 * en los casos correspondientes.
 * */

class MetodosCuentasTest {

    CajaAhorro cajaAhorro1 = new CajaAhorro(true, 555555L, "Arian", 1000.0);
    CajaAhorro cajaAhorro2 = new CajaAhorro(true, 888888L, "Manu", 2000.0);
    CajaAhorro cajaAhorro3 = new CajaAhorro(false, 999999L, "Pedro", 3000.0);

    CuentaCorriente cuentaCorriente1 = new CuentaCorriente(true, 1122L, "OLDBAYLEY", 1000.0, 0.0);
    CuentaCorriente cuentaCorriente2 = new CuentaCorriente(true, 2233L, "NEWHAVEN", 2000.0, 2000.0);
    CuentaCorriente cuentaCorriente3 = new CuentaCorriente(false, 5566L, "GROWDAY", 3000.0, 3000.0);
    CuentaCorriente cuentaCorriente4 = new CuentaCorriente(true, 8899L, "Arian", 4000.0, 4000.0);


     /* TESTS DE METODOS DE CUENTAS BANCARIAS
     * TESTS CON CAJAS DE AHORRO
     * TESTS RETIRAR DINERO
     */
    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - MONTO MENOR A SALDO")
    void test_retirar_dinero_caja_ahorro_monto_menor_a_saldo() {
        System.out.println(cajaAhorro1);
        cajaAhorro1.retirar(100.0);
        assertEquals(900.0, cajaAhorro1.getSaldo());
        System.out.println(cajaAhorro1);
    }

    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - MONTO IGUAL A SALDO")
    void test_retirar_dinero_caja_ahorro_monto_igual_a_saldo() {
        System.out.println(cajaAhorro1);
        cajaAhorro1.retirar(1000.0);
        assertEquals(0.0, cajaAhorro1.getSaldo());
        System.out.println(cajaAhorro1);
    }

    @Test
    @DisplayName("RETIRAR dinero en CAJA DE AHORRO - MONTO MAYOR A SALDO")
    void test_retirar_dinero_caja_ahorro_monto_mayor_a_saldo() {
        System.out.println(cajaAhorro1);
        cajaAhorro1.retirar(1100.0);
        assertEquals(1000.0, cajaAhorro1.getSaldo());
        System.out.println(cajaAhorro1);
    }

    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - INHABILITADA")
    void test_retirar_dinero_caja_ahorro_inhabilitada() {
        System.out.println(cajaAhorro3);
        cajaAhorro3.retirar(100.0);
        assertEquals(1000.0, cajaAhorro1.getSaldo());
        System.out.println(cajaAhorro3);
    }

    // TESTS DEPOSITAR DINERO
    @Test
    @DisplayName("DEPOSITAR dinero en CAJA DE AHORRO - CUENTA HABILITADA")
    void test_depositar_dinero_caja_ahorro_habilitada() {
        System.out.println(cajaAhorro1);
        cajaAhorro1.depositar(1000.0);
        assertEquals(2000.0, cajaAhorro1.getSaldo());
        System.out.println(cajaAhorro1);
    }

    @Test
    @DisplayName("DEPOSITAR dinero en CAJA DE AHORRO - INHABILITADA")
    void test_depositar_dinero_caja_ahorro_inhabilitada() {
        System.out.println(cajaAhorro3);
        cajaAhorro3.depositar(1000.0);
        assertEquals(3000.0, cajaAhorro3.getSaldo());
        System.out.println(cajaAhorro3);
    }

    // TESTS TRANSFERIR DINERO
    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - AMBAS HABILITADAS - MONTO MENOR A SALDO")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_menor_a_saldo() {
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
        cajaAhorro1.transferir(900.0, cajaAhorro2);
        assertEquals(2900.0, cajaAhorro2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(100.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - AMBAS HABILITADAS - MONTO IGUAL A SALDO")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_igual_a_saldo() {
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
        cajaAhorro1.transferir(1000.0, cajaAhorro2);
        assertEquals(3000.0, cajaAhorro2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(0.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - AMBAS HABILITADAS - MONTO MAYOR A SALDO")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_mayor_a_saldo() {
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
        cajaAhorro1.transferir(3000.0, cajaAhorro2);
        assertEquals(2000.0, cajaAhorro2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(1000.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - ORIGEN INHABILITADA - DESTINO HABILITADA")
    void test_transferir_dinero_caja_ahorro_origen_inhabilitada_destino_habilitada() {
        System.out.println(cajaAhorro2);
        System.out.println(cajaAhorro3);
        cajaAhorro3.transferir(1000.0, cajaAhorro2);
        assertEquals(2000.0, cajaAhorro2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(3000.0, cajaAhorro3.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro2);
        System.out.println(cajaAhorro3);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - ORIGEN HABILITADA DESTINO INHABILITADA")
    void test_transferir_dinero_caja_ahorro_origen_habilitada_destino_inhabilitada() {
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro3);
        cajaAhorro1.transferir(500.0, cajaAhorro3);
        assertEquals(3000.0, cajaAhorro3.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(1000.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cajaAhorro3);
    }

    /* TESTS CON CUENTAS CORRIENTES
     * TESTS RETIRAR DINERO
     */
    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO MENOR A SALDO")
    void test_retirar_dinero_cuenta_corriente_monto_menor_a_saldo() {
        System.out.println(cuentaCorriente1);
        cuentaCorriente1.retirar(100.0);
        assertEquals(900.0, cuentaCorriente1.getSaldo());
        System.out.println(cuentaCorriente1);
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO IGUAL A SALDO")
    void test_retirar_dinero_cuenta_corriente_monto_igual_a_saldo() {
        System.out.println(cuentaCorriente1);
        cuentaCorriente1.retirar(1000.0);
        assertEquals(0.0, cuentaCorriente1.getSaldo());
        System.out.println(cuentaCorriente1);
    }

    // TESTS RETIRAR DINERO EN RELACION CON SALDOS DESCUBIERTOS
    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO MAYOR A SALDO - SIN SALDO DESCUBIERTO ASIGNADO")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_sin_saldo_descubierto_asignado() {
        System.out.println(cuentaCorriente1);
        cuentaCorriente1.retirar(1500.0);
        assertEquals(1000.0, cuentaCorriente1.getSaldo());
        System.out.println(cuentaCorriente1);
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO MAYOR A SALDO - CON SALDO DESCUBIERTO ASIGNADO SUFICIENTE")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_suficiente() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(2500.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(500.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(1500.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO MAYOR A SALDO - CON SALDO DESCUBIERTO ASIGNADO IGUAL AL MONTO")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_igual_a_monto() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(4000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(2000.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(0.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - MONTO MAYOR A SALDO - CON SALDO DESCUBIERTO ASIGNADO INSUFICIENTE")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_insuficiente() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(5000.0);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(0.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(2000.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - INHABILITADA")
    void test_retirar_dinero_cuenta_corriente_inhabilitada() {
        System.out.println(cuentaCorriente3);
        cuentaCorriente3.retirar(500.0);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); // CHEQUEO SALDO
        assertEquals(3000.0, cuentaCorriente3.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente3);
    }

    // TEST DEPOSITAR DINERO
    @Test
    @DisplayName("DEPOSITAR dinero en CUENTA CORRIENTE - HABILITADA")
    void test_depositar_dinero_cuenta_corriente_habilitada() {
        System.out.println(cuentaCorriente1);
        cuentaCorriente1.depositar(500.0);
        assertEquals(1500.0, cuentaCorriente1.getSaldo()); // CHEQUEO SALDO
        System.out.println(cuentaCorriente1);
    }

    @Test
    @DisplayName("DEPOSITAR dinero en CUENTA CORRIENTE - INHABILITADA")
    void test_depositar_dinero_cuenta_corriente_inhabilitada() {
        System.out.println(cuentaCorriente3);
        cuentaCorriente3.depositar(500.0);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); // CHEQUEO SALDO
        System.out.println(cuentaCorriente3);
    }

    // TEST DEPOSITAR DINERO CON SALDOS DESCUBIERTOS
    @Test
    @DisplayName("DEPOSITAR dinero en CUENTA CORRIENTE - HABILITADA - CON SALDO DESCUBIERTO - MONTO MAYOR AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_mayor_al_descubierto() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(3000.0);  // SE RETIRA DINERO PARA GENERAR EL DESCUBIERTO
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        cuentaCorriente2.depositar(3000.0);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(0.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("DEPOSITAR dinero en CUENTA CORRIENTE - HABILITADA - CON SALDO DESCUBIERTO - MONTO MENOR AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_menor_al_descubierto() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(3000.0);  // SE RETIRA DINERO PARA GENERAR EL DESCUBIERTO
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        cuentaCorriente2.depositar(500.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(500.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(1500.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("DEPOSITAR dinero en CUENTA CORRIENTE - HABILITADA - CON SALDO DESCUBIERTO - MONTO IGUAL AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_igual_al_descubierto() {
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(3000.0);  // SE RETIRA DINERO PARA GENERAR EL DESCUBIERTO
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        cuentaCorriente2.depositar(1000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO
        assertEquals(0.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO SALDO DESCUBIERTO
        assertEquals(2000.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE
        System.out.println(cuentaCorriente2);
    }

    // TEST TRANSFERENCIAS EN CUENTAS CORRIENTES
    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - AMBAS HABILITADAS - MONTO MENOR A SALDO")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_menor_a_saldo() {
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
        cuentaCorriente1.transferir(900.0, cuentaCorriente2);
        assertEquals(2900.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(100.0, cuentaCorriente1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
    }
    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - AMBAS HABILITADAS - MONTO IGUAL A SALDO")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_igual_a_saldo() {
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
        cuentaCorriente1.transferir(1000.0, cuentaCorriente2);
        assertEquals(3000.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(0.0, cuentaCorriente1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - AMBAS HABILITADAS - MONTO MAYOR A SALDO")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_mayor_a_saldo() {
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
        cuentaCorriente1.transferir(2000.0, cuentaCorriente2);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(1000.0, cuentaCorriente1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - ORIGEN INHABILITADA")
    void test_transferir_dinero_cuenta_corriente_origen_inhabilitada() {
        System.out.println(cuentaCorriente3);
        System.out.println(cuentaCorriente2);
        cuentaCorriente3.transferir(900.0, cuentaCorriente2);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente3);
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - DESTINO INHABILITADA")
    void test_transferir_dinero_cuenta_corriente_destino_inhabilitada() {
        System.out.println(cuentaCorriente2);
        System.out.println(cuentaCorriente3);
        cuentaCorriente2.transferir(900.0, cuentaCorriente3);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente2);
        System.out.println(cuentaCorriente3);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - DESTINO CON DESCUBIERTO UTILIZADO - MONTO MAYOR A DESCUBIERTO")
    void test_transferir_dinero_cuenta_corriente_destino_saldo_descubierto_utilizado_monto_mayor_a_descubierto_utilizado() {
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(2500.0); // SE RETIRA DINERO PARA GENERAR EL DESCUBIERTO
        cuentaCorriente1.transferir(1000.0, cuentaCorriente2);
        assertEquals(500.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(0.0, cuentaCorriente1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - DESTINO CON DESCUBIERTO UTILIZADO - MONTO MENOR A DESCUBIERTO")
    void test_transferir_dinero_cuenta_corriente_destino_saldo_descubierto_utilizado_monto_menor_a_descubierto_utilizado() {
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
        cuentaCorriente2.retirar(2500.0);      // SE DEJA LA CUENTA EN DESCUBIERTO PARA LUEGO TRANSFERIRLE
        cuentaCorriente1.transferir(300.0, cuentaCorriente2);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); // CHEQUEO SALDO EN CUENTA DE DESTINO
        assertEquals(1800.0, cuentaCorriente2.getSaldoDescubierto()); // CHEQUEO DESCUBIERTO DISPONIBLE EN CUENTA DE DESTINO
        assertEquals(200.0, cuentaCorriente2.getDescubiertoUtilizado()); // CHEQUEO DESCUBIERTO UTILIZADO
        assertEquals(700.0, cuentaCorriente1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente1);
        System.out.println(cuentaCorriente2);
    }

    /* TESTS TRANSFERIR ENTRE CUENTAS DE DISTINTO TIPO
     * TESTS TRANSFERENCIAS DISTINTO TIPO DE CUENTA DE MISMO TITULAR
     */
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CAJA AHORRO A CUENTA CORRIENTE - MISMO TITULAR")
    void test_transferir_dinero_caja_ahorro_a_cuenta_corriente_mismo_titular() {
        System.out.println(cajaAhorro1);
        System.out.println(cuentaCorriente1);
        cajaAhorro1.transferir(900.0, cuentaCorriente4);
        assertEquals(4900.0, cuentaCorriente4.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(100.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cuentaCorriente1);
    }
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CUENTA CORRIENTE A CAJA AHORRO - MISMO TITULAR")
    void test_transferir_dinero_cuenta_corriente_a_caja_ahorro_mismo_titular() {
        System.out.println(cuentaCorriente4);
        System.out.println(cajaAhorro1);
        cuentaCorriente4.transferir(1000.0, cajaAhorro1);
        assertEquals(2000.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(3000.0, cuentaCorriente4.getSaldo ()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente4);
        System.out.println(cajaAhorro1);
    }
    // TESTS TRANSFERENCIAS DISTINTO TIPO DE CUENTA Y DISTINTOS TITULARES
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CAJA AHORRO A CUENTA CORRIENTE - DISTINTO TITULAR")
    void test_transferir_dinero_caja_ahorro_a_cuenta_corriente_distinto_titular() {
        System.out.println(cajaAhorro1);
        System.out.println(cuentaCorriente2);
        cajaAhorro1.transferir(900.0, cuentaCorriente2);
        assertEquals(2900.0, cuentaCorriente2.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(86.5, cajaAhorro1.getSaldo ()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cajaAhorro1);
        System.out.println(cuentaCorriente2);
    }
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CUENTA CORRIENTE A CAJA AHORRO - DISTINTO TITULAR")
    void test_transferir_dinero_cuenta_corriente_a_caja_ahorro_distinto_titular() {
        System.out.println(cuentaCorriente2);
        System.out.println(cajaAhorro1);
        cuentaCorriente2.transferir(1000.0, cajaAhorro1);
        assertEquals(2000.0, cajaAhorro1.getSaldo()); // CHEQUEO EN CUENTA DE DESTINO
        assertEquals(970.0, cuentaCorriente2.getSaldo ()); // CHEQUEO EN CUENTA DE ORIGEN
        System.out.println(cuentaCorriente2);
        System.out.println(cajaAhorro1);
    }


    /* TESTS DE MÉTODOS DE BANCO
     * TEST DE POSIBILIDADES DE HACKEO
     * */

    @Test
    @DisplayName("TEST DE BANCO HACKEABLE - CUENTA CORRIENTE CON RIESGO")
    void test_banco_hackeable_cuenta_corriente_con_riesgo() {
        CajaAhorro cajaAhorro10 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro20 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 2000.0);

        CuentaCorriente cuentaCorriente10 = new CuentaCorriente(true, 1122L, "Lord Videnbrock Harris Van der Rohe", 30000.0, 1000.0);
        CuentaCorriente cuentaCorriente20 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente30 = new CuentaCorriente(true, 2266L, "Katherine Zeta Johanesburg", 51000.0, 5000.0);

        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro10);
        listaCuentas1.add(cajaAhorro20);
        listaCuentas1.add(cuentaCorriente10);
        listaCuentas1.add(cuentaCorriente20);
        listaCuentas1.add(cuentaCorriente30);

        System.out.println(listaCuentas1);

        Banco banco = new Banco();
        System.out.println(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
        assertTrue(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
    }

    @Test
    @DisplayName("TEST DE BANCO HACKEABLE - CUENTA CORRIENTE CON RIESGO CONTANDO SALDO DESCUBIERTO")
    void test_banco_hackeable_cuenta_corriente_con_riesgo_contanto_saldo_descubierto() {

        CajaAhorro cajaAhorro10 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro20 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 2000.0);

        CuentaCorriente cuentaCorriente10 = new CuentaCorriente(true, 1122L, "Lord Viden", 30000.0, 1000.0);
        CuentaCorriente cuentaCorriente20 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente30 = new CuentaCorriente(true, 2266L, "Katherine Zeta Johanesburg", 45500.0, 5000.0);

        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro10);
        listaCuentas1.add(cajaAhorro20);
        listaCuentas1.add(cuentaCorriente10);
        listaCuentas1.add(cuentaCorriente20);
        listaCuentas1.add(cuentaCorriente30);

        System.out.println(listaCuentas1);

        Banco banco = new Banco();
        System.out.println(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
        assertTrue(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
    }
    @Test
    @DisplayName("TEST DE BANCO HACKEABLE - CAJA AHORRO CON RIESGO")
    void test_banco_hackeable_con_caja_ahorro_con_riesgo() {

        CajaAhorro cajaAhorro10 = new CajaAhorro(true, 555522L, "Sir Arian Abelardus Wildenheim", 51000.0);
        CajaAhorro cajaAhorro20 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 2000.0);

        CuentaCorriente cuentaCorriente10 = new CuentaCorriente(true, 1122L, "Lord Videnbrock Harris Van der Rohe", 30000.0, 1000.0);
        CuentaCorriente cuentaCorriente20 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente30 = new CuentaCorriente(true, 2266L, "Katherine Zeta Johanesburg", 1000.0, 5000.0);

        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro10);
        listaCuentas1.add(cajaAhorro20);
        listaCuentas1.add(cuentaCorriente10);
        listaCuentas1.add(cuentaCorriente20);
        listaCuentas1.add(cuentaCorriente30);

        System.out.println(listaCuentas1);

        Banco banco = new Banco();
        System.out.println(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
        assertTrue(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
    }

    @Test
    @DisplayName("TEST DE BANCO HACKEABLE - SIN CUENTA CON RIESGO")
    void test_banco_hackeable_sin_cuenta_con_riesgo() {

        CajaAhorro cajaAhorro10 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro20 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 51000.0);

        CuentaCorriente cuentaCorriente10 = new CuentaCorriente(true, 1122L, "Lord Viden", 30000.0, 21000.0);
        CuentaCorriente cuentaCorriente20 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente30 = new CuentaCorriente(true, 2263L, "Katherine Zeta Johanesburg", 52000.0, 5000.0);

        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro10);
        listaCuentas1.add(cajaAhorro20);
        listaCuentas1.add(cuentaCorriente10);
        listaCuentas1.add(cuentaCorriente20);
        listaCuentas1.add(cuentaCorriente30);

        System.out.println(listaCuentas1);

        Banco banco = new Banco();
        System.out.println(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
        assertFalse(banco.algunaCuentaPuedeSerHackeda(listaCuentas1));
    }

    /* IMPRESION DE LISTA DE TITULARES APTOS PARA PRESTAMO
     * */
    @Test
    @DisplayName("IMPRESION DE LISTA DE TITULARES APTOS PARA PRESTAMO")
    void impresion_lista_titulares_aptos_prestamo() {

        CajaAhorro cajaAhorro10 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro20 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 51000.0);

        CuentaCorriente cuentaCorriente10 = new CuentaCorriente(true, 1122L, "Lord Viden", 1000.0, 10000.0);
        CuentaCorriente cuentaCorriente20 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente30 = new CuentaCorriente(true, 2263L, "Katherine Zeta Johanesburg", 15000.0, 5000.0);

        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro10);
        listaCuentas1.add(cajaAhorro20);
        listaCuentas1.add(cuentaCorriente10);
        listaCuentas1.add(cuentaCorriente20);
        listaCuentas1.add(cuentaCorriente30);

        System.out.println(listaCuentas1);

        Banco banco = new Banco();
        banco.obtenerTitularesAptosParaPrestamo(listaCuentas1);
    }
}