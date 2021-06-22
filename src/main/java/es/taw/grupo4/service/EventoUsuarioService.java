package es.taw.grupo4.service;

import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dao.EventoUsuarioRepository;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.EventoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoUsuarioService {
    private EventoUsuarioRepository eventoUsuarioRepository;

    @Autowired
    public void setEventoUsuarioRepository(EventoUsuarioRepository eventoUsuarioRepository){
        this.eventoUsuarioRepository = eventoUsuarioRepository;
    }

    public List<EventoUsuario> findByEventoId(Integer id)
    {
        return eventoUsuarioRepository.findByEventoId(id);
    }

}
