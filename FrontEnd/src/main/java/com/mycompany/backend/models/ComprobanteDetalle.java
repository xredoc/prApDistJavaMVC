/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.models;

/**
 *
 * @author German
 */
public class ComprobanteDetalle {
    private int numeroComprobanteDetalle;
    private int numeroComprobante;
    private String codCuenta;
    private String detalleTransaccion;
    private float debe;
    private float haber;

    public ComprobanteDetalle(int numeroComprobanteDetalle, int numeroComprobante, String codCuenta, String detalleTransaccion, float debe, float haber) {
        this.numeroComprobanteDetalle = numeroComprobanteDetalle;
        this.numeroComprobante = numeroComprobante;
        this.codCuenta = codCuenta;
        this.detalleTransaccion = detalleTransaccion;
        this.debe = debe;
        this.haber = haber;
    }

    // Getters and Setters
    public int getNumeroComprobanteDetalle() {
        return numeroComprobanteDetalle;
    }

    public void setNumeroComprobanteDetalle(int numeroComprobanteDetalle) {
        this.numeroComprobanteDetalle = numeroComprobanteDetalle;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(String codCuenta) {
        this.codCuenta = codCuenta;
    }

    public String getDetalleTransaccion() {
        return detalleTransaccion;
    }

    public void setDetalleTransaccion(String detalleTransaccion) {
        this.detalleTransaccion = detalleTransaccion;
    }

    public float getDebe() {
        return debe;
    }

    public void setDebe(float debe) {
        this.debe = debe;
    }

    public float getHaber() {
        return haber;
    }

    public void setHaber(float haber) {
        this.haber = haber;
    }
}