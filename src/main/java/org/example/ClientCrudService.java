package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import  org.hibernate.Transaction;

public class ClientCrudService {

    private SessionFactory sessionFactory;

    public ClientCrudService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Long create(String name){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Client client = new Client(name);
        session.persist(client);

        tx.commit();
        session.close();

        return client.getId();
    }

    public Client getById(Long id){
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public void update(Long id, String name){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Client client = session.get(Client.class, id);
        client.setName(name);

        tx.commit();
        session.close();
    }

    public void delete(Long id){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Client client =  session.get(Client.class, id);
        session.remove(client);

        tx.commit();
        session.close();
    }
}
