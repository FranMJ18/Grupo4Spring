/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.grupo4.entity;

import es.taw.grupo4.dto.EventoUsuarioDto;
import es.taw.grupo4.dto.FiltroDto;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "EVENTO_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventoUsuario.findAll", query = "SELECT e FROM EventoUsuario e")
    , @NamedQuery(name = "EventoUsuario.findByIdeventousuario", query = "SELECT e FROM EventoUsuario e WHERE e.eventoUsuarioPK.ideventousuario = :ideventousuario")
    , @NamedQuery(name = "EventoUsuario.findByUsuario", query = "SELECT e FROM EventoUsuario e WHERE e.eventoUsuarioPK.usuario = :usuario")
    , @NamedQuery(name = "EventoUsuario.findByIdevento", query = "SELECT e FROM EventoUsuario e WHERE e.eventoUsuarioPK.idevento = :idevento")})
public class EventoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EventoUsuarioPK eventoUsuarioPK;
    @JoinColumns({
        @JoinColumn(name = "EVENTO", referencedColumnName = "EVENTO")
        , @JoinColumn(name = "FILA", referencedColumnName = "FILA")
        , @JoinColumn(name = "COLUMNA", referencedColumnName = "COLUMNA")})
    @ManyToOne
    private Asientos asientos;
    @JoinColumn(name = "IDEVENTO", referencedColumnName = "IDEVENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuarioEvento usuarioEvento;

    public EventoUsuario() {
    }

    public EventoUsuario(EventoUsuarioPK eventoUsuarioPK) {
        this.eventoUsuarioPK = eventoUsuarioPK;
    }

    public EventoUsuario(int ideventousuario, int usuario, int idevento) {
        this.eventoUsuarioPK = new EventoUsuarioPK(ideventousuario, usuario, idevento);
    }

    public EventoUsuarioPK getEventoUsuarioPK() {
        return eventoUsuarioPK;
    }

    public void setEventoUsuarioPK(EventoUsuarioPK eventoUsuarioPK) {
        this.eventoUsuarioPK = eventoUsuarioPK;
    }

    public Asientos getAsientos() {
        return asientos;
    }

    public void setAsientos(Asientos asientos) {
        this.asientos = asientos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public UsuarioEvento getUsuarioEvento() {
        return usuarioEvento;
    }

    public void setUsuarioEvento(UsuarioEvento usuarioEvento) {
        this.usuarioEvento = usuarioEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventoUsuarioPK != null ? eventoUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoUsuario)) {
            return false;
        }
        EventoUsuario other = (EventoUsuario) object;
        if ((this.eventoUsuarioPK == null && other.eventoUsuarioPK != null) || (this.eventoUsuarioPK != null && !this.eventoUsuarioPK.equals(other.eventoUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.EventoUsuario[ eventoUsuarioPK=" + eventoUsuarioPK + " ]";
    }

    @Transient
    public EventoUsuarioDto getDto(){
        EventoUsuarioDto eu = new EventoUsuarioDto();
        eu.setIdEventoUsuario(this.getEventoUsuarioPK().getIdeventousuario());
        eu.setUsuario(this.getUsuarioEvento().getUsuario()); // TODO NO SE SI ES USUARIO O USUARIO1
        eu.setIdEvento(this.getEventoUsuarioPK().getIdevento());
        if(this.getAsientos() != null){
            eu.setFila(this.getAsientos().getAsientosPK().getFila());
            eu.setColumna(this.getAsientos().getAsientosPK().getColumna());
        }
        eu.setIdEvento(this.getEvento().getIdevento());
        eu.setUsuario(this.usuarioEvento.getUsuario1());
        eu.setEvento(this.evento);
        return eu;
    }
    
}
