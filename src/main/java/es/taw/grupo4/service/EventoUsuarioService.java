package es.taw.grupo4.service;

import es.taw.grupo4.dao.AsientosRepository;
import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dao.EventoUsuarioRepository;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.EventoUsuario;
import es.taw.grupo4.entity.EventoUsuarioPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoUsuarioService {
    private EventoUsuarioRepository eventoUsuarioRepository;

    private AsientosRepository asientosRepository;

    @Autowired
    public void setAsientosRepository(AsientosRepository asientosRepository) {
        this.asientosRepository = asientosRepository;
    }

    @Autowired
    public void setEventoUsuarioRepository(EventoUsuarioRepository eventoUsuarioRepository){
        this.eventoUsuarioRepository = eventoUsuarioRepository;
    }

    public List<EventoUsuario> findByEventoId(Integer id)
    {
        return eventoUsuarioRepository.findByEventoId(id);
    }

    public EventoUsuario findByEventoUsuarioId(EventoUsuarioPK id){return eventoUsuarioRepository.getById(id);}

    public void cancelTicket(EventoUsuarioPK id){

        EventoUsuario eu = eventoUsuarioRepository.getById(id);
        if(eu.getEvento().getAsientosFijos()){
            eu.getAsientos().setOcupado(0);
            asientosRepository.save(eu.getAsientos());
        }
        eventoUsuarioRepository.delete(eu);
    }
}
