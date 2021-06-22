/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.grupo4.entity;

import es.taw.grupo4.dto.UsuarioDto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author carlo
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol")
    , @NamedQuery(name = "Usuario.findByNickname", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ROL", nullable = false)
    private int rol;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "NICKNAME", length = 50, nullable = false)
    private String nickname;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "PASSWORD", length = 50, nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Filtro> filtroList;
    @OneToMany(mappedBy = "usuario1")
    private List<Filtro> filtroList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario2")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private List<Chat> chatList1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private UsuarioEvento usuarioEvento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creadorevento")
    private List<Evento> eventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emisor")
    private List<Mensaje> mensajeList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, int rol, String nickname, String password) {
        this.idusuario = idusuario;
        this.rol = rol;
        this.nickname = nickname;
        this.password = password;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Filtro> getFiltroList() {
        return filtroList;
    }

    public void setFiltroList(List<Filtro> filtroList) {
        this.filtroList = filtroList;
    }

    @XmlTransient
    public List<Filtro> getFiltroList1() {
        return filtroList1;
    }

    public void setFiltroList1(List<Filtro> filtroList1) {
        this.filtroList1 = filtroList1;
    }

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @XmlTransient
    public List<Chat> getChatList1() {
        return chatList1;
    }

    public void setChatList1(List<Chat> chatList1) {
        this.chatList1 = chatList1;
    }

    public UsuarioEvento getUsuarioEvento() {
        return usuarioEvento;
    }

    public void setUsuarioEvento(UsuarioEvento usuarioEvento) {
        this.usuarioEvento = usuarioEvento;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupo4app.entity.Usuario[ idusuario=" + idusuario + " ]";
    }

    @Transient
    public UsuarioDto getDto(){
        UsuarioDto usuario = new UsuarioDto();

        usuario.setUsuario(this.nickname);
        usuario.setContraseña(this.password);
        usuario.setRol(this.rol);

        if(this.usuarioEvento != null) {
            UsuarioEvento usuarioEvento = this.usuarioEvento;

            usuario.setApellidos(usuarioEvento.getApellido());
            usuario.setCiudad(usuarioEvento.getCiudad());
            usuario.setDomicilio(usuarioEvento.getDomicilio());
            usuario.setEdad(usuarioEvento.getEdad());
            usuario.setNombre(usuarioEvento.getNombre());
            usuario.setSexo(usuarioEvento.getSexo());
        }

        return usuario;
    }
}
