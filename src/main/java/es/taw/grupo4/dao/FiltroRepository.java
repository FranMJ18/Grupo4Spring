package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FiltroRepository extends JpaRepository<Filtro, Integer> {
    @Query("SELECT f FROM Filtro f WHERE f.filtroPK.analistaeventos = :analistaeventos")
    public List<Filtro> filtrosCreador(int analistaeventos);
}
