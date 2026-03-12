package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import  org.hibernate.Transaction;

public class PlanetCrudService {
    private final SessionFactory sessionFactory;

    public PlanetCrudService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void create(String id, String name){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Planet planet = new Planet(id, name);
        session.persist(planet);

        tx.commit();
        session.close();
    }

    public Planet getById(String id){
        Session session = sessionFactory.openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet;
    }

    public void update(String id, String name){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Planet planet = session.get(Planet.class, id);
        planet.setName(name);

        tx.commit();
        session.close();
    }

    public void delete(String id){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Planet planet = session.get(Planet.class, id);
        if(planet != null) {
            session.remove(session);
        }

        tx.commit();
        session.close();
    }
}
