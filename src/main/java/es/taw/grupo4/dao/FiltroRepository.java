package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FiltroRepository extends JpaRepository<Filtro, Integer> {
    @Query("SELECT f FROM Filtro f WHERE f.filtroPK.analistaeventos = :analistaeventos")
    public List<Filtro> filtrosCreador(int analistaeventos);

    @Query("SELECT f FROM Filtro f WHERE f.filtroPK.analistaeventos = :analistaeventos AND f.filtroPK.idfiltro = :idfiltro")
    public List<Filtro> findByPK(int analistaeventos, int idfiltro);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO FILTRO (ANALISTAEVENTOS, NOMBRE, EDAD_LIM_INF, EDAD_LIM_SUP, SEXO, CIUDAD, ANYO, COSTE_ENTRADA, CATEGORIA, USUARIO) VALUES (:analista, :nombre, :edadInf, :edadSup, :sexo, :ciudad, :anyo, :coste, :categoria, :usuario)", nativeQuery = true)
    public void crearFiltro(Integer analista, String nombre, Integer edadInf, Integer edadSup, String sexo, String ciudad, Integer anyo, Integer coste, String categoria, Integer usuario);

}
