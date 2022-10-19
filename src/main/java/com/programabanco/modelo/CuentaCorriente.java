package com.programabanco.modelo;

/** Clase Cuenta Corriente
 * -->
 * Para implementar la lógica del saldo descubierto se ha tratado
 * de ser lo más fiel al uso bancario real.
 * */

public class CuentaCorriente extends CuentaBancaria {

 /** El descubierto Asignado es el tope máximo que el Banco asigna a una cuenta para poder usarse en descubierto. */
    private Double descubiertoAsignado;

    /** El descubierto Utilizado es monto que se ha consumido del tope máximo.*/
    private Double descubiertoUtilizado = 0.0;

    /** El Saldo Descubierto es el monto disponible para usarse en descubierto:
     * (saldoDescubierto = descubiertoAsignado - descubiertoUtilizado). */
    private Double saldoDescubierto;
    private final String tipoCuenta = "Cuenta Corriente";

    /** Constructor de CuentaCorriente
     * @param habilitada estado de la cuenta para realizar transacciones
     * @param nroCuenta identificador que deberia ser unico  TODO ID
     * @param titular propietario de la cuenta
     * @param saldo cantidad de dinero de la que dispone la cuenta para operar
     * @param descubiertoAsignado tope maximo de excedente que puede operar tras haber consumido el saldo
     * */
    public CuentaCorriente(boolean habilitada, Long nroCuenta, String titular, Double saldo, Double descubiertoAsignado) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.descubiertoAsignado = descubiertoAsignado;
        this.saldoDescubierto = descubiertoAsignado;    // EL SALDO DESCUBIERTO SE INICIA CON EL VALOR DEL TOPE MAXIMO
                                                           // Y SE ACTUALIZA CON LAS TRANSACCIONES
    }

    /** Metodo para depositar
     * @param monto dinero que se quiere a depositar en la cuenta.
     * @return true si la operación fue exitosa.
     * false si la operacion no se pudo realizar por cualquier motivo.
     * @see CajaAhorro#depositar(Double)
     * */
    public synchronized boolean depositar(Double monto) {
        if (isHabilitada()) {
            // Chequea si el monto llega a cubrir el saldo en descubierto
            if (monto >= getDescubiertoUtilizado()) {
                Double montoFinal = monto - getDescubiertoUtilizado();
                setSaldo(getSaldo() + montoFinal);
                setDescubiertoUtilizado(0.0);
                // Actualiza el descubierto disponible

            } else {   // Opera el deposito cuando el monto no cubre el saldo en descubierto actual
                setDescubiertoUtilizado(getDescubiertoUtilizado() - monto);
                // Actualiza el descubierto disponible
            }
            setSaldoDescubierto(getDescubiertoAsignado() - getDescubiertoUtilizado());
            System.out.println("La " + getTipoCuenta() + ", Nº " + getNroCuenta() +
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

    /** Metodo para retirar
     * @param monto dinero que se quiere retirar de la cuenta (puede exceder al saldo
     *              si hay un monto descubierto asignado para hacerlo).
     * @return true si la operacion fue exitosa.
     * false si la operacion no se pudo realizar por cualquier motivo.
     * @see CajaAhorro#retirar(Double)
     * */
    public synchronized boolean retirar(Double monto) {
        Double saldoTotal = getSaldo() + getSaldoDescubierto();
        if (isHabilitada()) {
         // Chequea que el monto a retirar no supere el saldo disponible (Contando el saldo descubierto)
            if (monto <= saldoTotal) {
                // Chequea si el saldo es suficiente para retirar el monto sin descubierto
                if (monto <= getSaldo()) {
                    setSaldo(getSaldo() - monto);
                    System.out.println("Se ha debitado exitosamente $" + monto +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\n------------------------------------------------");

                } else {    // Hace el retiro utilizando saldo en descubierto
                    Double montoDescubierto = monto - getSaldo(); // Calcula el monto que excede el saldo para restar del descubierto asignado
                    setDescubiertoUtilizado(getDescubiertoUtilizado() + montoDescubierto);
                    setSaldo(0.0);
                    setSaldoDescubierto(getDescubiertoAsignado() - getDescubiertoUtilizado()); // Actualiza el descubierto disponible
                    System.out.println("Se ha debitado exitosamente $" + monto + "" +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\nSe ha utilizado un monto en descubierto de $" + montoDescubierto +
                            "\nTiene disponible un saldo en descubierto de: $" + getSaldoDescubierto() +
                            "\n------------------------------------------------");
                }
                return true;
            } else {  // No se puede retirar el monto por superar el saldo disponible y el monto descubierto disponible sumados
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

    /** Permite la impresion de escritura en texto de los datos del objeto instanciado
     * @return todos los datos del objeto instanciado en formato cadena de caracteres.
     * Los datos son:
     * Numero de cuenta
     * Tipo de cuenta
     * Titular
     * Estado de habilitacion
     * Saldo
     * Saldo descubierto maximo permitido
     * Saldo descubierto gastado
     * Saldo descubierto disponible
     * @see CuentaBancaria#toString()
     * */
    @Override
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

    /** Metodo para obtener en cada caso el saldo total
     * @return saldoTotal saldo en cuenta al que se suma el saldo descubierto disponible
     * Se crea para poder abstraer y simplificar su aplicacion y chequeo en metodos como retiro y transferencia.
     * @see CajaAhorro#saldoTotal()
     */
    public Double saldoTotal() {
        Double saldoTotal = getSaldo() + getSaldoDescubierto();
        return saldoTotal;
    }

    // Getters y Setters de la clase
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