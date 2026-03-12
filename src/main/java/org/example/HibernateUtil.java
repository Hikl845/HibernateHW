package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml") // файл у src/main/resources
                        .build();

                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Client.class)
                        .addAnnotatedClass(Planet.class)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Помилка створення SessionFactory");
            }
        }
        return sessionFactory;
    }
}