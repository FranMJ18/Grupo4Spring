package es.taw.grupo4.service;

import es.taw.grupo4.dao.UsuarioEventoRepository;
import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.entity.UsuarioEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEventoService {

    private UsuarioEventoRepository usuarioEventoRepository;

    @Autowired
    public void setUsuarioEventoRepository(UsuarioEventoRepository usuarioEventoRepository){
        this.usuarioEventoRepository = usuarioEventoRepository;
    }


    public void guardarUsuarioEvento(UsuarioEvento usuarioEvento){
        usuarioEventoRepository.save(usuarioEvento);
    }
}
