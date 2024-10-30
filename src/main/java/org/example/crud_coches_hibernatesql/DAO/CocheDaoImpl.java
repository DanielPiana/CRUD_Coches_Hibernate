package org.example.crud_coches_hibernatesql.DAO;

import com.mysql.cj.protocol.x.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.crud_coches_hibernatesql.domain.Coche;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class CocheDaoImpl implements CocheDao{
    @Override
    public void insertarCoche(Coche coche, Session session) {
        Transaction transaction = null; //Inicializamos la transacción a null, servirá para hacer rollback a los cambios si se ha producido un error
        try{
            transaction = session.beginTransaction(); //Empezamos la transacción y la guardamos en la variable transaction
            session.save(coche); //Insertamos el coche
            transaction.commit(); //Confirmamos la transacción
            session.clear(); //Limpia la sesión
        }catch (Exception e) {
            if (transaction != null) { //Comprobamos si la transacción se ha iniciado
                transaction.rollback(); //Si se ha iniciado y ha generado un error, hacemos rollback. O hacemos todos los cambios o ninguno
                System.out.println(e.getMessage()); //Imprimimos el mensaje de error para identificarlo mas fácilmente
            }
        }
    }

    public void eliminarCoche(int id, Session session) {//Dado que conseguimos el coche de una tabla,
        // podriamos pasar coche directamente, lo pongo asi para practicar otra forma
        Transaction transaction = null; //Inicializamos la transacción a null, servirá para hacer rollback a los cambios si se ha producido un error
        try{
            transaction = session.beginTransaction(); //Empezamos la transacción y la guardamos en la variable transaction
            Coche coche = session.get(Coche.class, id); //Conseguimos el usuario con el id proporcionado
            session.delete(coche); //Eliminamos el coche, si pasasemos el coche directamente, no necesitariamos la linea anterior
            transaction.commit(); //Confirmamos la transacción
            session.clear(); //Limpia la sesión
        } catch (Exception e) {
            if (transaction != null) { //Comprobamos si la transacción se ha iniciado
                transaction.rollback(); //Si se ha iniciado y ha generado un error, hacemos rollback. O hacemos todos los cambios o ninguno
                System.out.println(e.getMessage()); //Imprimimos el mensaje de error para identificarlo mas fácilmente
            }
        }
    }

    @Override
    public void modificarCoche(int id,Coche coche, Session session) {
        Transaction transaction = null; //Inicializamos la transacción a null, servirá para hacer rollback a los cambios si se ha producido un error
        try{
            transaction = session.beginTransaction(); //Empezamos la transacción y la guardamos en la variable transaction
            Coche cocheExistente = session.get(Coche.class,id); //Conseguimos el usuario con el id proporcionado
            //Modificamos el coche conseguido con el id a los nuevos campos que queremos cambiar
            cocheExistente.setMarca(coche.getMarca());
            cocheExistente.setModelo(coche.getModelo());
            cocheExistente.setTipo(coche.getTipo());

            session.update(cocheExistente); //Llevamos a cabo la modificación del coche deseado
            transaction.commit(); //Confirmamos la transacción
            session.clear(); //Limpia la sesión
        }catch (Exception e) {
            if (transaction != null) { //Comprobamos si la transacción se ha iniciado
                transaction.rollback(); //Si se ha iniciado y ha generado un error, hacemos rollback. O hacemos todos los cambios o ninguno
                System.out.println(e.getMessage()); //Imprimimos el mensaje de error para identificarlo mas fácilmente
            }
        }
    }

    @Override
    public ObservableList<Coche> listar(Session session) {
        Transaction transaction = null; //Inicializamos la transacción a null, servirá para hacer rollback a los cambios si se ha producido un error
        List listaCoches = new ArrayList<>(); //Creamos una lista vacía
        try{
            transaction = session.beginTransaction(); //Empezamos la transacción y la guardamos en la variable transaction
            listaCoches = session.createQuery("from Coche").list(); //Creamos la petición para conseguir todos los coches, "from Coche" se refiere a la clase Coche de java.
            transaction.commit(); //Confirmamos la transacción
            session.clear(); //Limpia la sesión
        }catch (Exception e) {
            if (transaction != null) { //Comprobamos si la transacción se ha iniciado
                transaction.rollback(); //Si se ha iniciado y ha generado un error, hacemos rollback. O hacemos todos los cambios o ninguno
                System.out.println(e.getMessage()); //Imprimimos el mensaje de error para identificarlo mas fácilmente
            }
        }
        return FXCollections.observableList(listaCoches); //Devolvemos la lista en formato ObservableList para cargarlo en la tabla
    }

    @Override
    public boolean existe(String matricula, Session session) {
        Transaction transaction = null; //Inicializamos la transacción a null, servirá para hacer rollback a los cambios si se ha producido un error
        boolean comprobacion = false; //Inicializamos el booleando a devolver en false
        try {
            transaction = session.beginTransaction(); //Empezamos la transacción y la guardamos en la variable transaction
            //No podemos usar "Coche cocheExistente = session.get(Coche.class,id);" Puesto que solo sirve para claves primarias, y si queremos buscar algo por matrícula,
            //tendremos que usar una consulta hql, la cual equivale a una consulta mas personalizada ":matricula" equivale al parametro matricula.
            String hql = "FROM Coche WHERE matricula = :matricula"; //Guardamos el String de la consulta en una variable
            Query<Coche> query = session.createQuery(hql, Coche.class); // Creamos la consulta e indicamos que nos devuelve un objeto Coche
            query.setParameter("matricula", matricula); //Seteamos el parametro de la consulta al parametro enviado al metodo

            comprobacion = !query.list().isEmpty(); //Si la lista está vacía significa que no existe un coche con esa matrícula

            transaction.commit(); //Confirmamos la transacción
            session.clear(); //Limpia la sesión
        } catch (Exception e) {
            if (transaction != null) { //Comprobamos si la transacción se ha iniciado
                transaction.rollback(); //Si se ha iniciado y ha generado un error, hacemos rollback. O hacemos todos los cambios o ninguno
                System.out.println(e.getMessage()); //Imprimimos el mensaje de error para identificarlo mas fácilmente
            }

        }
        return comprobacion; //Devolvemos true si existe y false si no existe.
    }
}