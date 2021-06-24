package es.taw.grupo4.dto;

import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.entity.UsuarioEvento;

public class EventoUsuarioDto {
    private Integer idEventoUsuario;
    private UsuarioEvento usuario;
    private Integer idEvento;
    private Integer fila;

    public UsuarioEvento getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEvento usuario) {
        this.usuario = usuario;
    }

    private Integer columna;
    private Evento evento;

    public Integer getIdEventoUsuario() {
        return idEventoUsuario;
    }

    public void setIdEventoUsuario(Integer idEventoUsuario) {
        this.idEventoUsuario = idEventoUsuario;
    }


    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
