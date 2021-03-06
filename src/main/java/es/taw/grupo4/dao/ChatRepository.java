package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat c WHERE c.usuario1.idusuario = :us")
    public List<Chat> findMisChatsTeleoperador(Integer us);

    @Query("SELECT c FROM Chat c WHERE c.usuario1.idusuario != :us")
    public List<Chat> findOtrosChatsTeleoperador(Integer us);

    @Query("SELECT c FROM Chat c WHERE c.usuario2.idusuario = :us")
    public List<Chat> findMisChatsUsuario(Integer us);

    @Query("SELECT c FROM Chat c WHERE c.usuario1.idusuario != :tele AND (upper(c.usuario1.nickname) LIKE %:name% OR upper(c.usuario2.nickname) LIKE %:name%)")
    public List<Chat> findOtrosChatsTeleoperadorFiltro(Integer tele, String name);

    @Query("SELECT c FROM Chat c WHERE c.usuario1.idusuario = :tele AND (upper(c.usuario1.nickname) LIKE %:name% OR upper(c.usuario2.nickname) LIKE %:name%)")
    public List<Chat> findMisChatsTeleoperadorFiltro(Integer tele, String name);

    @Query("SELECT c FROM Chat c WHERE c.usuario2.idusuario = :us AND (upper(c.usuario1.nickname) LIKE %:name% OR upper(c.usuario2.nickname) LIKE %:name%)")
    public List<Chat> findMisChatsUsuarioFiltro(Integer us, String name);

    @Query("SELECT c FROM Chat c WHERE c.usuario1.idusuario = :id AND c.usuario2.idusuario = :id1")
    public List<Chat> findByTeleOpAndUser(int id, int id1);
}
