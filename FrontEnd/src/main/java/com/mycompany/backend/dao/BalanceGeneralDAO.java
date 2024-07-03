/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.dao;

import com.mycompany.backend.models.BalanceGeneralItem;
import com.mycompany.backend.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author German
 */
public class BalanceGeneralDAO {
    private Connection connection;

    public BalanceGeneralDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<BalanceGeneralItem> getBalanceGeneral() throws SQLException {
        List<BalanceGeneralItem> balance = new ArrayList<>();
        String query = "SELECT c.codigo_contable, c.nombre_cuenta, " +
                "SUM(d.debe) AS total_debe, SUM(d.haber) AS total_haber " +
                "FROM cuenta c " +
                "JOIN comprobante_detalle d ON c.cod_cuenta = d.cod_cuenta " +
                "GROUP BY c.codigo_contable, c.nombre_cuenta";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String codigoContable = rs.getString("codigo_contable");
                String nombreCuenta = rs.getString("nombre_cuenta");
                float totalDebe = rs.getFloat("total_debe");
                float totalHaber = rs.getFloat("total_haber");
                balance.add(new BalanceGeneralItem(codigoContable, nombreCuenta, totalDebe, totalHaber));
            }
        }
        return balance;
    }
}