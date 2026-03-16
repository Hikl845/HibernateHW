package org.example.service;

import org.example.entity.Client;

public interface ClientService {
    Long create(String name);
    Client getById(Long id);
    void update(Long id, String name);
    void delete(Long id);
}