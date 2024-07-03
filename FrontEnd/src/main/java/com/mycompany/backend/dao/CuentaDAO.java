/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.dao;

import com.mycompany.backend.database.DatabaseConnection;
import com.mycompany.backend.models.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CuentaDAO {
    
    private Connection connection;
    
    public CuentaDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            // Manejo de la excepción (por ejemplo, imprimir el stack trace o registrar el error)
            e.printStackTrace();
            // Podrías lanzar una RuntimeException si quieres que el programa falle si no puede conectarse
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public List<Cuenta> getAllCuentas() throws SQLException {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT c.codigo_contable, c.nombre_cuenta, tc.nombre_tipo_cuenta FROM cuenta c JOIN tipo_cuenta tc ON c.cod_tipo_cuenta = tc.cod_tipo_cuenta";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String codigoContable = rs.getString("codigo_contable");
                String nombreCuenta = rs.getString("nombre_cuenta");
                String nombreTipoCuenta = rs.getString("nombre_tipo_cuenta");
                cuentas.add(new Cuenta(codigoContable, nombreCuenta, nombreTipoCuenta));
            }
        }
        return cuentas;
    }

    // Crear una nueva cuenta
    public void createCuenta(Cuenta cuenta) throws SQLException {
        String query = "INSERT INTO cuenta (codigo_contable, nombre_cuenta, cod_tipo_cuenta) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cuenta.getCodigoContable());
            stmt.setString(2, cuenta.getNombreCuenta());
            stmt.setInt(3, cuenta.getCodTipoCuenta());
            stmt.executeUpdate();
        }
    }

    // Eliminar una cuenta
    public void deleteCuenta(String codigoContable) throws SQLException {
        String query = "DELETE FROM cuenta WHERE codigo_contable = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, codigoContable);
            stmt.executeUpdate();
        }
    }

    // Verificar si una cuenta ya existe
    public boolean cuentaExists(String codigoContable, String nombreCuenta) throws SQLException {
        String query = "SELECT COUNT(*) FROM cuenta WHERE codigo_contable = ? OR nombre_cuenta = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, codigoContable);
            stmt.setString(2, nombreCuenta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
        public int obtenerCodTipoCuenta(String nombreTipoCuenta) throws SQLException {
        String query = "SELECT cod_tipo_cuenta FROM tipo_cuenta WHERE nombre_tipo_cuenta = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, nombreTipoCuenta);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cod_tipo_cuenta");
                } else {
                    throw new SQLException("Tipo de cuenta no encontrado: " + nombreTipoCuenta);
                }
            }
        }
    }
}