package es.taw.grupo4.controller;

import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("chat")
public class ChatController {

    private ChatService chatService;

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String doListarConversaciones(Model model, HttpSession session){
        Usuario user = (Usuario) session.getAttribute("usuario");
        if(user.getRol() == 2){
            List<ChatDto> misChats = this.chatService.listarMisConversaciones();
            model.addAttribute("chats");
            List<ChatDto> otrosChats = this.chatService.listarOtrasConversaciones();
            model.addAttribute("otrosChats")
        } else {

        }

        return "Conversaciones";
    }
}
