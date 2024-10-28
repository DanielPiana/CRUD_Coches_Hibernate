package org.example.crud_coches_hibernatesql.DAO;

import javafx.collections.ObservableList;
import org.example.crud_coches_hibernatesql.domain.Coche;
import org.hibernate.Session;

public interface CocheDao {
    void insertarCoche(Coche coche, Session session);

    void eliminarCoche(int id, Session session);

    void modificarCoche(int id,Coche coche, Session session);

    ObservableList<Coche> listar(Session session);

    boolean existe(Coche coche,Session session);

}
