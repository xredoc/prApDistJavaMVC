/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.controlador;

import com.mycompany.backend.modelo.Cuenta;
import com.mycompany.backend.negocio.CuentaNegocio;
import com.mycompany.backend.modelo.TipoCuenta;
import com.mycompany.backend.negocio.TipoCuentaNegocio;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 *
 * @author German
 */
public class CuentaControlador {

    private CuentaNegocio cuentaNegocio;
    private TipoCuentaNegocio tipoCuentaNegocio;

    public CuentaControlador() {
        this.cuentaNegocio = new CuentaNegocio();
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
                            case "CREAR_CUENTA":
                                crearCuenta(parts, out);
                                break;
                            case "LEER_TODAS_CUENTAS":
                                leerTodasCuentas(out);
                                break;
                            case "LEER_TODOS_TIPO_CUENTA":
                                leerTodosTipoCuenta(out);
                                break;
                            case "LEER_TODOS_TIPO_CUENTA2":
                                leerTodosTipoCuenta2(out);
                                break;
                            case "LEER_CUENTA":
                            leerCuenta(parts, out);
                                break;
                            case "ELIMINAR_CUENTA":
                                eliminarCuenta(parts, out);
                                break;
                            case "ACTUALIZAR_CUENTA":
                                actualizarCuenta(parts, out);
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

    private void crearCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 4) {
            String codigoContable = parts[1];
            String nombreCuenta = parts[2];
            int codTipoCuenta = Integer.parseInt(parts[3]);
            TipoCuenta tipoCuenta = new TipoCuenta();
            tipoCuenta.setCodTipoCuenta(codTipoCuenta);
            Cuenta cuenta = new Cuenta(codigoContable, nombreCuenta, tipoCuenta);
            boolean isCreated = cuentaNegocio.crearCuenta(cuenta);
            out.println(isCreated ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void leerTodasCuentas(PrintWriter out) {
        List<Cuenta> cuentas = cuentaNegocio.leerTodasCuentas();
        for (Cuenta cuenta : cuentas) {
            //out.println(cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta().getCodTipoCuenta());
            out.println(cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta().getNombreTipoCuenta());
        }
        out.println("END");
    }
    
    private void leerCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String codigoContable = parts[1];
            Cuenta cuenta = cuentaNegocio.leerCuenta(codigoContable);
            if (cuenta != null) {
                out.println(cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta().getCodTipoCuenta());
            } else {
                out.println("NOT_FOUND");
            }
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void eliminarCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String codigoContable = parts[1];
            boolean isDeleted = cuentaNegocio.eliminarCuenta(codigoContable);
            out.println(isDeleted ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void actualizarCuenta(String[] parts, PrintWriter out) {
        if (parts.length == 4) {
            String codigoContable = parts[1];
            String nombreCuenta = parts[2];
            int codTipoCuenta = Integer.parseInt(parts[3]);
            TipoCuenta tipoCuenta = new TipoCuenta();
            tipoCuenta.setCodTipoCuenta(codTipoCuenta);
            Cuenta cuenta = new Cuenta(codigoContable, nombreCuenta, tipoCuenta);
            boolean isUpdated = cuentaNegocio.actualizarCuenta(cuenta);
            out.println(isUpdated ? "SUCCESS" : "FAILURE");
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
}
