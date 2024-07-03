/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend.vista;

import com.mycompany.backend.dao.EstadoResultadosDAO;
import com.mycompany.backend.models.EstadoResultadosItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author German
 */
public class EstadoResultados extends javax.swing.JFrame {
    private EstadoResultadosDAO estadoResultadosDAO;

    public EstadoResultados() {
        initComponents();
        try {
            estadoResultadosDAO = new EstadoResultadosDAO();
            cargarEstadoResultados();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEstadoResultados() {
        try {
            List<EstadoResultadosItem> resultados = estadoResultadosDAO.getEstadoResultados();
            DefaultTableModel model = (DefaultTableModel) jTableEstadoResultados.getModel();
            model.setRowCount(0);
            for (EstadoResultadosItem item : resultados) {
                model.addRow(new Object[]{item.getCodigoContable(), item.getNombreCuenta(), item.getTotalDebe(), item.getTotalHaber()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstadoResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estado de Resultados");

        jTableEstadoResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Contable", "Nombre de la Cuenta", "Total Debe", "Total Haber"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEstadoResultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoResultados().setVisible(true);
            }
        });
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstadoResultados;
}