/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.controlador;

import com.mycompany.frontend.modelo.Cuenta;
import com.mycompany.frontend.modelo.TipoCuenta;
import com.mycompany.frontend.vista.Cuentas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author German
 */
public class CuentaControlador {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12347;

    public boolean crearCuenta(Cuenta cuenta) {
        //String comando = "CREAR_CUENTA:" + cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta().getCodTipoCuenta();
        String comando = "CREAR_CUENTA:" + cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta();
        System.out.println(comando);
        return enviarComando(comando).equals("SUCCESS");
    }

    public List<Cuenta> leerTodasCuentas() {
        List<Cuenta> cuentas = new ArrayList<>();
        List<String> respuestas = enviarComandoLista("LEER_TODAS_CUENTAS");
        respuestas.forEach(System.out::println);
        System.out.println(respuestas.size());
        for (String respuesta : respuestas) {
            if (respuesta != null && !respuesta.isEmpty() && !respuesta.equals("END")) {
                String[] datos = respuesta.split(":");
                //cuentas.add(new Cuenta(datos[0], datos[1], new TipoCuenta(Integer.parseInt(datos[2]), null)));
                cuentas.add(new Cuenta(datos[0], datos[1], new TipoCuenta(datos[2])));
                //cuentas.add(new Cuenta(datos[0]));
            }
        }
        cuentas.forEach(System.out::println);
        System.out.println("de cuentacontrolador: " + cuentas.size());
        System.out.println("de cuentacontrolador: " + cuentas.toString());
        return cuentas;
    }

    public boolean eliminarCuenta(String codigoContable) {
        String comando = "ELIMINAR_CUENTA:" + codigoContable;
        return enviarComando(comando).equals("SUCCESS");
    }

    public boolean actualizarCuenta(Cuenta cuenta) {
        String comando = "ACTUALIZAR_CUENTA:" + cuenta.getCodigoContable() + ":" + cuenta.getNombreCuenta() + ":" + cuenta.getTipoCuenta().getCodTipoCuenta();
        return enviarComando(comando).equals("SUCCESS");
    }

    public List<TipoCuenta> leerTodosTipoCuenta() {
        List<TipoCuenta> tipoCuentas = new ArrayList<>();
        List<String> respuestas = enviarComandoLista("LEER_TODOS_TIPO_CUENTA");
        for (String respuesta : respuestas) {
            if (respuesta != null && !respuesta.isEmpty() && !respuesta.equals("END")) {
                String[] datos = respuesta.split(":");
//                tipoCuentas.add(new TipoCuenta(Integer.parseInt(datos[0]), datos[1]));
                tipoCuentas.add(new TipoCuenta(datos[0]));
            }
        }
        return tipoCuentas;
    }

    public int obtenerCodTipoCuenta(String nombreTipoCuenta) {
        List<TipoCuenta> tipoCuentas = leerTodosTipoCuenta();
        for (TipoCuenta tipoCuenta : tipoCuentas) {
            if (tipoCuenta.getNombreTipoCuenta().equals(nombreTipoCuenta)) {
                return tipoCuenta.getCodTipoCuenta();
            }
        }
        return -1; // Retorna -1 si no se encuentra el tipo de cuenta
    }

    private String enviarComando(String comando) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(comando);
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> enviarComandoLista(String comando) {
        List<String> respuesta = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(comando);
            String line;
            while ((line = in.readLine()) != null) {
                respuesta.add(line);
                if (line.equals("END")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public Cuenta leerCuenta(String codigoContable) {
        String respuesta = enviarComando("LEER_CUENTA:" + codigoContable);
        if (respuesta != null && !respuesta.equals("NOT_FOUND")) {
            String[] datos = respuesta.split(":");
            TipoCuenta tipoCuenta = new TipoCuenta(Integer.parseInt(datos[2]), null);
            return new Cuenta(datos[0], datos[1], tipoCuenta);
        }
        return null;
    }
}
