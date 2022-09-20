package com.programabanco.modelo;

/** Clase Abstracta CuentaBancaria */
public abstract class  CuentaBancaria {

    /** Atributos de la clase abstracta */

    protected boolean habilitada;
    protected Long nroCuenta;
    protected String titular;
    protected Double saldo;
    protected String tipoCuenta;


    /** Métodos de la clase abstracta */

    /** Método para depositar  */
    public abstract boolean depositar(Double monto);

    /** Métodoa para retirar */
    public abstract boolean retirar(Double monto);

    /** Método para transferir */
    public boolean transferir(Double monto, CuentaBancaria cuentaDestino) {
        Double cargoAdicional;
        /** Corrobora que las cuentas sean de distinto tipo y titular */
        if (!getClass().equals(cuentaDestino.getClass()) && !getTitular().equalsIgnoreCase(cuentaDestino.getTitular())) {
            /** Calculo de cargoAdicional según la clase: CajaAhorro o CuentaCorriente */
            if (getClass().equals(CajaAhorro.class)) {
                cargoAdicional = monto * 0.015;
            } else {
                cargoAdicional = monto * 0.03;
            }
        } else {
            cargoAdicional = 0.0;
        }
        /** Chequea habilitacion de cuentas y monto suficiente en cuenta de origen */
        if (isHabilitada() && monto <= saldoTotal() + cargoAdicional && cuentaDestino.isHabilitada()) {
            retirar((monto+ cargoAdicional));
            cuentaDestino.depositar(monto);
            System.out.println("Ha transferido exitosamente $" + monto +
                    "\nDesde su " + getTipoCuenta() + " Nº: " + getNroCuenta() +
                    "\na la " + cuentaDestino.getTipoCuenta() + " de " + cuentaDestino.getTitular() +
                    "\nLa transacción tiene un cargo de: $" + cargoAdicional +
                    "\nSu saldo actual es de $" + getSaldo() +
                    "\n------------------------------------------------");
            return true;

        } else {
            System.out.println("No es posible realizar la transferencia." +
                    "\n------------------------------------------------");
            return false;
        }
    }


    /** Permite la impresion de escritura en texto de los datos del objeto instanciado */
    public String toString() {
        return "\nNúmero de cuenta: " + getNroCuenta() +
                "\nTipo de cuenta: " + getTipoCuenta() +
                "\nTitular: " + getTitular() +
                "\nEstado de habilitación: " + isHabilitada() +
                "\nSaldo: " + getSaldo() +
                "\n------------------------------------------------";
    }

    /** Metodo para ver si el saldo se adecua al prestamo */
    public abstract boolean saldoPrestamoSuficiente();

    /** Metodo para obtener en cada caso el saldo total, contando el saldo descubierto en cuentas corrientes */
    public abstract Double saldoTotal();


    /** Getters y Setters de la clase */
    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Long getNroCuenta() {
        return nroCuenta;
    }


    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

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