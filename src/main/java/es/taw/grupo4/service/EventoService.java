package es.taw.grupo4.service;

import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dto.EventoDto;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> findByFilter(FiltroEvento filtro){
        return eventoRepository.findAll(); // TODO filtrar algo supongo
    }

    public Evento findById(Integer id)
    {
        return eventoRepository.findById(id).get();
    }

    public void eraseEventoById(Integer id) {
        Evento e = eventoRepository.getById(id);
        eventoRepository.delete(e);
    }

    public void createOrSaveEvent(EventoDto evento, Usuario creador)
    {
        Evento e;
        if(evento.getId() != null) // Edit
        {
            e = eventoRepository.getById(evento.getId());
        }
        else // Create
        {
            e = new Evento();
            e.setCreadorevento(creador);
        }

        // TODO

        eventoRepository.save(e);
    }
}
