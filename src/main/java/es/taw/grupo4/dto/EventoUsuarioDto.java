package es.taw.grupo4.dto;

import es.taw.grupo4.entity.Evento;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.entity.UsuarioEvento;

public class EventoUsuarioDto {
    private Integer idEventoUsuario;
    private Integer idUsuarioEvento;
    private Integer idEvento;
    private Integer fila;
    private Usuario usuario;
    private Evento evento;

    public void setIdUsuarioEvento(Integer idUsuarioEvento) {
        this.idUsuarioEvento = idUsuarioEvento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getIdUsuarioEvento() {
        return idUsuarioEvento;
    }

    public void setUsuario(Integer usuario) {
        this.idUsuarioEvento = usuario;
    }

    private Integer columna;

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

}
