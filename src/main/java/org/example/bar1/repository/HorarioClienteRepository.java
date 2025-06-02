package org.example.bar1.repository;

import org.example.bar1.model.ClienteHorario;
import org.springframework.data.repository.CrudRepository;

public interface HorarioClienteRepository extends CrudRepository<ClienteHorario, Long> {
    //Buscar por id
    ClienteHorario findById(long id);
}
