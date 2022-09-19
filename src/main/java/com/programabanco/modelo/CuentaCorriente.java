package com.programabanco.modelo;

import lombok.Getter;
import lombok.Setter;

//@RequiredArgsConstructor
public class CuentaCorriente extends CuentaBancaria {
    @Getter @Setter private boolean habilitada;
    @Getter @Setter private Long nroCuenta;
    @Getter @Setter private String titular;
    @Getter @Setter private Double saldo;
    @Getter @Setter private Double descubiertoAsignado;
    @Getter @Setter private Double saldoDescubierto = 0.0;
    @Getter @Setter private Double descubiertoDisponible;
    @Getter private final String tipoCuenta = "Cuenta Corriente";


    public CuentaCorriente(boolean habilitada, Long nroCuenta, String titular, Double saldo, Double descubiertoAsignado) {
        this.habilitada = habilitada;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.descubiertoAsignado = descubiertoAsignado;
        this.descubiertoDisponible = descubiertoAsignado;
    }

    @Override
    public void retirar(Double monto) {
        Double saldoTotal = getSaldo() + getDescubiertoDisponible();

        /** Chequea si la cuenta está habilitada */
        if (isHabilitada()) {

         /**Chequea que el monto a retirar no supere el saldo disponible (Contando el saldo descubierto) */
            if (monto <= saldoTotal) {

                /** Chequea si el saldo es suficiente para retirar el monto sin descubierto */
                if (monto <= getSaldo()) {
                    setSaldo(getSaldo() - monto);
                    System.out.println("Ha retirado exitosamente $" + monto +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\n------------------------------------------------");

                    /** Hace el retiro utilizando saldo en descubierto */
                } else {
                    Double montoDescubierto = monto - getSaldo(); /** Calcula el monto que excede el saldo para restar del descubierto asignado */
                    setSaldoDescubierto(getSaldoDescubierto() + montoDescubierto);
                    setSaldo(0.0);
                    setDescubiertoDisponible(getDescubiertoAsignado() - getSaldoDescubierto()); /** Actualiza el descubierto disponible */
                    System.out.println("Ha retirado exitosamente $" + monto + "" +
                            "\nSu saldo actual es de $" + getSaldo() +
                            "\nSe ha utilizado un monto en descubierto de $" + getSaldoDescubierto() +
                            "\nTiene disponible de un saldo en descubierto de: $" + getDescubiertoDisponible() +
                            "\n------------------------------------------------");
                }

            /** No se puede retirar el monto por superar el saldo disponible y el monto descubierto asignado sumados */
            } else {
                System.out.println("El monto que desea retirar es mayor al disponible" +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\n------------------------------------------------");
                }

        /** La cuenta no está habilitada */
        } else{
            System.out.println("Su cuenta no se encuentra habilitada." +
                        "\n------------------------------------------------");
        }
    }

    @Override
    public void depositar(Double monto) {
        /** Chequea si la cuenta está habilitada */
        if (isHabilitada()) {
            /** Chequea si el monto llega a cubrir el saldo en descubierto */
            if (monto >= getSaldoDescubierto()) {
                Double montoFinal = monto - getSaldoDescubierto();
                setSaldo(getSaldo() + montoFinal);
                setSaldoDescubierto(0.0);
                setDescubiertoDisponible(getDescubiertoAsignado() - getSaldoDescubierto()); /** Actualiza el descubierto disponible */
                System.out.println("Ha depositado exitosamente $" + monto +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\nTiene disponible de un saldo en descubierto de: $" + getDescubiertoDisponible() +
                        "\n------------------------------------------------");

            /** Opera el depósito cuando el monto no cubre el saldo en descubierto actual */
            } else {
                setSaldoDescubierto(getSaldoDescubierto() - monto);
                setDescubiertoDisponible(getDescubiertoAsignado() - getSaldoDescubierto()); /** Actualiza el descubierto disponible */
                System.out.println("Ha depositado exitosamente $" + monto +
                        "\nSu saldo actual es de $" + getSaldo() +
                        "\nTiene disponible de un saldo en descubierto de: $" + getDescubiertoDisponible() +
                        "\n------------------------------------------------");
            }
        /** La cuenta no se encuentra habilitada */
        } else {
            System.out.println("Su cuenta no se encuentra habilitada." +
                    "\n------------------------------------------------");
        }
    }

    @Override  /** Sobreescribe la impresión de escritura en texto de los datos del objeto instanciado para los datos propios de la clase CuentaCorriente */
    public String toString() {
        StringBuilder infoCuenta = new StringBuilder();
        infoCuenta.append("\nNúmero de cuenta: " + getNroCuenta() +
                "\nTipo de cuenta: " + getTipoCuenta() +
                "\nTitular: " + getTitular()+
                "\nEstado de habilitación: " + isHabilitada() +
                "\nSaldo: " + getSaldo() +
                "\nSaldo descubierto: " + getSaldoDescubierto()+
                "\n------------------------------------------------");
        return infoCuenta.toString();
    }
}