/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.persistencia;

import com.mycompany.backend.modelo.Cuenta;
import com.mycompany.backend.modelo.TipoCuenta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *
 * @author German
 */
public class CuentaDAO {

    public boolean crearCuenta(Cuenta cuenta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cuenta);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<Cuenta> leerTodasCuentas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cuenta", Cuenta.class).list();
        }
    }
    
    public List<Cuenta> leerTodasCuentasyTipo() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cuenta", Cuenta.class).list();
        }
    }
    
    public Cuenta leerCuenta(String codigoContable) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cuenta WHERE codigoContable = :codigoContable", Cuenta.class)
                    .setParameter("codigoContable", codigoContable)
                    .uniqueResult();
        }
    }

    public boolean eliminarCuenta(String codigoContable) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cuenta cuenta = session.createQuery("FROM Cuenta WHERE codigoContable = :codigoContable", Cuenta.class)
                                   .setParameter("codigoContable", codigoContable)
                                   .uniqueResult();
            if (cuenta != null) {
                session.delete(cuenta);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarCuenta(Cuenta cuenta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(cuenta);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
