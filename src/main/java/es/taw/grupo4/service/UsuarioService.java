package es.taw.grupo4.service;

import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<Usuario> us = this.usuarioRepository.findRandomTeleoperador(id);

        if(us.isEmpty()){
            return null;
        } else {
            Random rnd = new Random();
            int idRnd = rnd.nextInt(us.size());
            return us.get(idRnd).getDto();
        }
    }

    public List<String> listarTodosLosTeleoperadoresPorNombres(){
        List<Usuario> teleopList = this.usuarioRepository.findAllTeleoperadores();
        List<String> lista = new ArrayList<>();
        for(Usuario u : teleopList){
            lista.add(u.getNickname());
        }

        return lista;
    }

    public List<String> listarTodosCreadoresUsuariosEventoPorNombres(){
        List<Usuario> usList = this.usuarioRepository.findAllCreadoresUsuariosEvento();
        List<String> lista = new ArrayList<>();
        for(Usuario u : usList){
            lista.add(u.getNickname());
        }

        return lista;
    }

    public UsuarioDto findByNombre(String usuario) {
        Usuario usr = this.usuarioRepository.findbyNickname(usuario);
        if(usr != null){
            return usr.getDto();
        } else {
            return null;
        }
    }
}
