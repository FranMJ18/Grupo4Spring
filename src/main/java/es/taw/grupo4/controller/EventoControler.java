package es.taw.grupo4.controller;

import es.taw.grupo4.dto.EventoDto;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.EventoService;
import es.taw.grupo4.service.EventoUsuarioService;
import es.taw.grupo4.service.UsuarioEventoService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("evento")

public class EventoControler {

    private UsuarioService usuarioService;

    private UsuarioEventoService usuarioEventoService;

    @Autowired
    public void setUsuarioEventoService(UsuarioEventoService usuarioEventoService){
        this.usuarioEventoService = usuarioEventoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    private EventoService eventoService;

    @Autowired
    public void setEventoService(EventoService eventoService){
        this.eventoService = eventoService;
    }


    private EventoUsuarioService eventoUsuarioService;

    @Autowired
    public void setEventoUsuarioService(EventoUsuarioService eventoUsuarioService){
        this.eventoUsuarioService = eventoUsuarioService;
    }

    @GetMapping("/showEvent/{id}")
    public String doShowEvent(@PathVariable Integer id, Model model){
        model.addAttribute("evento", eventoService.findById(id));
        model.addAttribute("listaEventoUsuario", eventoUsuarioService.findByEventoId(id));
        return "MostrarEvento";
    }

    @GetMapping("/events")
    public String doListarEventos(@ModelAttribute("filtro") FiltroEvento filtro, Model model){

        model.addAttribute("eventos", eventoService.findByFilter(filtro));
        model.addAttribute("filtro", filtro);
        return "ListaEventos";
    }

    @GetMapping("/eraseEvent/{id}")
    public String doEraseEvent(@PathVariable Integer id, Model model){
        eventoService.eraseEventoById(id);
        return doListarEventos(new FiltroEvento(), model);
    }

    @GetMapping("/createEvent")
    public String doCreateEvent(Model model){
        model.addAttribute("evento", new EventoDto());
        return "CrearEvento";
    }

    @PostMapping("/saveEvent")
    public String doSaveEvent(@ModelAttribute EventoDto evento, Model model, HttpSession session){

        UsuarioDto aux = (UsuarioDto) session.getAttribute("usuario");
        eventoService.createOrSaveEvent(evento,usuarioService.findById(aux.getId()) );
        return doListarEventos(new FiltroEvento(), model);
    }
}