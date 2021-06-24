package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.EventoUsuario;
import es.taw.grupo4.entity.EventoUsuarioPK;
import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventoUsuarioRepository extends JpaRepository<EventoUsuario, EventoUsuarioPK> {

    @Query("SELECT eu FROM EventoUsuario eu WHERE eu.evento.idevento = :id")
    List<EventoUsuario> findByEventoId(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EVENTO_USUARIO (USUARIO, IDEVENTO, FILA, COLUMNA, EVENTO) VALUES (:usuario, :idevento, :fila, :columna, :idevento)", nativeQuery = true)
    void createEventoUsuarioConAsiento(Integer usuario, Integer idevento, Integer fila, Integer columna);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EVENTO_USUARIO (USUARIO, IDEVENTO, EVENTO) VALUES (:usuario, :idevento, :idevento)", nativeQuery = true)
    void createEventoUsuarioSinAsiento(Integer usuario, Integer idevento);
}
