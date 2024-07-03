/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.models;

/**
 *
 * @author German
 */
public class EstadoResultadosItem {
    private String codigoContable;
    private String nombreCuenta;
    private float totalDebe;
    private float totalHaber;

    public EstadoResultadosItem(String codigoContable, String nombreCuenta, float totalDebe, float totalHaber) {
        this.codigoContable = codigoContable;
        this.nombreCuenta = nombreCuenta;
        this.totalDebe = totalDebe;
        this.totalHaber = totalHaber;
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

    public float getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(float totalDebe) {
        this.totalDebe = totalDebe;
    }

    public float getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(float totalHaber) {
        this.totalHaber = totalHaber;
    }
}