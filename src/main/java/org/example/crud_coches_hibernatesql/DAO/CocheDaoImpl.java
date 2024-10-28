package org.example.crud_coches_hibernatesql.DAO;

import com.mysql.cj.protocol.x.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.crud_coches_hibernatesql.domain.Coche;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            Coche cocheExistente = session.get(Coche.class,id);
            cocheExistente.setMatricula(coche.getMatricula());
            cocheExistente.setMarca(coche.getMarca());
            cocheExistente.setModelo(coche.getModelo());
            cocheExistente.setTipo(coche.getTipo());
            session.update(cocheExistente);
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
    public boolean existe(String matricula, Session session) {
        Transaction transaction = null;
        boolean comprobacion = false;
        try {
            transaction = session.beginTransaction();

            // Usamos HQL para buscar un coche con la matrícula proporcionada
            String hql = "FROM Coche WHERE matricula = :matricula";
            Query<Coche> query = session.createQuery(hql, Coche.class);
            query.setParameter("matricula", matricula);

            // Comprobamos si hay resultados
            comprobacion = !query.list().isEmpty(); // Si la lista no está vacía, existe

            transaction.commit(); // Confirmamos la transacción
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Revertimos si hay un error
            }
            e.printStackTrace(); // Manejo de la excepción
        }
        return comprobacion; // Retornamos el resultado de la comprobación
    }
}