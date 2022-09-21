package com.programabanco.modelo;

/**Clase Caja de Ahorro
 * */
public class CajaAhorro extends CuentaBancaria {
    private Double saldo;
    private final String tipoCuenta = "Caja de Ahorro";

    /** Constructor de CajaAhorro
     * @param habilitada estado de la cuenta para realizar transacciones
     * @param nroCuenta identificador que deberia ser unico TODO ID
     * @param titular propietario de la cuenta
     * @param saldo cantidad de dinero de la que dispone la cuenta para operar
     * */
    public CajaAhorro(boolean habilitada, Long nroCuenta, String titular, Double saldo) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    /** Metodo para depositar
     * @param monto dinero que se quiere a depositar en la cuenta.
     *
     * @return true si la operacion fue exitosa.
     * false si la operacion no se pudo realizar por cualquier motivo.
     * */
       public synchronized boolean depositar(Double monto) {
        if (isHabilitada()) {
            setSaldo(getSaldo() + monto);
            System.out.println("La " + getTipoCuenta() + ", Nº" + getNroCuenta() +
                    "\nTitular: "  + getTitular() +
                    "\nha recibido exitosamente $" + monto +
                    "\n------------------------------------------------");
            return true;
        } else {
            System.out.println("La cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
            return false;
        }
    }

    /** Metodoa para retirar
     * @param monto dinero que se quiere retirar de la cuenta
     * */
    public synchronized boolean retirar(Double monto) {
        if (isHabilitada()) {
            if (monto <= getSaldo()) {
                setSaldo(getSaldo() - monto);
                System.out.println("Se ha debitado exitosamente $" + monto +
                        "\nEl saldo actual de la cuenta es: $" + getSaldo() +
                        "\n------------------------------------------------");
                return true;
            } else {
                System.out.println("El monto a debitar es mayor al disponible" +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\n------------------------------------------------");
                return false;
            }
        } else {
            System.out.println("La cuenta no se encuentra habilitada." +
                   "\n------------------------------------------------");
            return false;
        }
    }

    /** Metodo para ver si el saldo se adecua al prestamo
     * @return true si la cuenta esta habilitada y el saldo es mayor o igual a 10000.0
     * false si la cuenta esta inhabilitada o su saldo es menor a 10000.0
     * */
    public boolean saldoPrestamoSuficiente() {
        boolean saldoPrestamoSuficiente;
        saldoPrestamoSuficiente = isHabilitada() && getSaldo() >= 10000.0;
        return saldoPrestamoSuficiente;
    }

    /** Metodo para obtener en cada caso el saldo total
     * @return saldoTotal monto de saldo disponible en la cuenta
     * Se crea para poder operar en cuenta corriente su equivalente con saldo descubierto disponible
     * y abstraer la diferencia para simplificar su aplicacion y chequeo en metodos como retiro y transferencia.
     * */
    public Double saldoTotal() {
        Double saldoTotal = getSaldo();
        return saldoTotal;
    }

    // Getters y setters de la clase
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
}

