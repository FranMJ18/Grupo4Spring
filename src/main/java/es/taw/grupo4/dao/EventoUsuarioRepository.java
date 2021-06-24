package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.EventoUsuario;
import es.taw.grupo4.entity.EventoUsuarioPK;
import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface EventoUsuarioRepository extends JpaRepository<EventoUsuario, EventoUsuarioPK> {

    @Query("SELECT eu FROM EventoUsuario eu WHERE eu.evento.idevento = :id")
    List<EventoUsuario> findByEventoId(Integer id);

    @Query("select eu from EventoUsuario eu where eu.usuarioEvento.edad >= :min")
    List<EventoUsuario> findByMinEdad(Integer min);

    @Query("select eu from EventoUsuario eu where eu.usuarioEvento.edad <= :max")
    List<EventoUsuario> findByMaxEdad(Integer max);

    @Query("select eu from EventoUsuario eu where eu.usuarioEvento.sexo like :sexo")
    List<EventoUsuario> findBySexo(String sexo);

    @Query("select eu from EventoUsuario eu where upper(eu.usuarioEvento.ciudad)  like upper(:ciudad)")
    List<EventoUsuario> findByCiudad(String ciudad);

    @Query("select eu from EventoUsuario eu where eu.evento.costeEntrada = :costeEntrada")
    List<EventoUsuario> findByCosteEntrada(Integer costeEntrada);

    @Query("select eu from EventoUsuario eu where eu.evento.musica = 1 ")
    List<EventoUsuario> findByMusica();

    @Query("select eu from EventoUsuario eu where eu.evento.aireLibre = 1 ")
    List<EventoUsuario> findByAireLibre();

    @Query("select eu from EventoUsuario eu where eu.evento.deporte = 1 ")
    List<EventoUsuario> findByDeporte();

    @Query("select eu from EventoUsuario eu where eu.evento.teatro = 1 ")
    List<EventoUsuario> findByTeatro();

    @Query("select eu from EventoUsuario eu where eu.evento.gaming = 1 ")
    List<EventoUsuario> findByGaming();

    @Query("select eu from EventoUsuario eu where eu.evento.lectura = 1 ")
    List<EventoUsuario> findByLectura();

    @Query("select eu from EventoUsuario eu where eu.evento.formacion = 1 ")
    List<EventoUsuario> findByFormacion();

    @Query("select eu from EventoUsuario eu where eu.evento.conferencia = 1 ")
    List<EventoUsuario> findByConferencia();

    @Query("select eu from EventoUsuario eu where eu.evento.benefico = 1 ")
    List<EventoUsuario> findByBenefico();

    @Query("select eu from EventoUsuario eu where eu.evento.arte = 1 ")
    List<EventoUsuario> findByArte();

    @Query("select eu from EventoUsuario eu where eu.evento.turismo = 1 ")
    List<EventoUsuario> findByTurismo();

    @Query("select eu from EventoUsuario eu where eu.evento.fecha between :inf and :sup")
    List<EventoUsuario> findByAnyo(Date inf, Date sup);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EVENTO_USUARIO (USUARIO, IDEVENTO, FILA, COLUMNA, EVENTO) VALUES (:usuario, :idevento, :fila, :columna, :idevento)", nativeQuery = true)
    void createEventoUsuarioConAsiento(Integer usuario, Integer idevento, Integer fila, Integer columna);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EVENTO_USUARIO (USUARIO, IDEVENTO, EVENTO) VALUES (:usuario, :idevento, :idevento)", nativeQuery = true)
    void createEventoUsuarioSinAsiento(Integer usuario, Integer idevento);
}
