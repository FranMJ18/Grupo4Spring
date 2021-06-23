package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Asientos;
import es.taw.grupo4.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("select a from Asientos a where a.evento1.idevento = :id and a.ocupado = 0")
    List<Asientos> findAsientosLibresByEventoId(Integer id);

    @Query("select e from Evento e where e.costeEntrada>= :min and e.costeEntrada<= :max and e.titulo like :nombre ")
    List<Evento> filtrarEventos(int min, int max,String nombre) ;
}
