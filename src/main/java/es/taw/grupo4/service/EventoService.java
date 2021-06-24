package es.taw.grupo4.service;

import es.taw.grupo4.dao.AsientosRepository;
import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dao.EventoUsuarioRepository;
import es.taw.grupo4.dao.UsuarioEventoRepository;
import es.taw.grupo4.dto.EventoDto;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    private EventoUsuarioRepository eventoUsuarioRepository;

    @Autowired
    public void setEventoUsuarioRepository(EventoUsuarioRepository eventoUsuarioRepository){
        this.eventoUsuarioRepository = eventoUsuarioRepository;
    }

    private AsientosRepository asientosRepository;

    @Autowired
    public void setAsientosRepository(AsientosRepository asientosRepository) {
        this.asientosRepository = asientosRepository;
    }

    private UsuarioEventoRepository usuarioEventoRepository;

    @Autowired
    public void setUsuarioEventoRepository(es.taw.grupo4.dao.UsuarioEventoRepository usuarioEventoRepository) {
        this.usuarioEventoRepository = usuarioEventoRepository;
    }

    public List<Evento> findByFilter(FiltroEvento filtro){

        List<Evento> res = new ArrayList<>();

        for (Evento e: eventoRepository.filtrarEventos(filtro.getMin()==null ? Integer.MIN_VALUE : filtro.getMin(), filtro.getMax()==null ? Integer.MAX_VALUE : filtro.getMax(),  filtro.getNombre()==null ? "%%" : "%" + filtro.getNombre() + "%")          ) {
            if((filtro.getAireLibre() && e.getAireLibre() != 1)
                    || (filtro.getArte() && e.getArte() != 1)
                    || (filtro.getBenefico() && e.getBenefico() != 1)
                    || (filtro.getMusica() && e.getMusica() != 1)
                    || (filtro.getDeporte() && e.getDeporte() != 1)
                    || (filtro.getTeatro() && e.getTeatro() != 1)
                    || (filtro.getGaming() && e.getGaming() != 1)
                    || (filtro.getLectura() && e.getLectura() != 1)
                    || (filtro.getFormacion() && e.getFormacion() != 1)
                    || (filtro.getConferencia() && e.getConferencia() != 1)
                    || (filtro.getTurismo() && e.getTurismo() != 1) ) {

            } else {res.add(e);}
        }
        return res;
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

        e.setCosteEntrada(evento.getCosteEntrada());
        e.setTitulo(evento.getTitulo());
        e.setAsientosFijos(evento.getAsientosFijos());
        e.setDescripcion(evento.getDescripcion());
        e.setMaxNumEntradas(evento.getMaxEntradas());
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

    public List<Asientos> getAsientosLibres(Integer id)
    {
        return eventoRepository.findAsientosLibresByEventoId(id);
    }

    public void buyTicket(Integer eventoId, Integer usuarioId, String asientoString) {
        Evento e = eventoRepository.getById(eventoId);
        UsuarioEvento ue = usuarioEventoRepository.getById(usuarioId);
/*
        EventoUsuarioPK epk = new EventoUsuarioPK();
        epk.setIdevento(eventoId);
        epk.setUsuario(usuarioId);
        EventoUsuario eu = new EventoUsuario(epk);
        eu.setEvento(e);
        eu.setUsuarioEvento(ue);
        */
        if(e.getAsientosFijos()){
            String[] asiento = asientoString.split(" ");
            Integer fila = Integer.parseInt(asiento[0]), columna =Integer.parseInt(asiento[1]);
            eventoUsuarioRepository.createEventoUsuarioConAsiento(usuarioId, eventoId, fila, columna);
            Asientos a = asientosRepository.getById(new AsientosPK(fila,columna, eventoId));
            a.setOcupado(1);
            asientosRepository.save(a);

            //eu.setAsientos(a);



        } else{
            eventoUsuarioRepository.createEventoUsuarioSinAsiento(usuarioId, eventoId);
        }

        //eventoUsuarioRepository.save(eu);
    }


}
