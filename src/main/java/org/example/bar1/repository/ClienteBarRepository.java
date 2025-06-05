package org.example.bar1.repository;

import org.example.bar1.model.ClienteBar;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteBarRepository extends CrudRepository<ClienteBar, Long> {
    // buscar por id
    ClienteBar findById(long id);
    @Query("SELECT c.* FROM cliente_bar c JOIN cliente_horario ch ON c.id = ch.cliente_id WHERE ch.horario_id = :horarioId")
    List<ClienteBar> findClientesByHorarioId(@Param("horarioId") Long horarioId);
}
