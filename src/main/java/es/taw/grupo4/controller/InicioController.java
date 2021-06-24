package es.taw.grupo4.controller;
import es.taw.grupo4.dto.EventoDto;
import es.taw.grupo4.dto.FiltroEvento;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.entity.UsuarioEvento;
import es.taw.grupo4.service.EventoService;
import es.taw.grupo4.service.EventoUsuarioService;
import es.taw.grupo4.service.UsuarioEventoService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class InicioController {

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
        Usuario us = this.usuarioService.findByCredenciales(usuarioDto.getUsuario(), usuarioDto.getPassword());
        if(us == null){
            model.addAttribute("error", "Credenciales inválidas");
            return this.doLogin(model);
        }

        usuarioDto.setId(us.getIdusuario());
        usuarioDto.setRol(us.getRol());
        session.setAttribute("usuario", usuarioDto);

        return doPantallaInicio(session);
    }

    @GetMapping("/pantallaInicio")
    public String doPantallaInicio(HttpSession session){

        UsuarioDto us = (UsuarioDto) session.getAttribute("usuario");
        switch (us.getRol()){
            //CREADOR DE EVENTO
            case 0 : return "redirect:evento/events";
            //ADMINISTRADOR
            case 1 : return "redirect:administrador/";
            //TELEOPERADOR
            case 2 : return "redirect:chat/";
            //ANALISTA DE EVENTOS
            case 3 : return "redirect:filtro/";
            //USUARIO DE EVENTO
            case 4 : return "redirect:evento/events";// return doListarEventos(new FiltroEvento(), model);
        }
        return null;
    }

    @GetMapping("/perfil")
    public String doPerfil(Model model, HttpSession session){
        UsuarioDto uDto = (UsuarioDto) session.getAttribute("usuario");
        Usuario u = usuarioService.findById(uDto.getId());
        uDto = u.getDto();
        model.addAttribute("usuario", uDto);
        model.addAttribute("listaEventos", u.getEventoList());
        return "Perfil";
    }

    @GetMapping("/editarPerfilUsuario/{id}")
    public String doEditarPerfilUsuario(@PathVariable("id") Integer id, Model model){
        UsuarioDto usrDto = this.usuarioService.findById(id).getDto();
        model.addAttribute("usuario", usrDto);
        model.addAttribute("sexo", new String[]{"Hombre", "Mujer", "Otro"});

        return "EditarPerfil";
    }

    @PostMapping("/guardarPerfilUsuario")
    public String doGuardarPerfilUsuario(@ModelAttribute("usuario") UsuarioDto usuario){
        Usuario us = new Usuario();
        UsuarioEvento usuarioEvento = new UsuarioEvento();

        try{
            us = this.usuarioService.findById(usuario.getId());
            if(us.getRol() == 4){
                usuarioEvento = us.getUsuarioEvento();
            }
        }catch (Exception e){System.err.println("No se ha encontrado");}

        us.setNickname(usuario.getUsuario());
        us.setPassword(usuario.getPassword());

        usuarioService.guardarUsuario(us);
        if(usuario.getRol() == 4){
            usuarioEvento.setUsuario(us.getIdusuario());
            usuarioEvento.setCiudad(usuario.getCiudad());
            usuarioEvento.setDomicilio(usuario.getDomicilio());
            usuarioEvento.setApellido(usuario.getApellidos());
            usuarioEvento.setEdad(usuario.getEdad());
            usuarioEvento.setNombre(usuario.getNombre());
            usuarioEvento.setSexo(usuario.getSexo());
            usuarioEvento.setUsuario1(us);

            us.setUsuarioEvento(usuarioEvento);
        }

        usuarioService.guardarUsuario(us);

        if(usuario.getRol() == 4){
            usuarioEventoService.guardarUsuarioEvento(usuarioEvento);
        }
        return "redirect:/perfil";
    }

    @PostMapping("/registrar")
    public String doRegistrar(@ModelAttribute("usuario") UsuarioDto usuario, Model model, HttpSession session){

        if(this.usuarioService.findByNombre(usuario.getUsuario()) != null){
            model.addAttribute("error", "Error: el nombre de usuario ya está registrado");
            return doRegister(model);
        }
        return doEditarPerfil(usuario, session);
    }

    @GetMapping("/editarPerfil")
    public String doEditarPerfil(UsuarioDto usuario, HttpSession session){
        Usuario us = new Usuario();
        UsuarioEvento usuarioEvento = new UsuarioEvento();

        try{
            us = this.usuarioService.findById(usuario.getId());
            if(us.getRol() == 4){
                usuarioEvento = us.getUsuarioEvento();
            }
        }catch (Exception e){System.err.println("No se ha encontrado");}

        us.setNickname(usuario.getUsuario());
        us.setPassword(usuario.getPassword());
        us.setRol(session.getAttribute("usuario") == null ? 4 : us.getRol());

        usuarioService.guardarUsuario(us);
        if(usuario.getRol() == 4){
            usuarioEvento.setUsuario(us.getIdusuario());
            usuarioEvento.setCiudad(usuario.getCiudad());
            usuarioEvento.setDomicilio(usuario.getDomicilio());
            usuarioEvento.setApellido(usuario.getApellidos());
            usuarioEvento.setEdad(usuario.getEdad());
            usuarioEvento.setNombre(usuario.getNombre());
            usuarioEvento.setSexo(usuario.getSexo());
            usuarioEvento.setUsuario1(us);

            us.setUsuarioEvento(usuarioEvento);
        }

        usuarioService.guardarUsuario(us);

        if(usuario.getRol() == 4){
            usuarioEventoService.guardarUsuarioEvento(usuarioEvento);
        }

        if(session.getAttribute("usuario") == null){
            session.setAttribute("usuario", usuario);
        }

        switch (((UsuarioDto) (session.getAttribute("usuario"))).getRol()){
            //CREADOR DE EVENTO
            case 0 : return "redirect:evento/events";//doListarEventos(new FiltroEvento(), model);
            //ADMINISTRADOR
            case 1 : return "redirect:administrador/";
            //TELEOPERADOR
            case 2 : return "redirect:chat/";
            //ANALISTA DE EVENTOS
            case 3 : return "redirect:administrador/";//return doListarEventos(new FiltroEvento(), model);
            //USUARIO DE EVENTO
            case 4 : return "redirect:evento/events";// return doListarEventos(new FiltroEvento(), model);
        }
        return null;
    }

    // Cierra la sesion y te devuelve a index.jsp
    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.removeAttribute("usuario");
        return "index";
    }

   /* @PostMapping("/events")
    public String doListarEventos(@ModelAttribute("filtro") FiltroEvento filtro, Model model){

        model.addAttribute("eventos", eventoService.findByFilter(filtro));
        model.addAttribute("filtro", filtro);
        return "ListaEventos";
    }

    @GetMapping("/showEvent/{id}")
    public String doShowEvent(@PathVariable Integer id, Model model){
        model.addAttribute("evento", eventoService.findById(id));
        model.addAttribute("listaEventoUsuario", eventoUsuarioService.findByEventoId(id));
        return "MostrarEvento";
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

        eventoService.createOrSaveEvent(evento, (Usuario) session.getAttribute("usuario"));
        return doListarEventos(new FiltroEvento(), model);
    }*/
}
