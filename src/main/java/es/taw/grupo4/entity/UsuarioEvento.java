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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "USUARIO_EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEvento.findAll", query = "SELECT u FROM UsuarioEvento u")
    , @NamedQuery(name = "UsuarioEvento.findByUsuario", query = "SELECT u FROM UsuarioEvento u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "UsuarioEvento.findByApellido", query = "SELECT u FROM UsuarioEvento u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "UsuarioEvento.findByDomicilio", query = "SELECT u FROM UsuarioEvento u WHERE u.domicilio = :domicilio")
    , @NamedQuery(name = "UsuarioEvento.findByNombre", query = "SELECT u FROM UsuarioEvento u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UsuarioEvento.findByCiudad", query = "SELECT u FROM UsuarioEvento u WHERE u.ciudad = :ciudad")
    , @NamedQuery(name = "UsuarioEvento.findByEdad", query = "SELECT u FROM UsuarioEvento u WHERE u.edad = :edad")
    , @NamedQuery(name = "UsuarioEvento.findBySexo", query = "SELECT u FROM UsuarioEvento u WHERE u.sexo = :sexo")})
public class UsuarioEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "USUARIO", nullable = false)
    private Integer usuario;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "APELLIDO", length = 50, nullable = false)
    private String apellido;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "DOMICILIO", length = 50, nullable = false)
    private String domicilio;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "CIUDAD", length = 50, nullable = false)
    private String ciudad;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "EDAD", nullable = false)
    private int edad;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "SEXO", length = 50, nullable = false)
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioEvento")
    private List<EventoUsuario> eventoUsuarioList;
    @JoinColumn(name = "USUARIO", referencedColumnName = "IDUSUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario1;

    public UsuarioEvento() {
    }

    public UsuarioEvento(Integer usuario) {
        this.usuario = usuario;
    }

    public UsuarioEvento(Integer usuario, String apellido, String domicilio, String nombre, String ciudad, int edad, String sexo) {
        this.usuario = usuario;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
        this.sexo = sexo;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public List<EventoUsuario> getEventoUsuarioList() {
        return eventoUsuarioList;
    }

    public void setEventoUsuarioList(List<EventoUsuario> eventoUsuarioList) {
        this.eventoUsuarioList = eventoUsuarioList;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEvento)) {
            return false;
        }
        UsuarioEvento other = (UsuarioEvento) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.UsuarioEvento[ usuario=" + usuario + " ]";
    }
    
}
