package es.taw.grupo4.service;

import es.taw.grupo4.dao.ChatRepository;
import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void borrarChat(Integer id){
        Optional<Chat> c = this.chatRepository.findById(id);
        if(c.isPresent()){
            this.chatRepository.delete(c.get());
        }
    }

    public ChatDto crearChat(Usuario teleOpRandom, Usuario usuarioIniciado) {
        Chat chat = new Chat();

        chat.setUsuario1(teleOpRandom);
        chat.setUsuario2(usuarioIniciado);

        this.chatRepository.save(chat);

        return chat.getDto();
    }

    public ChatDto findById(Integer idChat){
        Optional<Chat> c = this.chatRepository.findById(idChat);
        if(c.isPresent()){
            return c.get().getDto();
        } else {
            return null;
        }
    }

    private List<ChatDto> convertirLista(List<Chat> lista){
        List<ChatDto> chatDtoList = new ArrayList<>();

        for(Chat ch : lista){
            chatDtoList.add(ch.getDto());
        }

        return chatDtoList;
    }


}
