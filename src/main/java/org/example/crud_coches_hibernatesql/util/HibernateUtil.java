package org.example.crud_coches_hibernatesql.util;

import org.example.crud_coches_hibernatesql.domain.Coche;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null;
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("org/example/crud_coches_hibernatesql/Hibernate/hibernate.cfg.xml");
            cfg.addAnnotatedClass(Coche.class);
            factory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
