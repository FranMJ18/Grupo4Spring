package es.taw.grupo4.service;

import es.taw.grupo4.dao.ChatRepository;
import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private ChatRepository chatRepository;

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ChatDto> listarMisConversacionesTeleoperador(Integer id){
        List<Chat> chats = this.chatRepository.findMisChatsTeleoperador(id);

        return convertirLista(chats);
    }

    public List<ChatDto> listarOtrasConversaciones(Integer id) {
        List<Chat> chats = this.chatRepository.findOtrosChatsTeleoperador(id);

        return convertirLista(chats);
    }

    public List<ChatDto> listarMisConversacionesUsuario(Integer id) {
        List<Chat> chats = this.chatRepository.findMisChatsUsuario(id);

        return convertirLista(chats);
    }

    private List<ChatDto> convertirLista(List<Chat> lista){
        List<ChatDto> chatDtoList = new ArrayList<>();

        for(Chat ch : lista){
            chatDtoList.add(ch.getDto());
        }

        return chatDtoList;
    }

}
