package com.mycompany.backend.models;

public class Cuenta {
    private String codigoContable;
    private String nombreCuenta;
    private int codTipoCuenta;
    private String nombreTipoCuenta;

    public Cuenta(String codigoContable, String nombreCuenta, int codTipoCuenta) {
        this.codigoContable = codigoContable;
        this.nombreCuenta = nombreCuenta;
        this.codTipoCuenta = codTipoCuenta;
    }

    // Constructor para cargar datos con el nombreTipoCuenta
    public Cuenta(String codigoContable, String nombreCuenta, String nombreTipoCuenta) {
        this.codigoContable = codigoContable;
        this.nombreCuenta = nombreCuenta;
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    // Getters y Setters
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