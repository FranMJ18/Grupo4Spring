package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Asientos;
import es.taw.grupo4.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("select a from Asientos a where a.evento1.idevento = :id and a.ocupado = 0")
    List<Asientos> findAsientosLibresByEventoId(Integer id);
}
