/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.models;

import java.util.Date;

public class ComprobanteCabecera {
    private int numeroComprobante;
    private Date fechaComprobante;
    private String observacionesComprobante;
    private boolean anulado;

    public ComprobanteCabecera(int numeroComprobante, Date fechaComprobante, String observacionesComprobante, boolean anulado) {
        this.numeroComprobante = numeroComprobante;
        this.fechaComprobante = fechaComprobante;
        this.observacionesComprobante = observacionesComprobante;
        this.anulado = anulado;
    }

    // Getters and Setters
    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public String getObservacionesComprobante() {
        return observacionesComprobante;
    }

    public void setObservacionesComprobante(String observacionesComprobante) {
        this.observacionesComprobante = observacionesComprobante;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
}

