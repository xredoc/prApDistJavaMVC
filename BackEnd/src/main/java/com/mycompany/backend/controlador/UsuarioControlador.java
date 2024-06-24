/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.controlador;

import com.mycompany.backend.modelo.Usuario;
import com.mycompany.backend.negocio.UsuarioNegocio;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 *
 * @author German
 */
public class UsuarioControlador {

    private UsuarioNegocio usuarioNegocio;

    public UsuarioControlador() {
        this.usuarioNegocio = new UsuarioNegocio();
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
                            case "AUTENTICAR":
                                autenticarUsuario(parts, out);
                                break;
                            case "CREAR":
                                crearUsuario(parts, out);
                                break;
                            case "LEER":
                                leerUsuario(parts, out);
                                break;
                            case "LEER_TODOS":
                                leerTodosUsuarios(out);
                                break;
                            case "ACTUALIZAR":
                                actualizarUsuario(parts, out);
                                break;
                            case "ELIMINAR":
                                eliminarUsuario(parts, out);
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

    private void autenticarUsuario(String[] parts, PrintWriter out) {
        if (parts.length == 3) {
            String usuario = parts[1];
            String clave = parts[2];
            boolean isAuthenticated = usuarioNegocio.autenticarUsuario(usuario, clave);
            out.println(isAuthenticated ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void crearUsuario(String[] parts, PrintWriter out) {
        if (parts.length == 4) {
            String usuario = parts[1];
            String clave = parts[2];
            String nombreUsuario = parts[3];
            Usuario user = new Usuario(usuario, clave, nombreUsuario);
            boolean isCreated = usuarioNegocio.crearUsuario(user);
            out.println(isCreated ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void leerUsuario(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String usuario = parts[1];
            Usuario user = usuarioNegocio.leerUsuario(usuario);
            if (user != null) {
                out.println(user.getUsuario() + ":" + user.getClave() + ":" + user.getNombreUsuario());
            } else {
                out.println("FAILURE");
            }
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void leerTodosUsuarios(PrintWriter out) {
        List<Usuario> usuarios = usuarioNegocio.leerTodosUsuarios();
        for (Usuario user : usuarios) {
            out.println(user.getUsuario() + ":" + user.getClave() + ":" + user.getNombreUsuario());
        }
        out.println("END");
    }

    private void actualizarUsuario(String[] parts, PrintWriter out) {
        if (parts.length == 4) {
            String usuario = parts[1];
            String clave = parts[2];
            String nombreUsuario = parts[3];
            Usuario user = new Usuario(usuario, clave, nombreUsuario);
            boolean isUpdated = usuarioNegocio.actualizarUsuario(user);
            out.println(isUpdated ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }

    private void eliminarUsuario(String[] parts, PrintWriter out) {
        if (parts.length == 2) {
            String usuario = parts[1];
            boolean isDeleted = usuarioNegocio.eliminarUsuario(usuario);
            out.println(isDeleted ? "SUCCESS" : "FAILURE");
        } else {
            out.println("FORMATO_INVALIDO");
        }
    }
}
