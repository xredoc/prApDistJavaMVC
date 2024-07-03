/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.negocio;

import com.mycompany.backend.modelo.TipoCuenta;
import com.mycompany.backend.persistencia.TipoCuentaDAO;

import java.util.List;

/**
 *
 * @author German
 */
public class TipoCuentaNegocio {

    private TipoCuentaDAO tipoCuentaDAO;

    public TipoCuentaNegocio() {
        this.tipoCuentaDAO = new TipoCuentaDAO();
    }

    public boolean crearTipoCuenta(TipoCuenta tipoCuenta) {
        return tipoCuentaDAO.crearTipoCuenta(tipoCuenta);
    }

    public List<TipoCuenta> leerTodosTipoCuenta() {
        tipoCuentaDAO.leerTodosTipoCuenta().forEach(System.out::println);
        System.out.println("El tamaño de la lista es de negocio: " + tipoCuentaDAO.leerTodosTipoCuenta().size());
        return tipoCuentaDAO.leerTodosTipoCuenta();
    }

    public boolean eliminarTipoCuenta(String nombreTipoCuenta) {
        return tipoCuentaDAO.eliminarTipoCuenta(nombreTipoCuenta);
    }
}