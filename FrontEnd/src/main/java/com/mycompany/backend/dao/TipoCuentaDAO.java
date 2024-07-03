/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.dao;

import com.mycompany.backend.database.DatabaseConnection;
import com.mycompany.backend.models.TipoCuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoCuentaDAO {
    
    private Connection connection;

    public TipoCuentaDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            
            e.printStackTrace();
            
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    // Obtener el código de tipo de cuenta por nombre
    public int obtenerCodTipoCuenta(String nombreTipoCuenta) throws SQLException {
        String query = "SELECT cod_tipo_cuenta FROM tipo_cuenta WHERE nombre_tipo_cuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreTipoCuenta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cod_tipo_cuenta");
                }
            }
        }
        throw new SQLException("Tipo de cuenta no encontrado");
    }

    public TipoCuenta obtenerTipoCuentaPorNombre(String nombreTipoCuenta) throws SQLException {
        String query = "SELECT cod_tipo_cuenta, nombre_tipo_cuenta FROM tipo_cuenta WHERE nombre_tipo_cuenta = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, nombreTipoCuenta);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TipoCuenta(rs.getInt("cod_tipo_cuenta"), rs.getString("nombre_tipo_cuenta"));
                }
            }
        }
        return null;
    }
    
    // Obtener todos los nombres de tipo de cuenta
    public List<String> getAllNombreTipoCuenta() throws SQLException {
        List<String> tiposCuenta = new ArrayList<>();
        String query = "SELECT nombre_tipo_cuenta FROM tipo_cuenta";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tiposCuenta.add(rs.getString("nombre_tipo_cuenta"));
            }
        }
        return tiposCuenta;
    }
    
    
    
}