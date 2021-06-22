package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.EventoUsuario;
import es.taw.grupo4.entity.EventoUsuarioPK;
import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoUsuarioRepository extends JpaRepository<EventoUsuario, EventoUsuarioPK> {

    @Query("SELECT eu FROM EventoUsuario eu WHERE eu.evento.idevento = :id")
    List<EventoUsuario> findByEventoId(Integer id);
}
