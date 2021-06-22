package es.taw.grupo4.dao;

import es.taw.grupo4.entity.UsuarioEvento;
import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {
}
