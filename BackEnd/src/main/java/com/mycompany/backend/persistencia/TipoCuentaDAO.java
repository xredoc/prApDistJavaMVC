/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend.persistencia;

import com.mycompany.backend.modelo.TipoCuenta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *
 * @author German
 */
public class TipoCuentaDAO {
     public boolean crearTipoCuenta(TipoCuenta tipoCuenta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tipoCuenta);
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

    public List<TipoCuenta> leerTodosTipoCuenta() {
        Transaction transaction = null;
        List<TipoCuenta> tipoCuentas = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            tipoCuentas = session.createQuery("FROM TipoCuenta", TipoCuenta.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        tipoCuentas.forEach(System.out::println);
        System.out.println("El tamaño de la lista es de dao: " + tipoCuentas.size());
        return tipoCuentas;
    }

    public boolean eliminarTipoCuenta(String nombreTipoCuenta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TipoCuenta tipoCuenta = session.createQuery("FROM TipoCuenta WHERE nombreTipoCuenta = :nombreTipoCuenta", TipoCuenta.class)
                                           .setParameter("nombreTipoCuenta", nombreTipoCuenta)
                                           .uniqueResult();
            if (tipoCuenta != null) {
                session.delete(tipoCuenta);
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
}
