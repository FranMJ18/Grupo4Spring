package es.taw.grupo4.service;

import es.taw.grupo4.dao.ChatRepository;
import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.dto.FiltroBusquedaChat;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<ChatDto> listarOtrasConversaciones(UsuarioDto us, FiltroBusquedaChat filtro) {
        List<Chat> chats;
        if(filtro.getUsuario() == null){
            chats = this.chatRepository.findOtrosChatsTeleoperador(us.getId());
        } else {
            chats = this.chatRepository.findOtrosChatsTeleoperadorFiltro(us.getId(), filtro.getUsuario().toUpperCase());
        }

        return convertirLista(chats);
    }

    public List<ChatDto> listarMisConversaciones(UsuarioDto us, FiltroBusquedaChat filtro){
        List<Chat> chats;
        if(us.getRol() == 2){
            if(filtro.getUsuario() == null){
                chats = this.chatRepository.findMisChatsTeleoperador(us.getId());
            } else {
                chats = this.chatRepository.findMisChatsTeleoperadorFiltro(us.getId(), filtro.getUsuario().toUpperCase());
            }
        } else {
            if(filtro.getUsuario() == null){
                chats = this.chatRepository.findMisChatsUsuario(us.getId());
            } else {
                chats = this.chatRepository.findMisChatsUsuarioFiltro(us.getId(), filtro.getUsuario().toUpperCase());
            }
        }

        return convertirLista(chats);
    }

    public void borrarChat(Integer id){
        Optional<Chat> c = this.chatRepository.findById(id);
        if(c.isPresent()){
            this.chatRepository.delete(c.get());
        }
    }

    public ChatDto crearChat(UsuarioDto teleOp, UsuarioDto usuario) {
        Chat chat = new Chat();

        Usuario usr1 = this.usuarioRepository.findById(teleOp.getId()).get();
        Usuario usr2 = this.usuarioRepository.findById(usuario.getId()).get();

        chat.setUsuario1(usr1);
        chat.setUsuario2(usr2);

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


    public boolean existeChat(UsuarioDto usuario1, UsuarioDto usuario2) {
        List<Chat> chatsExitentes = this.chatRepository.findByTeleOpAndUser(usuario1.getId(),usuario2.getId());

        if(chatsExitentes.isEmpty()){
            return false;
        } else {
            return true;
        }

    }
}
