/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.modelo;

/**
 *
 * @author German
 */
public class Cuenta {
    private String codigoContable;
    private String nombreCuenta;
    private TipoCuenta tipoCuenta;

    public Cuenta() {
    }

    public Cuenta(String codigoContable, String nombreCuenta, TipoCuenta tipoCuenta) {
        this.codigoContable = codigoContable;
        this.nombreCuenta = nombreCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public String getCodigoContable() {
        return codigoContable;
    }

    public void setCodigoContable(String codigoContable) {
        this.codigoContable = codigoContable;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
