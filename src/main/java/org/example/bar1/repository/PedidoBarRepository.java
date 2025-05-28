package org.example.bar1.repository;

import org.example.bar1.model.PedidoBar;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PedidoBarRepository extends CrudRepository<PedidoBar, Long> {
    //Buscar pedido por mesaId
    List<PedidoBar> findByMesaId(Long mesaId);
}
