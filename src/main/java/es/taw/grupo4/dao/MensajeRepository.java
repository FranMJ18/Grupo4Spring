package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Mensaje;
import es.taw.grupo4.entity.MensajePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, MensajePK> {

    @Query("SELECT m FROM Mensaje m WHERE m.chat1.idchat = :idC")
    public List<Mensaje> findByChat(Integer idC);
}
