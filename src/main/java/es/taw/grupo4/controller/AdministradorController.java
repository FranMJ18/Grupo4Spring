package es.taw.grupo4.controller;

import es.taw.grupo4.dto.EventoUsuarioDto;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.EventoUsuario;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.entity.UsuarioEvento;
import es.taw.grupo4.service.EventoService;
import es.taw.grupo4.service.EventoUsuarioService;
import es.taw.grupo4.service.UsuarioEventoService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("administrador")
public class AdministradorController {

    private UsuarioService usuarioService;

    private UsuarioEventoService usuarioEventoService;

    private EventoService eventoService;
    private EventoUsuarioService eventoUsuarioService;

    @Autowired
    public void setEventoUsuarioService(EventoUsuarioService eventoUsuarioService){
        this.eventoUsuarioService = eventoUsuarioService;
    }
    @Autowired
    public void setUsuarioEventoService (UsuarioEventoService usuarioEventoService) { this.usuarioEventoService = usuarioEventoService; }

    @Autowired
    public void setEventoService (EventoService eventoService){
        this.eventoService = eventoService;
    }

    @Autowired
    public void setUsuarioService (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String doInit(HttpSession session, Model model){
        session.setAttribute("listaUsuarios", usuarioService.findAll());
        model.addAttribute("usuario", new UsuarioDto());
        return "Administrador";
    }

    @GetMapping("/cerrarSesion")
    public String doEnd(HttpSession session){
        session.removeAttribute("usuario");
        return "redirect:/";
    }



    @GetMapping("/usuario/{id}")
    public String doUsuario(@PathVariable("id") Integer id, Model model, HttpSession session){
        model.addAttribute("usuario", usuarioService.findById(id).getDto());
        List<EventoUsuario> aux = eventoUsuarioService.findByUsuarioId(id);
        List<EventoUsuarioDto> listaEventos = new ArrayList<>();

        for(EventoUsuario e : aux){
            listaEventos.add(e.getDto());
        }

        model.addAttribute("listaEventos",listaEventos);
        return "Perfil";
    }

    @GetMapping("/editar/{id}")
    public String doEditar(@PathVariable("id") Integer id, Model model){
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario.getDto());
        model.addAttribute("sexo", new String[]{"Hombre", "Mujer", "Otro"});
        return "EditarUsuario";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable("id") Integer id, HttpSession session, Model model){
        Usuario usuario = usuarioService.findById(id);
        usuarioService.borrarUsuario(usuario);
        return "redirect:/administrador/";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("usuario") UsuarioDto usuarioDto){
        Usuario usuario = usuarioService.findById(usuarioDto.getId());
        usuario.setNickname(usuarioDto.getUsuario());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(usuarioDto.getRol());
        usuarioService.guardarUsuario(usuario);

        //ES UN USUARIO DE EVENTO
        if(usuario.getRol() == 4){
            UsuarioEvento usuarioEvento = new UsuarioEvento();
            usuarioEvento.setUsuario1(usuario);
            usuarioEvento.setUsuario(usuario.getIdusuario());
            usuarioEvento.setSexo(usuarioDto.getSexo());
            usuarioEvento.setNombre(usuarioDto.getNombre());
            usuarioEvento.setApellido(usuarioDto.getApellidos());
            usuarioEvento.setEdad(usuarioDto.getEdad());
            usuarioEvento.setDomicilio(usuarioDto.getDomicilio());
            usuarioEvento.setCiudad(usuarioDto.getCiudad());

            usuario.setUsuarioEvento(usuarioEvento);

            usuarioService.guardarUsuario(usuario);
            usuarioEventoService.guardarUsuarioEvento(usuarioEvento);
        }
        return "redirect:/administrador/";
    }

    @PostMapping("/anyadirUsuario")
    public String doAnyadir(@ModelAttribute("usuario") UsuarioDto usuarioDto, Model model, HttpSession session){
        if(this.usuarioService.findByNombre(usuarioDto.getUsuario()) != null){
            model.addAttribute("error", "Error: el nombre de usuario ya está registrado");
            return doInit(session, model);
        }
        Usuario usuario = new Usuario();
        usuario.setNickname(usuarioDto.getUsuario());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(usuarioDto.getRol());

        if (usuarioDto.getId() != 0) {
            usuario.setIdusuario(usuarioDto.getId());
        }
        usuarioService.guardarUsuario(usuario);

        //ES UN USUARIO DE EVENTO
        if(usuario.getRol() == 4){
            UsuarioEvento usuarioEvento = new UsuarioEvento();
            usuarioEvento.setUsuario1(usuario);
            usuarioEvento.setUsuario(usuario.getIdusuario());
            usuarioEvento.setSexo(usuarioDto.getSexo());
            usuarioEvento.setNombre(usuarioDto.getNombre());
            usuarioEvento.setApellido(usuarioDto.getApellidos());
            usuarioEvento.setEdad(usuarioDto.getEdad());
            usuarioEvento.setDomicilio(usuarioDto.getDomicilio());
            usuarioEvento.setCiudad(usuarioDto.getCiudad());

            usuario.setUsuarioEvento(usuarioEvento);

            usuarioService.guardarUsuario(usuario);
            usuarioEventoService.guardarUsuarioEvento(usuarioEvento);
        }
        return "redirect:/administrador/";
    }
}
