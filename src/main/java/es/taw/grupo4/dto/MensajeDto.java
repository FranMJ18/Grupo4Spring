package es.taw.grupo4.dto;

import java.util.Date;

public class MensajeDto {
    private int idmensaje;
    private int idchat;
    private String texto;
    private Date fechaHora;
    private UsuarioDto emisor;

    public int getIdmensaje() {
        return idmensaje;
    }

    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
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

    public UsuarioDto getEmisor() {
        return emisor;
    }

    public void setEmisor(UsuarioDto emisor) {
        this.emisor= emisor;
    }

    public int getIdchat() {
        return idchat;
    }

    public void setIdchat(int idchat) {
        this.idchat = idchat;
    }
}
