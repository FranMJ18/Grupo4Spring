package es.taw.grupo4.service;

import es.taw.grupo4.dao.AsientosRepository;
import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dto.EventoDto;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.Asientos;
import es.taw.grupo4.entity.AsientosPK;
import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    private AsientosRepository asientosRepository;

    @Autowired
    public void setAsientosRepository(AsientosRepository asientosRepository) {
        this.asientosRepository = asientosRepository;
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
            if(evento.getAsientosFijos()){
                e.setAforo(evento.getFilas() * evento.getColumnas());
            }else{
                e.setAforo(evento.getAforo());
            }
        }
        e.setAireLibre(evento.getAireLibre() ? 1 : 0);
        e.setArte(evento.getArte() ? 1 : 0);
        e.setConferencia(evento.getConferencia() ? 1 : 0);
        e.setDeporte(evento.getDeporte() ? 1 : 0);
        e.setFormacion(evento.getFormacion() ? 1 : 0);
        e.setGaming(evento.getGaming() ? 1 : 0);
        e.setLectura(evento.getLectura() ? 1 : 0);
        e.setMusica(evento.getMusica() ? 1 : 0);
        e.setTeatro(evento.getTeatro() ? 1 : 0);
        e.setTurismo(evento.getTurismo() ? 1 : 0);
        e.setBenefico(evento.getBenefico() ? 1 : 0);

        e.setCosteEntrada(evento.getPrecio());
        e.setTitulo(evento.getTitulo());
        e.setAsientosFijos(evento.getAsientosFijos());
        e.setDescripcion(evento.getDescripcion());
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            e.setFecha(fecha.parse(evento.getFechaInicio()));
            e.setFechaReserva(fecha.parse(evento.getFechaFin()));
        }catch(Exception exc){

        }
        eventoRepository.save(e);
        if(evento.getId() == null && evento.getAsientosFijos()){
            List<Asientos> list= new ArrayList<>();
            for(int i = 0; i<evento.getFilas(); i++){
                for(int j = 0; j<evento.getColumnas();j++){

                    AsientosPK pk = new AsientosPK();
                    pk.setEvento(e.getIdevento());
                    pk.setFila(i);
                    pk.setColumna(j);
                    Asientos a = new Asientos(pk);
                    a.setOcupado(0);
                    a.setEventoUsuarioList(new ArrayList<>());
                    asientosRepository.save(a);
                    list.add(a);

                }
            }
            e.setAsientosList(list);
            e.setFilas(evento.getFilas());
            e.setColumnas(evento.getColumnas());
            eventoRepository.save(e);
        }
    }
}
