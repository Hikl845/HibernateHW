package org.example;

import jakarta.persistence.*;import jakarta.persistence.metamodel.Metamodel;import org.hibernate.*;import org.hibernate.Cache;import org.hibernate.boot.spi.SessionFactoryOptions;import org.hibernate.engine.spi.FilterDefinition;import org.hibernate.graph.RootGraph;import org.hibernate.query.criteria.HibernateCriteriaBuilder;import org.hibernate.relational.SchemaManager;import org.hibernate.stat.Statistics;import javax.naming.NamingException;import javax.naming.Reference;import java.sql.Connection;import java.util.List;import java.util.Map;import java.util.Set;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        // Ініціалізація Hibernate
        SessionFactory sessionFactory = HibernateUtil.getInstance();

        // CRUD сервіси
        ClientCrudService clientService = new ClientCrudService(sessionFactory);
        PlanetCrudService planetService = new PlanetCrudService(sessionFactory);

        try {
            // CREATE
            Long clientId = clientService.create("John Doe");
            planetService.create("MARS", "Mars");

            System.out.println("=== CREATE ===");
            System.out.println("Client created with ID: " + clientId);
            System.out.println("Planet created: MARS");

            // READ
            Client client = clientService.getById(clientId);
            Planet planet = planetService.getById("MARS");

            System.out.println("=== READ ===");
            if (client != null) System.out.println("Client name: " + client.getName());
            if (planet != null) System.out.println("Planet name: " + planet.getName());

            // UPDATE
            clientService.update(clientId, "John Updated");
            planetService.update("MARS", "Mars Planet");

            client = clientService.getById(clientId);
            planet = planetService.getById("MARS");

            System.out.println("=== UPDATE ===");
            if (client != null) System.out.println("Updated client name: " + client.getName());
            if (planet != null) System.out.println("Updated planet name: " + planet.getName());

            // DELETE
            clientService.delete(clientId);
            planetService.delete("MARS");

            client = clientService.getById(clientId);
            planet = planetService.getById("MARS");

            System.out.println("=== DELETE ===");
            System.out.println("Client after delete: " + (client == null ? "deleted" : client.getName()));
            System.out.println("Planet after delete: " + (planet == null ? "deleted" : planet.getName()));

        } finally {
            // Закриваємо SessionFactory
            sessionFactory.close();
        }
    }
}