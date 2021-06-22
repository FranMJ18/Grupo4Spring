package es.taw.grupo4.service;

import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<UsuarioDto> findAll(){
        List<Usuario> aux = usuarioRepository.findAll();
        List<UsuarioDto> usuarios = new ArrayList<>();
        for(Usuario u : aux){
            usuarios.add(u.getDto());
        }

        return usuarios;
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario findRandomTeleoperador(int id) {
        Usuario us = this.usuarioRepository.findRandomTeleoperador(id);
        if(us != null){
            return us;
        } else {
            return null;
        }
    }
}
