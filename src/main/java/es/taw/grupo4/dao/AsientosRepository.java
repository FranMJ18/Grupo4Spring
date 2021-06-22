package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Asientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsientosRepository extends JpaRepository<Asientos, Integer> {
}
