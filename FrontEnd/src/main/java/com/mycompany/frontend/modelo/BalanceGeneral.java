/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.modelo;

public class BalanceGeneral {
    private String codCuenta;
    private double totalDebe;
    private double totalHaber;

    public BalanceGeneral(String codCuenta, double totalDebe, double totalHaber) {
        this.codCuenta = codCuenta;
        this.totalDebe = totalDebe;
        this.totalHaber = totalHaber;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public double getTotalDebe() {
        return totalDebe;
    }

    public double getTotalHaber() {
        return totalHaber;
    }
}