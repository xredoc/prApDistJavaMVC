/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.negocio;

import com.mycompany.backend.modelo.Cuenta;
import com.mycompany.backend.modelo.TipoCuenta;
import com.mycompany.backend.persistencia.CuentaDAO;
import com.mycompany.backend.persistencia.TipoCuentaDAO;

import java.util.List;

/**
 *
 * @author German
 */
public class CuentaNegocio {
    private CuentaDAO cuentaDAO;
    private TipoCuentaDAO tipoCuentaDAO;

    public CuentaNegocio() {
        this.cuentaDAO = new CuentaDAO();
    }

    public boolean crearCuenta(Cuenta cuenta) {
        return cuentaDAO.crearCuenta(cuenta);
    }

    public List<Cuenta> leerTodasCuentas() {
        return cuentaDAO.leerTodasCuentas();
    }
    
     public Cuenta leerCuenta(String codigoContable) {
        return cuentaDAO.leerCuenta(codigoContable);
    }

    public boolean eliminarCuenta(String codigoContable) {
        return cuentaDAO.eliminarCuenta(codigoContable);
    }

    public boolean actualizarCuenta(Cuenta cuenta) {
        return cuentaDAO.actualizarCuenta(cuenta);
    }
    public List<TipoCuenta> leerTodosTipoCuenta() {
        tipoCuentaDAO.leerTodosTipoCuenta().forEach(System.out::println);
        System.out.println("El tamaño de la lista es de negocio: " + tipoCuentaDAO.leerTodosTipoCuenta().size());
        return tipoCuentaDAO.leerTodosTipoCuenta();
    }
}