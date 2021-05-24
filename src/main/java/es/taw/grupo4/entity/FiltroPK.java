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
public class FiltroPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFILTRO")
    private int idfiltro;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ANALISTAEVENTOS", nullable = false)
    private int analistaeventos;

    public FiltroPK() {
    }

    public FiltroPK(int idfiltro, int analistaeventos) {
        this.idfiltro = idfiltro;
        this.analistaeventos = analistaeventos;
    }

    public int getIdfiltro() {
        return idfiltro;
    }

    public void setIdfiltro(int idfiltro) {
        this.idfiltro = idfiltro;
    }

    public int getAnalistaeventos() {
        return analistaeventos;
    }

    public void setAnalistaeventos(int analistaeventos) {
        this.analistaeventos = analistaeventos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idfiltro;
        hash += (int) analistaeventos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiltroPK)) {
            return false;
        }
        FiltroPK other = (FiltroPK) object;
        if (this.idfiltro != other.idfiltro) {
            return false;
        }
        if (this.analistaeventos != other.analistaeventos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.FiltroPK[ idfiltro=" + idfiltro + ", analistaeventos=" + analistaeventos + " ]";
    }
    
}
