/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.backend;

import com.mycompany.backend.controlador.TipoCuentaControlador;
import com.mycompany.backend.controlador.UsuarioControlador;
import com.mycompany.backend.controlador.CuentaControlador;

/**
 *
 * @author German
 */
public class BackEnd {
    
    public static void main(String[] args) {
      //  UsuarioControlador controlador = new UsuarioControlador();
        UsuarioControlador usuarioControlador = new UsuarioControlador();
        TipoCuentaControlador tipoCuentaControlador = new TipoCuentaControlador();
        CuentaControlador cuentaControlador = new CuentaControlador();
        
        Thread usuarioThread = new Thread(() ->usuarioControlador.iniciarServidor(12345));
        Thread tipoCuentaThread = new Thread(() -> tipoCuentaControlador.iniciarServidor(12346));
        Thread cuentasThread = new Thread(() -> cuentaControlador.iniciarServidor(12347));       
        
        usuarioThread.start();
        tipoCuentaThread.start();
        cuentasThread.start();
    }
}
