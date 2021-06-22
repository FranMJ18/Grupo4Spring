package es.taw.grupo4.service;

import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.Evento;
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
}
