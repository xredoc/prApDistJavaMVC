/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.dao;

import com.mycompany.backend.models.ComprobanteDetalle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.backend.database.DatabaseConnection;

public class ComprobanteDetalleDAO {
    private Connection connection;

    public ComprobanteDetalleDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<ComprobanteDetalle> getDetallesByNumeroComprobante(int numeroComprobante) throws SQLException {
        List<ComprobanteDetalle> detalles = new ArrayList<>();
        String query = "SELECT * FROM comprobante_detalle WHERE numero_comprobante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroComprobante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new ComprobanteDetalle(
                        rs.getInt("numero_comprobante_detalle"),
                        rs.getInt("numero_comprobante"),
                        rs.getString("cod_cuenta"),
                        rs.getString("detalle_transaccion"),
                        rs.getFloat("debe"),
                        rs.getFloat("haber")
                    ));
                }
            }
        }
        return detalles;
    }

    public void createComprobanteDetalle(ComprobanteDetalle detalle) throws SQLException {
        String query = "INSERT INTO comprobante_detalle (numero_comprobante, cod_cuenta, detalle_transaccion, debe, haber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detalle.getNumeroComprobante());
            stmt.setString(2, detalle.getCodCuenta());
            stmt.setString(3, detalle.getDetalleTransaccion());
            stmt.setFloat(4, detalle.getDebe());
            stmt.setFloat(5, detalle.getHaber());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    detalle.setNumeroComprobanteDetalle(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void updateComprobanteDetalle(ComprobanteDetalle detalle) throws SQLException {
        String query = "UPDATE comprobante_detalle SET cod_cuenta = ?, detalle_transaccion = ?, debe = ?, haber = ? WHERE numero_comprobante_detalle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, detalle.getCodCuenta());
            stmt.setString(2, detalle.getDetalleTransaccion());
            stmt.setFloat(3, detalle.getDebe());
            stmt.setFloat(4, detalle.getHaber());
            stmt.setInt(5, detalle.getNumeroComprobanteDetalle());
            stmt.executeUpdate();
        }
    }

    public void deleteComprobanteDetalle(int numeroComprobanteDetalle) throws SQLException {
        String query = "DELETE FROM comprobante_detalle WHERE numero_comprobante_detalle = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroComprobanteDetalle);
            stmt.executeUpdate();
        }
    }

    public void anularDetalles(int numeroComprobante) throws SQLException {
        String query = "UPDATE comprobante_detalle SET detalle_transaccion = 'ANULADO', debe = 0, haber = 0 WHERE numero_comprobante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, numeroComprobante);
            stmt.executeUpdate();
        }
    }

    
}