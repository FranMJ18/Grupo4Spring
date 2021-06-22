package es.taw.grupo4.controller;

import es.taw.grupo4.dao.EventoRepository;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class InicioController {

    private UsuarioService usuarioService;
    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }



    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String doInit(){
        return "index";
    }

    @GetMapping("/login")
    public String doLogin(Model model){
        model.addAttribute("usuario", new UsuarioDto());
        return "InicioSesion";
    }

    @GetMapping("/register")
    public String doRegister(Model model){
        model.addAttribute("usuario", new UsuarioDto());
        model.addAttribute("sexo", new String[]{"Hombre", "Mujer", "Otro"});
        return "RegistroUsuario";
    }

    @PostMapping("/iniciar")
    public String doIniciar(@ModelAttribute("usuario") UsuarioDto usuarioDto, Model model, HttpSession session){
        Usuario us = this.usuarioService.findByCredenciales(usuarioDto.getUsuario(), usuarioDto.getContraseña());
        if(us == null){
            model.addAttribute("error", "Credenciales inválidas");
            return this.doLogin(model);
        }
        session.setAttribute("usuario", us);
        return doListarEventos(model);
    }

    @GetMapping("/events")
    public String doListarEventos(Model model){
        model.addAttribute("eventos", eventoRepository.findAll());
        return "ListaEventos";
    }
}
