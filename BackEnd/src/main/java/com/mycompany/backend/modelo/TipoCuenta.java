/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.modelo;
import javax.persistence.*;

/**
 *
 * @author German
 */

@Entity
@Table(name = "tipo_cuenta")

public class TipoCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_tipo_cuenta")
    private int codTipoCuenta;

    @Column(name = "nombre_tipo_cuenta", unique = true, nullable = false)
    private String nombreTipoCuenta;

    public TipoCuenta() {
    }

    public TipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
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
