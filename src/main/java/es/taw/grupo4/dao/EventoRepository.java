package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
