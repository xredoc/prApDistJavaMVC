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
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cuenta")
    private int codCuenta;

    @Column(name = "codigo_contable", unique = true, nullable = false)
    private String codigoContable;

    @Column(name = "nombre_cuenta", nullable = false)
    private String nombreCuenta;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    public Cuenta() {
    }

    public Cuenta(String codigoContable, String nombreCuenta, TipoCuenta tipoCuenta) {
        this.codigoContable = codigoContable;
        this.nombreCuenta = nombreCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public int getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(int codCuenta) {
        this.codCuenta = codCuenta;
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