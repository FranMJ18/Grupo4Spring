/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.grupo4.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "ASIENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asientos.findAll", query = "SELECT a FROM Asientos a")
    , @NamedQuery(name = "Asientos.findByFila", query = "SELECT a FROM Asientos a WHERE a.asientosPK.fila = :fila")
    , @NamedQuery(name = "Asientos.findByColumna", query = "SELECT a FROM Asientos a WHERE a.asientosPK.columna = :columna")
    , @NamedQuery(name = "Asientos.findByEvento", query = "SELECT a FROM Asientos a WHERE a.asientosPK.evento = :evento")
    , @NamedQuery(name = "Asientos.findByOcupado", query = "SELECT a FROM Asientos a WHERE a.ocupado = :ocupado")})
public class Asientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsientosPK asientosPK;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "OCUPADO", nullable = false)
    private int ocupado;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "asientos")
    @OneToMany(mappedBy = "asientos")
    private List<EventoUsuario> eventoUsuarioList;
    @JoinColumn(name = "EVENTO", referencedColumnName = "IDEVENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento1;

    public Asientos() {
    }

    public Asientos(AsientosPK asientosPK) {
        this.asientosPK = asientosPK;
    }

    public Asientos(AsientosPK asientosPK, int ocupado) {
        this.asientosPK = asientosPK;
        this.ocupado = ocupado;
    }

    public Asientos(int fila, int columna, int evento) {
        this.asientosPK = new AsientosPK(fila, columna, evento);
    }

    public AsientosPK getAsientosPK() {
        return asientosPK;
    }

    public void setAsientosPK(AsientosPK asientosPK) {
        this.asientosPK = asientosPK;
    }

    public int getOcupado() {
        return ocupado;
    }

    public void setOcupado(int ocupado) {
        this.ocupado = ocupado;
    }

    @XmlTransient
    public List<EventoUsuario> getEventoUsuarioList() {
        return eventoUsuarioList;
    }

    public void setEventoUsuarioList(List<EventoUsuario> eventoUsuarioList) {
        this.eventoUsuarioList = eventoUsuarioList;
    }

    public Evento getEvento1() {
        return evento1;
    }

    public void setEvento1(Evento evento1) {
        this.evento1 = evento1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asientosPK != null ? asientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asientos)) {
            return false;
        }
        Asientos other = (Asientos) object;
        if ((this.asientosPK == null && other.asientosPK != null) || (this.asientosPK != null && !this.asientosPK.equals(other.asientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.Asientos[ asientosPK=" + asientosPK + " ]";
    }
    
}
