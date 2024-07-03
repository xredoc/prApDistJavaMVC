/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.modelo;

/**
 *
 * @author German
 */
public class TipoCuenta {
    private int codTipoCuenta;
    private String nombreTipoCuenta;

    public TipoCuenta() {
    }

    public TipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    public TipoCuenta(int codTipoCuenta, String nombreTipoCuenta) {
        this.codTipoCuenta = codTipoCuenta;
        this.nombreTipoCuenta = nombreTipoCuenta;
    }
//    public TipoCuenta(String nombreTipoCuenta) {
//        //this.codTipoCuenta = codTipoCuenta;
//        this.nombreTipoCuenta = nombreTipoCuenta;
//    }

    public int getCodTipoCuenta() {
        return codTipoCuenta;
    }

    public void setCodTipoCuenta(int codTipoCuenta) {
        this.codTipoCuenta = codTipoCuenta;
    }

    public String getNombreTipoCuenta() {
        return nombreTipoCuenta;
    }

    public void setNombreTipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }
}