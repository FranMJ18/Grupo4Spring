package es.taw.grupo4.controller;

import es.taw.grupo4.dto.*;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Mensaje;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.ChatService;
import es.taw.grupo4.service.MensajeService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("chat")
public class ChatController {

    private ChatService chatService;

    private UsuarioService usuarioService;

    private MensajeService mensajeService;

    @Autowired
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String doListarConversaciones(Model model, HttpSession session){
        FiltroBusquedaChat filtro = new FiltroBusquedaChat();

        return this.doFiltrarChats(filtro, model, session);
    }

    @PostMapping("/filtrar")
    public String doFiltrarChats(@ModelAttribute("filtro") FiltroBusquedaChat filtro, Model model, HttpSession session){
        UsuarioDto user = (UsuarioDto) session.getAttribute("usuario");
        if(user.getRol() == 2){
            List<ChatDto> misChats = this.chatService.listarMisConversaciones(user,filtro);
            model.addAttribute("chats",misChats);
            List<ChatDto> otrosChats = this.chatService.listarOtrasConversaciones(user,filtro);
            model.addAttribute("otrosChats",otrosChats);
        } else {
            List<ChatDto> misChats = this.chatService.listarMisConversaciones(user,filtro);
            model.addAttribute("chats",misChats);
        }

        model.addAttribute("filtro", filtro);
        if(filtro.getUsuario() != null){
            model.addAttribute("buscado","buscado");
        }

        return "Conversaciones";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrarConversacion(@PathVariable("id") Integer idchat){
        this.chatService.borrarChat(idchat);

        return "redirect:/chat/";
    }

    @GetMapping("/crear")
    public String doCrearChatAleatorio(Model model, HttpSession session){
        UsuarioDto usr = (UsuarioDto) session.getAttribute("usuario");
        UsuarioDto teleOpRandom = this.usuarioService.findRandomTeleoperador(usr.getId());
        if(teleOpRandom != null){
            ChatDto chat = this.chatService.crearChat(teleOpRandom, usr);

            return "redirect:/chat/mostrar/" + chat.getIdchat();

        } else {
            model.addAttribute("error","Error: No hay teleoperadores adicionales disponibles");

            return this.doListarConversaciones(model, session);
        }
    }

    @GetMapping("/crearManual")
    public String doCrearChatManual(Model model){
        ChatDto chat = new ChatDto();
        model.addAttribute("chat",chat);
        model.addAttribute("teleoperadores",this.usuarioService.listarTodosLosTeleoperadoresPorNombres());
        model.addAttribute("usuarios",this.usuarioService.listarTodosCreadoresUsuariosEventoPorNombres());

        return "CrearChatManual";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("chat") ChatDto chat, Model model){
        UsuarioDto usr1 = this.usuarioService.findByNombre(chat.getUsuario1());
        UsuarioDto usr2 = this.usuarioService.findByNombre(chat.getUsuario2());
        if(!this.chatService.existeChat(usr1, usr2)){
            this.chatService.crearChat(usr1, usr2);

            return "redirect:/chat/";
        }

        model.addAttribute("error", "Error: Esa conversación ya existe");
        return this.doCrearChatManual(model);
    }

    @GetMapping("/mostrar/{id}")
    public String doMostrarChat(@PathVariable("id") Integer id, Model model){
        List<MensajeDto> msgs = this.mensajeService.listarMensajesPorChat(id);
        ChatDto chat = this.chatService.findById(id);
        model.addAttribute("chat",chat);
        model.addAttribute("mensajes",msgs);
        MensajeDto nuevo = new MensajeDto();
        nuevo.setIdchat(id);
        model.addAttribute("msgNuevo", nuevo);

        UsuarioDto usr1 = this.usuarioService.findByNombre(chat.getUsuario1());
        UsuarioDto usr2 = this.usuarioService.findByNombre(chat.getUsuario2());
        model.addAttribute("teleoperador",usr1);
        model.addAttribute("usuario2",usr2);

        return "Chat";
    }

    @PostMapping("/enviarMensaje")
    public String doEnviarMensaje(@ModelAttribute("msgNuevo") MensajeDto msgNuevo, HttpSession session){
        UsuarioDto usuario = (UsuarioDto) session.getAttribute("usuario");
        this.mensajeService.crearMensaje(msgNuevo, usuario);

        return "redirect:/chat/mostrar/" + msgNuevo.getIdchat();
    }

}
