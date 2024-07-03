/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.controlador;

import com.mycompany.frontend.modelo.TipoCuenta;
import com.mycompany.frontend.modelo.GrupoGPNP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author German
 */
public class TipoCuentaControlador {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12346;

    public boolean crearTipoCuenta(TipoCuenta tipoCuenta) {
        String comando = "CREAR_TIPO_CUENTA:" + tipoCuenta.getNombreTipoCuenta();
        return enviarComando(comando).equals("SUCCESS");
    }

    public List<TipoCuenta> leerTodosTipoCuenta() {
        List<TipoCuenta> tipoCuentas = new ArrayList<>();
//        String respuesta = enviarComando("LEER_TODOS_TIPO_CUENTA");
//        if (respuesta != null && !respuesta.isEmpty()) {
//            String[] registros = respuesta.split("\n");
//            for (String registro : registros) {
//                if (!registro.equals("END")) {
//                    String[] datos = registro.split(":");
//                    //tipoCuentas.add(new TipoCuenta(Integer.parseInt(datos[0]), datos[1]));
//                    tipoCuentas.add(new TipoCuenta(datos[0]));
//                }
//            }
//        }
        List<String> respuestas = enviarComandoLista("LEER_TODOS_TIPO_CUENTA");
        for (String respuesta : respuestas) {
            if (respuesta != null && !respuesta.isEmpty() && !respuesta.equals("END")) {
                String[] datos = respuesta.split(":");
              //  tipoCuentas.add(new TipoCuenta(Integer.parseInt(datos[0]), datos[1]));
                tipoCuentas.add(new TipoCuenta(datos[0]));
            }
        }
        return tipoCuentas;
    }
    
    public List<TipoCuenta> leerTodosTipoCuenta2() {
        List<TipoCuenta> tipoCuentas = new ArrayList<>();
//        String respuesta = enviarComando("LEER_TODOS_TIPO_CUENTA");
//        if (respuesta != null && !respuesta.isEmpty()) {
//            String[] registros = respuesta.split("\n");
//            for (String registro : registros) {
//                if (!registro.equals("END")) {
//                    String[] datos = registro.split(":");
//                    //tipoCuentas.add(new TipoCuenta(Integer.parseInt(datos[0]), datos[1]));
//                    tipoCuentas.add(new TipoCuenta(datos[0]));
//                }
//            }
//        }
        List<String> respuestas = enviarComandoLista("LEER_TODOS_TIPO_CUENTA2");
        for (String respuesta : respuestas) {
            System.out.println("de tipode cuetna controlador leertodos tipocuenta2()"+respuesta);
            if (respuesta != null && !respuesta.isEmpty() && !respuesta.equals("END")) {
                String[] datos = respuesta.split(":");
                tipoCuentas.add(new TipoCuenta(Integer.parseInt(datos[0]), datos[1]));
              //  tipoCuentas.add(new TipoCuenta(datos[0]));
              //tipoCuentas.add(new TipoCuenta(datos[0], datos[1]));
            }
        }
        return tipoCuentas;
    }

    public boolean eliminarTipoCuenta(String nombreTipoCuenta) {
        String comando = "ELIMINAR_TIPO_CUENTA:" + nombreTipoCuenta;
        return enviarComando(comando).equals("SUCCESS");
    }

    private String enviarComando(String comando) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(comando);
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta); // Agregar esta línea para depurar
            return respuesta;
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
    
    public List<GrupoGPNP> leerTodosGruposGPNP(){
        List<GrupoGPNP> gruposGPNP = new ArrayList<>();
        
        return gruposGPNP;
    } 
    
    public int obtenerCodTipoCuenta(String nombreTipoCuenta) {
        List<TipoCuenta> tipoCuentas = leerTodosTipoCuenta();
        for (TipoCuenta tipoCuenta : tipoCuentas) {
            System.out.println("eL nombre TIPO CTA ES "+tipoCuenta.getNombreTipoCuenta());
            System.out.println("eL nombre comparado ES "+nombreTipoCuenta);
            System.out.println("eL codigo TIPO CTA ES "+tipoCuenta.getCodTipoCuenta());
            if (tipoCuenta.getNombreTipoCuenta().equals(nombreTipoCuenta)) {
                return tipoCuenta.getCodTipoCuenta();
            }
        }
        return -1; // Retorna -1 si no se encuentra el tipo de cuenta
    }
    public int obtenerCodTipoCuenta2(String nombreTipoCuenta) {
        List<TipoCuenta> tipoCuentas = leerTodosTipoCuenta2();
        for (TipoCuenta tipoCuenta : tipoCuentas) {
            System.out.println("eL nombre TIPO CTA ES "+tipoCuenta.getNombreTipoCuenta());
            System.out.println("eL nombre comparado ES "+nombreTipoCuenta);
            System.out.println("eL codigo TIPO CTA ES "+tipoCuenta.getCodTipoCuenta());
            if (tipoCuenta.getNombreTipoCuenta().equals(nombreTipoCuenta)) {
                return tipoCuenta.getCodTipoCuenta();
            }
        }
        return -1; // Retorna -1 si no se encuentra el tipo de cuenta
    }
}