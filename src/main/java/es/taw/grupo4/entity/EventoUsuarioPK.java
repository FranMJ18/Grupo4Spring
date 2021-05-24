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
public class EventoUsuarioPK implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEVENTOUSUARIO")
    private int ideventousuario;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "USUARIO", nullable = false)
    private int usuario;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "IDEVENTO", nullable = false)
    private int idevento;

    public EventoUsuarioPK() {
    }

    public EventoUsuarioPK(int ideventousuario, int usuario, int idevento) {
        this.ideventousuario = ideventousuario;
        this.usuario = usuario;
        this.idevento = idevento;
    }

    public int getIdeventousuario() {
        return ideventousuario;
    }

    public void setIdeventousuario(int ideventousuario) {
        this.ideventousuario = ideventousuario;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ideventousuario;
        hash += (int) usuario;
        hash += (int) idevento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventoUsuarioPK)) {
            return false;
        }
        EventoUsuarioPK other = (EventoUsuarioPK) object;
        if (this.ideventousuario != other.ideventousuario) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.idevento != other.idevento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.EventoUsuarioPK[ ideventousuario=" + ideventousuario + ", usuario=" + usuario + ", idevento=" + idevento + " ]";
    }
    
}
