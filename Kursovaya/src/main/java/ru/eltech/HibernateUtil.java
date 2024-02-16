package ru.eltech;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

// спец. Java класс для инициализации Hibernate
public class HibernateUtil {

    // фабрика для создания сессий
    private static final SessionFactory sessionFactory = initSessionFactory();


    private static SessionFactory initSessionFactory(){
        try {
            return new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SeseionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // этот метод будем вызывать, когда потребуется SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // закрыть все соединения с помощью SessionFactory
    public static void close() {
        getSessionFactory().close();
    }
}


























