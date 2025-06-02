package org.example.bar1.repository;

import org.example.bar1.model.ClienteBar;
import org.springframework.data.repository.CrudRepository;

public interface ClienteBarRepository extends CrudRepository<ClienteBar, Long> {
    // buscar por id
    ClienteBar findById(long id);
}
