package es.taw.grupo4.controller;

import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.dto.MensajeDto;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.ChatService;
import es.taw.grupo4.service.MensajeService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
        UsuarioDto user = (UsuarioDto) session.getAttribute("usuario");
        if(user.getRol() == 2){
            List<ChatDto> misChats = this.chatService.listarMisConversacionesTeleoperador(user.getId());
            model.addAttribute("chats",misChats);
            List<ChatDto> otrosChats = this.chatService.listarOtrasConversaciones(user.getId());
            model.addAttribute("otrosChats",otrosChats);
        } else {
            List<ChatDto> misChats = this.chatService.listarMisConversacionesUsuario(user.getId());
            model.addAttribute("chats",misChats);
        }

        return "Conversaciones";
    }

    @GetMapping("/borrar/{id}")
    public String doBorrarConversacion(@PathVariable("id") Integer idchat){
        this.chatService.borrarChat(idchat);

        return "redirect:chat/";
    }

    @GetMapping("/crear/")
    public String doCrearChatAleatorio(Model model, HttpSession session){
        UsuarioDto usr = (UsuarioDto) session.getAttribute("usuario");
        Usuario teleOpRandom = this.usuarioService.findRandomTeleoperador(usr.getId());
        if(teleOpRandom != null){
            Usuario usuarioIniciado = this.usuarioService.findById(usr.getId());
            ChatDto chat = this.chatService.crearChat(teleOpRandom, usuarioIniciado);

            return "redirect:chat/mostrar/" + chat.getIdchat();

        } else {
            model.addAttribute("error","Error: No hay teleoperadores adicionales disponibles");

            return doListarConversaciones(model, session);
        }
    }

    @GetMapping("/mostrar/{id}")
    public String doMostrarChat(@PathVariable("id") Integer id, Model model){
        List<MensajeDto> msgs = this.mensajeService.listarMensajesPorChat(id);
        ChatDto chat = this.chatService.findById(id);
        model.addAttribute("chat",chat);
        model.addAttribute("mensajes",msgs);

        return "Chat";
    }
}
