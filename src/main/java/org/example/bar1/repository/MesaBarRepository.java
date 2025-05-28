package org.example.bar1.repository;

import org.example.bar1.model.MesaBar;
import org.springframework.data.repository.CrudRepository;

public interface MesaBarRepository extends CrudRepository<MesaBar, Long> {
    // Buscar mesa por ID
    MesaBar findById(long id);
}
