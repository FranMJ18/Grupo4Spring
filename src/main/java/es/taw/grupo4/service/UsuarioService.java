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

    public void borrarUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
    public UsuarioDto findRandomTeleoperador(int id) {
        Usuario us = this.usuarioRepository.findRandomTeleoperador(id);
        if(us != null){
            return us.getDto();
        } else {
            return null;
        }
    }

    public List<UsuarioDto> listarTodosLosTeleoperadores(){
        List<Usuario> teleopList = this.usuarioRepository.findAllTeleoperadores();
        List<UsuarioDto> lista = new ArrayList<>();
        for(Usuario u : teleopList){
            lista.add(u.getDto());
        }

        return lista;
    }

    public List<UsuarioDto> listarTodosCreadoresUsuariosEvento(){
        List<Usuario> usList = this.usuarioRepository.findAllCreadoresUsuariosEvento();
        List<UsuarioDto> lista = new ArrayList<>();
        for(Usuario u : usList){
            lista.add(u.getDto());
        }

        return lista;
    }
}
