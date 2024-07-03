/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.controlador;

import com.mycompany.backend.modelo.TipoCuenta;
import com.mycompany.backend.negocio.TipoCuentaNegocio;
//import com.mycompany.backend.persistencia.TipoCuentaDAO;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 *
 * @author German
 */
public class TipoCuentaControlador {

    private TipoCuentaNegocio tipoCuentaNegocio;
   // private TipoCuentaDAO tipoCuentaDAO;

    public TipoCuentaControlador() {
        this.tipoCuentaNegocio = new TipoCuentaNegocio();
        //this.tipoCuentaDAO = new TipoCuentaDAO();
    }

    public void iniciarServidor(int puerto) {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String input = in.readLine();
                    if (input != null) {
                        String[] parts = input.split(":");
                        String comando = parts[0];

                        switch (comando) {
                            case "CREAR_TIPO_CUENTA":
                                crearTipoCuenta(parts, out);
                                break;
                            case "LEER_TODOS_TIPO_CUENTA":
                                leerTodosTipoCuenta(out);
                                break;
                            case "LEER_TODOS_TIPO_CUENTA2":
                                leerTodosTipoCuenta(out);
                                break;
                            case "ELIMINAR_TIPO_CUENTA":
                                eliminarTipoCuenta(parts, out);
                                break;
                            default:
                                out.println("COMANDO_NO_RECONOCIDO");
                                break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearTipoCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String nombreTipoCuenta = parts[1];
            TipoCuenta tipoCuenta = new TipoCuenta(nombreTipoCuenta);
            boolean isCreated = tipoCuentaNegocio.crearTipoCuenta(tipoCuenta);
            //boolean isCreated = tipoCuentaDAO.crearTipoCuenta(tipoCuenta);
            out.println(isCreated ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void leerTodosTipoCuenta(PrintWriter out) {
        List<TipoCuenta> tipoCuentas = tipoCuentaNegocio.leerTodosTipoCuenta();
        //List<TipoCuenta> tipoCuentas = tipoCuentaDAO.leerTodosTipoCuenta();
        for (TipoCuenta tipoCuenta : tipoCuentas) {
            //out.println(tipoCuenta.getCodTipoCuenta() + ":" + tipoCuenta.getNombreTipoCuenta());
            out.println(tipoCuenta.getNombreTipoCuenta());
        }
        out.println("END");
        tipoCuentas.forEach(System.out::println);
        System.out.println("El tamaño de la lista es de controlador: " + tipoCuentas.size());
    }
    
    private void leerTodosTipoCuenta2(PrintWriter out) {
        List<TipoCuenta> tipoCuentas = tipoCuentaNegocio.leerTodosTipoCuenta();
        //List<TipoCuenta> tipoCuentas = tipoCuentaDAO.leerTodosTipoCuenta();
        for (TipoCuenta tipoCuenta : tipoCuentas) {
            out.println(tipoCuenta.getCodTipoCuenta() + ":" + tipoCuenta.getNombreTipoCuenta());
            //out.println(tipoCuenta.getNombreTipoCuenta());
        }
        out.println("END");
        tipoCuentas.forEach(System.out::println);
        System.out.println("El tamaño de la lista es de controlador: " + tipoCuentas.size());
    }

    private void eliminarTipoCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String nombreTipoCuenta = parts[1];
            boolean isDeleted = tipoCuentaNegocio.eliminarTipoCuenta(nombreTipoCuenta);
            //boolean isDeleted = tipoCuentaDAO.eliminarTipoCuenta(nombreTipoCuenta);
            out.println(isDeleted ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }
}