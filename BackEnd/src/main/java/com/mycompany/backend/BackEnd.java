/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.backend;

import com.mycompany.backend.controlador.UsuarioControlador;

/**
 *
 * @author German
 */
public class BackEnd {
    
    public static void main(String[] args) {
        UsuarioControlador controlador = new UsuarioControlador();
        controlador.iniciarServidor(12345);
    }
}
