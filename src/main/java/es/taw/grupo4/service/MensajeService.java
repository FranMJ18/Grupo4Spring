package es.taw.grupo4.service;

import es.taw.grupo4.dao.MensajeRepository;
import es.taw.grupo4.dto.ChatDto;
import es.taw.grupo4.dto.MensajeDto;
import es.taw.grupo4.entity.Chat;
import es.taw.grupo4.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {
    private MensajeRepository mensajeRepository;

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
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
}
