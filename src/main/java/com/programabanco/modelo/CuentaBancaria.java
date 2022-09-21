package com.programabanco.modelo;

/** Clase Abstracta CuentaBancaria
 * Metodos de la clase abstracta:
 * Los metodos de depositar, retirar y transferir retornan un valor boolean
 * true si la operacion se realizo con éxito
 * false si la operacion no ha podido realizarse.
 * Si bien no se le da uso en este momento preveo que tiene utilidad en el futuro desarrollo de una aplicaicion
 * de las caracteristicas solicitadas, generando chequeos de verificacion por transacciones.
 * Se han generado Setters como el de Habilitación o Titular
 * que si bien no tienen uso en el programa aun, pueden ser importantes para una implementacion futura.
 * */
public abstract class  CuentaBancaria {

    protected boolean habilitada;
    protected Long nroCuenta;
    protected String titular;
    protected Double saldo;
    protected String tipoCuenta;

    /** Metodo para depositar */
    public abstract boolean depositar(Double monto);

    /** Metodo para retirar */
    public abstract boolean retirar(Double monto);

    /** Metodo para transferir (se utiliza en ambas clases
     * a partir de implementarse conjuntamente los metodos de depositar y retirar
     * que se ejecutan con diferencias dependiendo si el objeto
     * es instanciado como CuentaCorriente o CajaAhorro
     *
     * @param monto dinero que se quiere transferir a otra cuenta, se descontara
     *              del saldo total de la cuenta (teniendo en cuenta el saldo descubierto disponible
     *              en las cuentas corrientes).
     * @param cuentaDestino cuenta a la que se le quiere enviar el monto.
     * @return true si la operacion fue exitosa.
     * false si la operacion no se pudo realizar por cualquier motivo.
     */
    public boolean transferir(Double monto, CuentaBancaria cuentaDestino) {
        Double cargoAdicional;
        // Corrobora que las cuentas sean de distinto tipo y titular
        if (!getClass().equals(cuentaDestino.getClass())
                && !getTitular().equalsIgnoreCase(cuentaDestino.getTitular())) {
            // Calculo de cargoAdicional segun la clase: CajaAhorro o CuentaCorriente
            if (getClass().equals(CajaAhorro.class)) {
                cargoAdicional = monto * 0.015;
            } else {
                cargoAdicional = monto * 0.03;
            }
        } else {
            cargoAdicional = 0.0;
        }
        // Chequea habilitacion de cuentas y monto suficiente en cuenta de origen
        if (isHabilitada() && monto <= saldoTotal() + cargoAdicional && cuentaDestino.isHabilitada()) {
            retirar((monto + cargoAdicional));
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

    /** Permite la impresion de escritura en texto de los datos del objeto instanciado
     * @return los datos del objeto instanciado en formato cadena de caracteres.
     * Los datos son:
     * Numero de cuenta
     * Tipo de cuenta
     * Titular
     * Estado de habilitacion
     * Saldo
     * */
    public String toString() {
        return "\nNúmero de cuenta: " + getNroCuenta() +
                "\nTipo de cuenta: " + getTipoCuenta() +
                "\nTitular: " + getTitular() +
                "\nEstado de habilitación: " + isHabilitada() +
                "\nSaldo: " + getSaldo() +
                "\n------------------------------------------------";
    }

    /** Metodo abstracto para ver si el saldo se adecua al prestamo */
    public abstract boolean saldoPrestamoSuficiente();

    /** Metodo para obtener en cada caso el saldo total, contando el saldo descubierto en cuentas corrientes */
    public abstract Double saldoTotal();


    // Getters y Setters de la clase
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