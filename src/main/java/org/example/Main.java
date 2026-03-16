package org.example;

import org.example.dao.ClientDaoService;
import org.example.dao.ClientDaoServiceImpl;
import org.example.dao.PlanetDaoService;
import org.example.dao.PlanetDaoServiceImpl;
import org.example.service.ClientService;
import org.example.service.ClientServiceImpl;
import org.example.service.PlanetCrudService;
import org.example.service.PlanetCrudServiceImpl;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getInstance();

        ClientDaoService clientDao = new ClientDaoServiceImpl(sessionFactory);
        ClientService clientService = new ClientServiceImpl(clientDao);

        PlanetDaoService planetDao = new PlanetDaoServiceImpl(sessionFactory);
        PlanetCrudService planetService = new PlanetCrudServiceImpl(planetDao);

        try {
            // CREATE
            Long clientId = clientService.create("John Doe");
            planetService.create("MARS", "Mars");

            System.out.println("Client ID: " + clientId);
            System.out.println("Planet created: MARS");

            // READ
            Client client = clientService.getById(clientId);
            Planet planet = planetService.getById("MARS");

            System.out.println("Client name: " + client.getName());
            System.out.println("Planet name: " + planet.getName());

            // UPDATE
            clientService.update(clientId, "John Updated");
            planetService.update("MARS", "Mars Planet");

            System.out.println("Updated client name: " + clientService.getById(clientId).getName());
            System.out.println("Updated planet name: " + planetService.getById("MARS").getName());

            // DELETE
            clientService.delete(clientId);
            planetService.delete("MARS");

            System.out.println("Client after delete: " + (clientService.getById(clientId) == null ? "deleted" : "exists"));
            System.out.println("Planet after delete: " + (planetService.getById("MARS") == null ? "deleted" : "exists"));

        } finally {
            sessionFactory.close();
        }
    }
}