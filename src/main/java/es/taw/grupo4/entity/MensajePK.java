/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.grupo4.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 *
 * @author carlo
 */
@Embeddable
public class MensajePK implements Serializable {

    @Basic(optional = false)
    //@NotNull
    @Column(name = "CHAT", nullable = false)
    private int chat;
    @Basic(optional = false)
    @Column(name = "IDMENSAJE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmensaje;

    public MensajePK() {
    }

    public MensajePK(int chat, int idmensaje) {
        this.chat = chat;
        this.idmensaje = idmensaje;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) chat;
        hash += (int) idmensaje;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePK)) {
            return false;
        }
        MensajePK other = (MensajePK) object;
        if (this.chat != other.chat) {
            return false;
        }
        if (this.idmensaje != other.idmensaje) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.MensajePK[ chat=" + chat + ", idmensaje=" + idmensaje + " ]";
    }
    
}
