package es.taw.grupo4.controller;

import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.EventoService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("administrador")
public class AdministradorController {

    private UsuarioService usuarioService;

    private EventoService eventoService;

    @Autowired
    public void setEventoService (EventoService eventoService){
        this.eventoService = eventoService;
    }

    @Autowired
    public void setUsuarioService (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String doInit(HttpSession session){
        session.setAttribute("listaUsuarios", usuarioService.findAll());
        return "Administrador";
    }

    @GetMapping("/cerrarSesion")
    public String doEnd(HttpSession session){
        session.removeAttribute("usuario");
        return "redirect:/";
    }

    @GetMapping("/perfil")
    public String doPerfil(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("listaEventos", ((Usuario) session.getAttribute("usuario")).getEventoList());
        return "Perfil";
    }

}
