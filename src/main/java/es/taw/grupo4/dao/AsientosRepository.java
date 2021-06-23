package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Asientos;
import es.taw.grupo4.entity.AsientosPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsientosRepository extends JpaRepository<Asientos, AsientosPK> {
}
