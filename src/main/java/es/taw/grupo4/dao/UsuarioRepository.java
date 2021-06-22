package es.taw.grupo4.dao;

import es.taw.grupo4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.nickname = :nombre AND u.password = :password")
    Usuario findByNombreYPass(String nombre, String password);
}
