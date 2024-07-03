/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.dao;

import com.mycompany.backend.models.ComprobanteCabecera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.backend.database.DatabaseConnection;
import java.util.Date;

public class ComprobanteCabeceraDAO {
    private Connection connection;

    public ComprobanteCabeceraDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Integer> getAllNumeroComprobantes() throws SQLException {
        List<Integer> numeros = new ArrayList<>();
        String query = "SELECT numero_comprobante FROM comprobante_cabecera";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                numeros.add(rs.getInt("numero_comprobante"));
            }
        }
        return numeros;
    }

    // Obtener comprobante cabecera por número de comprobante
    public ComprobanteCabecera getComprobanteCabecera(int numeroComprobante) throws SQLException {
        String query = "SELECT * FROM comprobante_cabecera WHERE numero_comprobante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroComprobante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ComprobanteCabecera(
                            rs.getInt("numero_comprobante"),
                            rs.getDate("fecha_comprobante"),
                            rs.getString("observaciones_comprobante"),
                            rs.getBoolean("anulado")
                    );
                }
            }
        }
        return null;
    }

    // Crear comprobante cabecera
    public void createComprobanteCabecera(ComprobanteCabecera comprobante) throws SQLException {
        String query = "INSERT INTO comprobante_cabecera (fecha_comprobante, observaciones_comprobante, anulado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(comprobante.getFechaComprobante().getTime()));
            stmt.setString(2, comprobante.getObservacionesComprobante());
            stmt.setBoolean(3, comprobante.isAnulado());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comprobante.setNumeroComprobante(generatedKeys.getInt(1));
                }
            }
        }
    }

   // Actualizar comprobante cabecera
    public void updateComprobanteCabecera(ComprobanteCabecera comprobante) throws SQLException {
        String query = "UPDATE comprobante_cabecera SET fecha_comprobante = ?, observaciones_comprobante = ?, anulado = ? WHERE numero_comprobante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(comprobante.getFechaComprobante().getTime()));
            stmt.setString(2, comprobante.getObservacionesComprobante());
            stmt.setBoolean(3, comprobante.isAnulado());
            stmt.setInt(4, comprobante.getNumeroComprobante());
            stmt.executeUpdate();
        }
    }

     // Anular comprobante
    public void anularComprobante(int numeroComprobante) throws SQLException {
        String query = "UPDATE comprobante_cabecera SET observaciones_comprobante = 'ANULADO', anulado = true WHERE numero_comprobante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroComprobante);
            stmt.executeUpdate();
        }
    }
    
     public int getMaxNumeroComprobante() throws SQLException {
        String query = "SELECT MAX(numero_comprobante) AS max_numero FROM comprobante_cabecera";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("max_numero");
            }
        }
        return 0; // Retorna 0 si no hay comprobantes
    }

    
}