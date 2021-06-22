/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.grupo4.entity;

import es.taw.grupo4.dto.MensajeDto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m")
    , @NamedQuery(name = "Mensaje.findByChat", query = "SELECT m FROM Mensaje m WHERE m.mensajePK.chat = :chat")
    , @NamedQuery(name = "Mensaje.findByIdmensaje", query = "SELECT m FROM Mensaje m WHERE m.mensajePK.idmensaje = :idmensaje")
    , @NamedQuery(name = "Mensaje.findByTexto", query = "SELECT m FROM Mensaje m WHERE m.texto = :texto")
    , @NamedQuery(name = "Mensaje.findByFechaHora", query = "SELECT m FROM Mensaje m WHERE m.fechaHora = :fechaHora")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensajePK mensajePK;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "TEXTO", length = 200, nullable = false)
    private String texto;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_HORA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "CHAT", referencedColumnName = "IDCHAT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Chat chat1;
    @JoinColumn(name = "EMISOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario emisor;

    public Mensaje() {
    }

    public Mensaje(MensajePK mensajePK) {
        this.mensajePK = mensajePK;
    }

    public Mensaje(MensajePK mensajePK, String texto, Date fechaHora) {
        this.mensajePK = mensajePK;
        this.texto = texto;
        this.fechaHora = fechaHora;
    }

    public Mensaje(int chat, int idmensaje) {
        this.mensajePK = new MensajePK(chat, idmensaje);
    }

    public MensajePK getMensajePK() {
        return mensajePK;
    }

    public void setMensajePK(MensajePK mensajePK) {
        this.mensajePK = mensajePK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Chat getChat1() {
        return chat1;
    }

    public void setChat1(Chat chat1) {
        this.chat1 = chat1;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensajePK != null ? mensajePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.mensajePK == null && other.mensajePK != null) || (this.mensajePK != null && !this.mensajePK.equals(other.mensajePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.Mensaje[ mensajePK=" + mensajePK + " ]";
    }

    public MensajeDto getDto(){
        MensajeDto dto = new MensajeDto();

        dto.setIdmensaje(this.mensajePK.getIdmensaje());
        dto.setTexto(this.texto);
        dto.setFechaHora(this.fechaHora);
        dto.setEmisor(this.emisor.getDto());

        return dto;
    }
}
