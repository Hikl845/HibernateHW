package org.example;

public interface ClientDaoService {

    Long create(String name);

    Client getById(Long id);

    void update(Long id, String name);

    void delete(Long id);
}