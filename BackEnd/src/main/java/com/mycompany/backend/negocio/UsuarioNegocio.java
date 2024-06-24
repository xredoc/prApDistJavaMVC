/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.negocio;

import com.mycompany.backend.modelo.Usuario;
import com.mycompany.backend.persistencia.UsuarioDAO;
import java.util.List;

/**
 *
 * @author German
 */
public class UsuarioNegocio {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioNegocio() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticarUsuario(String usuario, String clave) {
        return usuarioDAO.autenticarUsuario(usuario, clave);
    }

    // Métodos CRUD para usuarios
    // Métodos para crear, leer, actualizar y eliminar usuarios
     public boolean crearUsuario(Usuario usuario) {
        return usuarioDAO.crearUsuario(usuario);
    }

    public Usuario leerUsuario(String usuario) {
        return usuarioDAO.leerUsuario(usuario);
    }

    public List<Usuario> leerTodosUsuarios() {
        return usuarioDAO.leerTodosUsuarios();
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(String usuario) {
        return usuarioDAO.eliminarUsuario(usuario);
    }
}
