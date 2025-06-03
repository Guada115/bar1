package org.example.bar1.repository;

import org.example.bar1.model.HorarioBar;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorarioBarRepository extends  CrudRepository<HorarioBar, Long>{
    //buscar id
    HorarioBar findById(long id);

    @Query("SELECT h.* FROM horario_bar h JOIN cliente_horario ch ON h.id = ch.horario_id WHERE ch.cliente_id = :clienteId")
    List<HorarioBar> findHorariosByClienteId(@Param("clienteId") Long clienteId);

}
