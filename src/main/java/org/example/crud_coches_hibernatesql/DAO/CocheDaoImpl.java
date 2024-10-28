package org.example.crud_coches_hibernatesql.DAO;

import com.mysql.cj.protocol.x.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.crud_coches_hibernatesql.domain.Coche;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CocheDaoImpl implements CocheDao{
    @Override
    public void insertarCoche(Coche coche, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(coche);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void eliminarCoche(int id, Session session) {//Dado que conseguimos el coche de una tabla,
        // podriamos pasar coche directamente, lo pongo asi para practicar otra forma
        Transaction transaction = null; //Transacción servirá para hacer rollback a los cambios si se ha producido un error
        try{
            transaction = session.beginTransaction();
            Coche coche = session.get(Coche.class, id);//Conseguimos el usuario con el id
            session.delete(coche);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {//Verificamos si la transaccion se ha iniciado
                transaction.rollback();//Si ha habido un error en la transacción revierte los cambios
            }
        }
    }

    @Override
    public void modificarCoche(int id,Coche coche, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Coche coche1 = session.get(Coche.class,id);
            session.update(coche);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public ObservableList<Coche> listar(Session session) {
        Transaction transaction = null;
        List<Coche> listaCoches = null;
        try{
            transaction = session.beginTransaction();
            listaCoches = session.createQuery("from Coche").list();
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(listaCoches);
    }

    @Override
    public boolean existe(Coche coche,Session session) {
        boolean comprobacion = false;
        Transaction transaction = null;
        Coche coche1 = null;
        try{
            transaction = session.beginTransaction();
            coche1 = session.get(Coche.class,coche.getId());
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
        if (coche1 != null) {
            comprobacion = true;
        }
        return comprobacion;
    }
}
