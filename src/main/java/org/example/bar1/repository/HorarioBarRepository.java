package org.example.bar1.repository;

import org.example.bar1.model.HorarioBar;
import org.springframework.data.repository.CrudRepository;

public interface HorarioBarRepository extends  CrudRepository<HorarioBar, Long>{
    //buscar id
    HorarioBar findById(long id);

}
