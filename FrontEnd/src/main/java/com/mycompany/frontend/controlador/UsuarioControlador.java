/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.controlador;

import com.mycompany.frontend.modelo.Usuario;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class UsuarioControlador {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public boolean autenticarUsuario(String usuario, String clave) {
        return enviarComando("AUTENTICAR:" + usuario + ":" + clave).equals("SUCCESS");
    }

    public boolean crearUsuario(Usuario usuario) {
        String comando = "CREAR:" + usuario.getUsuario() + ":" + usuario.getClave() + ":" + usuario.getNombreUsuario();
        return enviarComando(comando).equals("SUCCESS");
    }

    public Usuario leerUsuario(String usuario) {
        String respuesta = enviarComando("LEER:" + usuario);
        
        if (respuesta.equals("FAILURE")) {
            return null;
        } else {
            String[] datos = respuesta.split(":");
            return new Usuario(datos[0], datos[1], datos[2]);
        }
    }

    public List<Usuario> leerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
       // String respuesta = enviarComando("LEER_TODOS");
        //List<Usuario> respuesta =enviarComandoLista("LEER_TODOS");
        
//        if (respuesta != null && !respuesta.isEmpty()) {
//            String[] registros = respuesta.split("\n");
//            for (String registro : registros) {
//                if (!registro.equals("END")) {
//                    String[] datos = registro.split(":");
//                    usuarios.add(new Usuario(datos[0], datos[1], datos[2]));
//                }
//            }
//        }
        List<String> respuestas = enviarComandoLista("LEER_TODOS");
        for (String respuesta : respuestas) {
            if (respuesta != null && !respuesta.isEmpty() && !respuesta.equals("END")) {
                String[] datos = respuesta.split(":");
                usuarios.add(new Usuario(datos[0], datos[1], datos[2]));
            }
        }
        return usuarios;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String comando = "ACTUALIZAR:" + usuario.getUsuario() + ":" + usuario.getClave() + ":" + usuario.getNombreUsuario();
        return enviarComando(comando).equals("SUCCESS");
    }

    public boolean eliminarUsuario(String usuario) {
        return enviarComando("ELIMINAR:" + usuario).equals("SUCCESS");
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
}
