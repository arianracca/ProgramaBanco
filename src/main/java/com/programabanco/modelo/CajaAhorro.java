package com.programabanco.modelo;

public class CajaAhorro extends CuentaBancaria {
    private Double saldo;
    private final String tipoCuenta = "Caja de Ahorro";

    public CajaAhorro(boolean habilitada, Long nroCuenta, String titular, Double saldo) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    /**
     * Métodoa para depositar
     */
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

    /** Métodoa para retirar */
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

    /** Metodo para ver si el saldo se adecua al prestamo */
    public boolean saldoPrestamoSuficiente() {
        boolean saldoPrestamoSuficiente;
        saldoPrestamoSuficiente = isHabilitada() && getSaldo() >= 10000.0;
        return saldoPrestamoSuficiente;
    }

    /** Metodo para obtener en cada caso el saldo total */
    public Double saldoTotal() {
        Double saldoTotal = getSaldo();
        return saldoTotal;
    }

    /** Getters y setters de la clase */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}

