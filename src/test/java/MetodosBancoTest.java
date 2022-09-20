import static org.junit.jupiter.api.Assertions.*;

import com.programabanco.modelo.CajaAhorro;
import com.programabanco.modelo.CuentaBancaria;
import com.programabanco.modelo.CuentaCorriente;
import com.programabanco.repositorio.Banco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MetodosBancoTest {
    public static void main(String[] args) {

        CajaAhorro cajaAhorro1 = new CajaAhorro(true, 555555L, "Arian Racca", 1000.0);
        CajaAhorro cajaAhorro2 = new CajaAhorro(true, 888888L, "Manuela Gimenez", 2000.0);


        CuentaCorriente cuentaCorriente1 = new CuentaCorriente(true, 1122L, "Lord Videnbrock Harris Van der Rohe", 30000.0, 21000.0);
        CuentaCorriente cuentaCorriente2 = new CuentaCorriente(false, 2233L, "Irene Haven", 50000.0, 5000.0);
        CuentaCorriente cuentaCorriente3 = new CuentaCorriente(true, 2266L, "Katherine Zeta Johanesburg", 50000.0, 5000.0);


        ArrayList<CuentaBancaria> listaCuentas1 = new ArrayList<>();
        listaCuentas1.add(cajaAhorro1);
        listaCuentas1.add(cajaAhorro2);
        listaCuentas1.add(cuentaCorriente1);
        listaCuentas1.add(cuentaCorriente2);


        Banco banco = new Banco();
        banco.obtenerTitularesAptosParaPrestamo(listaCuentas1);

        System.out.println(banco.chequeoHackeable(listaCuentas1));




    }

    @Test
    @DisplayName("RETIRAR dinero de CAJA DE AHORRO - Monto menor a saldo")
    void test_retirar_dinero_caja_ahorro_monto_menor_a_saldo() {

        assertEquals(0.0, cajaAhorro1.getSaldo());
    }


}

