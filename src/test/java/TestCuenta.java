import static org.junit.jupiter.api.Assertions.*;

import com.programabanco.modelo.CajaAhorro;
import com.programabanco.modelo.CuentaCorriente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MetodosCuentasTest {

    CajaAhorro cajaAhorro1 = new CajaAhorro(true, 555555L, "Arian", 1000.0);
    CajaAhorro cajaAhorro2 = new CajaAhorro(true, 888888L, "Manu", 2000.0);
    CajaAhorro cajaAhorro3 = new CajaAhorro(false, 999999L, "Pedro", 3000.0);

    CuentaCorriente cuentaCorriente1 = new CuentaCorriente(true, 1122L, "OLDBAYLEY", 1000.0, 0.0);
    CuentaCorriente cuentaCorriente2 = new CuentaCorriente(true, 2233L, "NEWHAVEN", 2000.0, 2000.0);
    CuentaCorriente cuentaCorriente3 = new CuentaCorriente(false, 5566L, "GROWDAY", 3000.0, 3000.0);
    CuentaCorriente cuentaCorriente4 = new CuentaCorriente(true, 8899L, "Arian", 4000.0, 4000.0);






    /** TESTS CON CAJAS DE AHORRO */
    /** TESTS RETIRAR DINERO */
    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - Monto menor a saldo")
    void test_retirar_dinero_caja_ahorro_monto_menor_a_saldo() {
        cajaAhorro1.retirar(100.0);
        assertEquals(900.0, cajaAhorro1.getSaldo());
    }

    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - Monto igual a saldo")
    void test_retirar_dinero_caja_ahorro_monto_igual_a_saldo() {
        cajaAhorro1.retirar(1000.0);
        assertEquals(0.0, cajaAhorro1.getSaldo());
    }

    @Test
    @DisplayName("RETIRAR dinero en CAJA DE AHORRO - Monto mayor a saldo")
    void test_retirar_dinero_caja_ahorro_monto_mayor_a_saldo() {
        cajaAhorro1.retirar(1100.0);
        assertEquals(1000.0, cajaAhorro1.getSaldo());
    }

    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - Cuenta inhabilitada")
    void test_retirar_dinero_caja_ahorro_inhabilitada() {
        cajaAhorro3.retirar(100.0);
        assertEquals(1000.0, cajaAhorro1.getSaldo());
    }

    /** TESTS DEPOSITAR DINERO */
    @Test
    @DisplayName("DEPOSITAR dinero en CAJA DE AHORRO - Habilitada")
    void test_depositar_dinero_caja_ahorro_habilitada() {
        cajaAhorro1.depositar(1000.0);
        assertEquals(2000.0, cajaAhorro1.getSaldo());
    }

    @Test
    @DisplayName("DEPOSITAR dinero en CAJA DE AHORRO - Inhabilitada")
    void test_depositar_dinero_caja_ahorro_inhabilitada() {
        cajaAhorro3.depositar(1000.0);
        assertEquals(3000.0, cajaAhorro3.getSaldo());
    }

    /** TESTS TRANSFERIR DINERO */
    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - Ambas Habilitadas - Monto menor a saldo")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_menor_a_saldo() {
        cajaAhorro1.transferir(900.0, cajaAhorro2);
        assertEquals(2900.0, cajaAhorro2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(100.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - Ambas Habilitadas - Monto igual a saldo")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_igual_a_saldo() {
        cajaAhorro1.transferir(1000.0, cajaAhorro2);
        assertEquals(3000.0, cajaAhorro2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(0.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - Ambas Habilitadas - Monto mayor a saldo")
    void test_transferir_dinero_caja_ahorro_habilitadas_monto_mayor_a_saldo() {
        cajaAhorro1.transferir(3000.0, cajaAhorro2);
        assertEquals(2000.0, cajaAhorro2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(1000.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - ORIGEN Inhabilitada DESTINO Habilitada")
    void test_transferir_dinero_caja_ahorro_origen_inhabilitada_destino_habilitada() {
        cajaAhorro3.transferir(1000.0, cajaAhorro2);
        assertEquals(2000.0, cajaAhorro2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(3000.0, cajaAhorro3.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CAJA DE AHORRO a CAJA DE AHORRO - ORIGEN Habilitada DESTINO Inhabilitada")
    void test_transferir_dinero_caja_ahorro_origen_habilitada_destino_inhabilitada() {
        cajaAhorro1.transferir(500.0, cajaAhorro3);
        assertEquals(3000.0, cajaAhorro3.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(1000.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    /** TESTS CON CUENTAS CORRIENTES */
    /** TESTS RETIRAR DINERO */
    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto menor a saldo")
    void test_retirar_dinero_cuenta_corriente_monto_menor_a_saldo() {
        cuentaCorriente1.retirar(100.0);
        assertEquals(900.0, cuentaCorriente1.getSaldo());
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto igual a saldo")
    void test_retirar_dinero_cuenta_corriente_monto_igual_a_saldo() {
        cuentaCorriente1.retirar(1000.0);
        assertEquals(0.0, cuentaCorriente1.getSaldo());
    }

    /** TESTS RETIRAR DINERO EN RELACION CON SALDOS DESCUBIERTOS */
    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto mayor a saldo - Sin SALDO DESCUBIERTO ASIGNADO")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_sin_saldo_descubierto_asignado() {
        cuentaCorriente1.retirar(1500.0);
        assertEquals(1000.0, cuentaCorriente1.getSaldo());
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto mayor a saldo - Con SALDO DESCUBIERTO ASIGNADO SUFICIENTE")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_suficiente() {
        cuentaCorriente2.retirar(2500.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(500.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(1500.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto mayor a saldo - Con SALDO DESCUBIERTO ASIGNADO IGUAL AL MONTO")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_igual_a_monto() {
        cuentaCorriente2.retirar(4000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(2000.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(0.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Monto mayor a saldo - Con SALDO DESCUBIERTO ASIGNADO INSUFICIENTE")
    void test_retirar_dinero_cuenta_corriente_monto_mayor_a_saldo_con_saldo_descubierto_asignado_insuficiente() {
        cuentaCorriente2.retirar(5000.0);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(0.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(2000.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    @Test
    @DisplayName("RETIRAR dinero de CUENTA CORRIENTE - Cuenta Inhabilitada")
    void test_retirar_dinero_cuenta_corriente_inhabilitada() {
        cuentaCorriente3.retirar(500.0);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(3000.0, cuentaCorriente3.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    /** TEST DEPOSITAR DINERO */
    @Test
    @DisplayName("DEPOSITAR dinero de CUENTA CORRIENTE - Habilitada")
    void test_depositar_dinero_cuenta_corriente_habilitada() {
        cuentaCorriente1.depositar(500.0);
        assertEquals(1500.0, cuentaCorriente1.getSaldo()); /** CHEQUEO SALDO */
    }

    @Test
    @DisplayName("DEPOSITAR dinero de CUENTA CORRIENTE - Cuenta Inhabilitada")
    void test_depositar_dinero_cuenta_corriente_inhabilitada() {
        cuentaCorriente3.depositar(500.0);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); /** CHEQUEO SALDO */
    }

    /** TEST DEPOSITAR DINERO CON SALDOS DESCUBIERTOS */
    @Test
    @DisplayName("DEPOSITAR dinero de CUENTA CORRIENTE - Habilitada - Con SALDO DESCUBIERTO - MONTO MAYOR AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_mayor_al_descubierto() {
        cuentaCorriente2.retirar(3000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
        cuentaCorriente2.depositar(3000.0);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(0.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
    }

    @Test
    @DisplayName("DEPOSITAR dinero de CUENTA CORRIENTE - Habilitada - Con SALDO DESCUBIERTO - MONTO MENOR AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_menor_al_descubierto() {
        cuentaCorriente2.retirar(3000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
        cuentaCorriente2.depositar(500.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(500.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(1500.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    @Test
    @DisplayName("DEPOSITAR dinero de CUENTA CORRIENTE - Habilitada - Con SALDO DESCUBIERTO - MONTO IGUAL AL DESCUBIERTO")
    void test_depositar_dinero_cuenta_corriente_habilitada_con_saldo_descubierto_monto_igual_al_descubierto() {
        cuentaCorriente2.retirar(3000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(1000.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(1000.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
        cuentaCorriente2.depositar(1000.0);
        assertEquals(0.0, cuentaCorriente2.getSaldo()); /** CHEQUEO SALDO */
        assertEquals(0.0, cuentaCorriente2.getSaldoDescubierto()); /** CHEQUEO SALDO DESCUBIERTO */
        assertEquals(2000.0, cuentaCorriente2.getDescubiertoDisponible()); /** CHEQUEO DESCUBIERTO DISPONIBLE */
    }

    /** TEST TRANSFERENCIAS EN CUENTAS CORRIENTES */
    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - Ambas Habilitadas - Monto menor a saldo")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_menor_a_saldo() {
        cuentaCorriente1.transferir(900.0, cuentaCorriente2);
        assertEquals(2900.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(100.0, cuentaCorriente1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }
    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - Ambas Habilitadas - Monto igual a saldo")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_igual_a_saldo() {
        cuentaCorriente1.transferir(1000.0, cuentaCorriente2);
        assertEquals(3000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(0.0, cuentaCorriente1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    /** CASO SOSTENIDO EN FUNCION DE QUE LAS TRANSFERENCIAS NO UTILICEN SALDOS DESCUBIERTOS */
    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - Ambas Habilitadas - Monto mayor a saldo")
    void test_transferir_dinero_cuenta_corriente_habilitadas_monto_mayor_a_saldo() {
        cuentaCorriente1.transferir(2000.0, cuentaCorriente2);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(1000.0, cuentaCorriente1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - ORIGEN INHABILITADA")
    void test_transferir_dinero_cuenta_corriente_origen_inhabilitada() {
        cuentaCorriente3.transferir(900.0, cuentaCorriente2);
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    @Test
    @DisplayName("TRANSFERIR dinero de CUENTA CORRIENTE a CUENTA CORRIENTE - DESTINO INHABILITADA")
    void test_transferir_dinero_cuenta_corriente_destino_inhabilitada() {
        cuentaCorriente2.transferir(900.0, cuentaCorriente3);
        assertEquals(3000.0, cuentaCorriente3.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(2000.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }

    /** TESTS TRANSFERIR ENTRE CUENTAS DE DISTINTO TIPO */
    /** TESTS TRANSFERENCIAS DISTINTO TIPO DE CUENTA DE MISMO TITULAR */
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CAJA AHORRO A CUENTA CORRIENTE - MISMO TITULAR")
    void test_transferir_dinero_caja_ahorro_a_cuenta_corriente_mismo_titular() {
        cajaAhorro1.transferir(900.0, cuentaCorriente4);
        assertEquals(4900.0, cuentaCorriente4.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(100.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CUENTA CORRIENTE A CAJA AHORRO - MISMO TITULAR")
    void test_transferir_dinero_cuenta_corriente_a_caja_ahorro_mismo_titular() {
        cuentaCorriente4.transferir(1000.0, cajaAhorro1);
        assertEquals(2000.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(3000.0, cuentaCorriente4.getSaldo ()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }
    /** TESTS TRANSFERENCIAS DISTINTO TIPO DE CUENTA Y DISTINTOS TITULARES */
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CAJA AHORRO A CUENTA CORRIENTE - DISTINTO TITULAR")
    void test_transferir_dinero_caja_ahorro_a_cuenta_corriente_distinto_titular() {
        cajaAhorro1.transferir(900.0, cuentaCorriente2);
        assertEquals(2900.0, cuentaCorriente2.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(86.5, cajaAhorro1.getSaldo ()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }
    @Test
    @DisplayName("TESTS TRANSFERENCIAS DE CUENTA CORRIENTE A CAJA AHORRO - DISTINTO TITULAR")
    void test_transferir_dinero_cuenta_corriente_a_caja_ahorro_distinto_titular() {
        cuentaCorriente2.transferir(1000.0, cajaAhorro1);
        assertEquals(2000.0, cajaAhorro1.getSaldo()); /** CHEQUEO EN CUENTA DE DESTINO */
        assertEquals(970.0, cuentaCorriente2.getSaldo ()); /** CHEQUEO EN CUENTA DE ORIGEN */
    }




}

