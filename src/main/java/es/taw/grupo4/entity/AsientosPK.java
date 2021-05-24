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

/**
 *
 * @author carlo
 */
@Embeddable
public class AsientosPK implements Serializable {

    @Basic(optional = false)
    //@NotNull
    @Column(name = "FILA", nullable = false)
    private int fila;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "COLUMNA", nullable = false)
    private int columna;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "EVENTO", nullable = false)
    private int evento;

    public AsientosPK() {
    }

    public AsientosPK(int fila, int columna, int evento) {
        this.fila = fila;
        this.columna = columna;
        this.evento = evento;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fila;
        hash += (int) columna;
        hash += (int) evento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsientosPK)) {
            return false;
        }
        AsientosPK other = (AsientosPK) object;
        if (this.fila != other.fila) {
            return false;
        }
        if (this.columna != other.columna) {
            return false;
        }
        if (this.evento != other.evento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.AsientosPK[ fila=" + fila + ", columna=" + columna + ", evento=" + evento + " ]";
    }
    
}
