package es.taw.grupo4.service;

import es.taw.grupo4.dao.ChatRepository;
import es.taw.grupo4.dao.MensajeRepository;
import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.dto.MensajeDto;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Mensaje;
import es.taw.grupo4.entity.MensajePK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MensajeService {
    private MensajeRepository mensajeRepository;
    private UsuarioRepository usuarioRepository;
    private ChatRepository chatRepository;

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<MensajeDto> listarMensajesPorChat(Integer idChat){
        List<Mensaje> lista = this.mensajeRepository.findByChat(idChat);
        return convertirLista(lista);
    }

    private List<MensajeDto> convertirLista(List<Mensaje> lista){
        List<MensajeDto> mensajeDtoList = new ArrayList<>();

        for(Mensaje msg : lista){
            mensajeDtoList.add(msg.getDto());
        }

        return mensajeDtoList;
    }

    public void crearMensaje(MensajeDto msgNuevo, UsuarioDto usuario) {
        this.mensajeRepository.crearMensaje(msgNuevo.getIdchat(), msgNuevo.getTexto(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), usuario.getId());
    }
}
