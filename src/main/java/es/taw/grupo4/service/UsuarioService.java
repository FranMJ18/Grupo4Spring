package es.taw.grupo4.service;

import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByCredenciales(String nombre, String password){
        return usuarioRepository.findByNombreYPass(nombre, password);
    }
    
}
