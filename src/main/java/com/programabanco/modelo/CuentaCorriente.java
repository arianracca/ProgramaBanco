package com.programabanco.modelo;

public class CuentaCorriente extends CuentaBancaria {

    /** Para implementar la lógica del saldo descubierto se ha tratado
     * de ser lo más fiel al uso bancario real.
     * */

    /** El descubierto Asignado es el tope máximo que el Banco asigna a una cuenta para poder usarse en descubierto */
    private Double descubiertoAsignado;

    /** El descubierto Utilizado es monto que se ha consumido del tope máximo */
    private Double descubiertoUtilizado = 0.0;

    /** El Saldo Descubierto es el monto disponible para usarse en descubierto
     * (saldoDescubierto = descubiertoAsignado - descubiertoUtilizado) */
    private Double saldoDescubierto;
    private final String tipoCuenta = "Cuenta Corriente";


    public CuentaCorriente(boolean habilitada, Long nroCuenta, String titular, Double saldo, Double descubiertoAsignado) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.descubiertoAsignado = descubiertoAsignado;
        this.saldoDescubierto = descubiertoAsignado;    /** EL SALDO DESCUBIERTO SE INICIA CON EL VALOR DEL TOPE MÁXIMO
                                                            Y SE ACTUALIZA CON LAS TRANSACCIONES */
    }

    public synchronized boolean depositar(Double monto) {
        if (isHabilitada()) {
            /** Chequea si el monto llega a cubrir el saldo en descubierto */
            if (monto >= getDescubiertoUtilizado()) {
                Double montoFinal = monto - getDescubiertoUtilizado();
                setSaldo(getSaldo() + montoFinal);
                setDescubiertoUtilizado(0.0);
                setSaldoDescubierto(getDescubiertoAsignado() - getDescubiertoUtilizado()); /** Actualiza el descubierto disponible */
                System.out.println("La " + getTipoCuenta() + ", Nº " + getNroCuenta() +
                        "\nTitular: "  + getTitular() +
                        "\nha recibido exitosamente $" + monto +
                        "\n------------------------------------------------");
                return true;


            } else {   /** Opera el depósito cuando el monto no cubre el saldo en descubierto actual */
                setDescubiertoUtilizado(getDescubiertoUtilizado() - monto);
                setSaldoDescubierto(getDescubiertoAsignado() - getDescubiertoUtilizado()); /** Actualiza el descubierto disponible */
                System.out.println("La " + getTipoCuenta() + ", Nº " + getNroCuenta() +
                        "\nTitular: "  + getTitular() +
                        "\nha recibido exitosamente $" + monto +
                        "\n------------------------------------------------");
                return true;
            }

        } else {
            System.out.println("La cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
            return false;
        }
    }

    public synchronized boolean retirar(Double monto) {
        Double saldoTotal = getSaldo() + getSaldoDescubierto();
        if (isHabilitada()) {
         /**Chequea que el monto a retirar no supere el saldo disponible (Contando el saldo descubierto) */
            if (monto <= saldoTotal) {

                /** Chequea si el saldo es suficiente para retirar el monto sin descubierto */
                if (monto <= getSaldo()) {
                    setSaldo(getSaldo() - monto);
                    System.out.println("Se ha debitado exitosamente $" + monto +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\n------------------------------------------------");
                    return true;

                } else {    /** Hace el retiro utilizando saldo en descubierto */
                    Double montoDescubierto = monto - getSaldo(); /** Calcula el monto que excede el saldo para restar del descubierto asignado */
                    setDescubiertoUtilizado(getDescubiertoUtilizado() + montoDescubierto);
                    setSaldo(0.0);
                    setSaldoDescubierto(getDescubiertoAsignado() - getDescubiertoUtilizado()); /** Actualiza el descubierto disponible */
                    System.out.println("Se ha debitado exitosamente $" + monto + "" +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\nSe ha utilizado un monto en descubierto de $" + montoDescubierto +
                            "\nTiene disponible un saldo en descubierto de: $" + getSaldoDescubierto() +
                            "\n------------------------------------------------");
                    return true;
                }


            } else {  /** No se puede retirar el monto por superar el saldo disponible y el monto descubierto disponible sumados */
                System.out.println("El monto que desea operar es mayor al disponible" +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\nTiene disponible un saldo en descubierto de: $" + getSaldoDescubierto() +
                        "\n------------------------------------------------");
                return false;
                }


        } else{
            System.out.println("La cuenta no se encuentra habilitada." +
                        "\n------------------------------------------------");
            return false;
        }
    }


    @Override  /** Sobreescribe la impresión de escritura para los datos propios de la clase CuentaCorriente */
    public String toString() {
        return "\nNúmero de cuenta: " + getNroCuenta() +
                "\nTipo de cuenta: " + getTipoCuenta() +
                "\nTitular: " + getTitular() +
                "\nEstado de habilitación: " + isHabilitada() +
                "\nSaldo: " + getSaldo() +
                "\nSaldo descubierto máximo permitido: " + getDescubiertoAsignado() +
                "\nSaldo descubierto gastado: " + getDescubiertoUtilizado() +
                "\nSaldo descubierto disponible: " + getSaldoDescubierto() +
                "\n------------------------------------------------";
    }

    /** Metodo para obtener en cada caso el saldo total, contando el saldo descubierto disponible */
    public Double saldoTotal() {
        Double saldoTotal = getSaldo() + getSaldoDescubierto();
        return saldoTotal;
    }

    /** Metodo para ver si el saldo se adecua al prestamo */
    public boolean saldoPrestamoSuficiente() {
        boolean saldoPrestamoSuficiente;
        saldoPrestamoSuficiente = isHabilitada() && saldoTotal() >= 10000.0;
        return saldoPrestamoSuficiente;
    }

    /** Getters y Setters de la clase */
    public Double getDescubiertoAsignado() {
        return descubiertoAsignado;
    }

    public void setDescubiertoAsignado(Double descubiertoAsignado) {
        this.descubiertoAsignado = descubiertoAsignado;
    }

    public Double getDescubiertoUtilizado() {
        return descubiertoUtilizado;
    }

    public void setDescubiertoUtilizado(Double descubiertoUtilizado) {
        this.descubiertoUtilizado = descubiertoUtilizado;
    }

    public Double getSaldoDescubierto() {
        return saldoDescubierto;
    }

    public void setSaldoDescubierto(Double saldoDescubierto) {
        this.saldoDescubierto = saldoDescubierto;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
}