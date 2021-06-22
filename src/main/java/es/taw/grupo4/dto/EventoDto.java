package es.taw.grupo4.dto;

import java.util.Date;

public class EventoDto {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer precio;

    public Boolean getAsientosFijos() {
        return asientosFijos;
    }

    public void setAsientosFijos(Boolean asientosFijos) {
        this.asientosFijos = asientosFijos;
    }

    private Boolean asientosFijos;

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCosteEntrada() {
        return costeEntrada;
    }

    public void setCosteEntrada(Integer costeEntrada) {
        this.costeEntrada = costeEntrada;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Integer getMaxEntradas() {
        return maxEntradas;
    }

    public void setMaxEntradas(Integer maxEntradas) {
        this.maxEntradas = maxEntradas;
    }

    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    public Integer getColumnas() {
        return columnas;
    }

    public void setColumnas(Integer columnas) {
        this.columnas = columnas;
    }

    public Boolean getMusica() {
        return musica;
    }

    public void setMusica(Boolean musica) {
        this.musica = musica;
    }

    public Boolean getAireLibre() {
        return aireLibre;
    }

    public void setAireLibre(Boolean aireLibre) {
        this.aireLibre = aireLibre;
    }

    public Boolean getDeporte() {
        return deporte;
    }

    public void setDeporte(Boolean deporte) {
        this.deporte = deporte;
    }

    public Boolean getTeatro() {
        return teatro;
    }

    public void setTeatro(Boolean teatro) {
        this.teatro = teatro;
    }

    public Boolean getGaming() {
        return gaming;
    }

    public void setGaming(Boolean gaming) {
        this.gaming = gaming;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }

    public Boolean getFormacion() {
        return formacion;
    }

    public void setFormacion(Boolean formacion) {
        this.formacion = formacion;
    }

    public Boolean getConferencia() {
        return conferencia;
    }

    public void setConferencia(Boolean conferencia) {
        this.conferencia = conferencia;
    }

    public Boolean getBenefico() {
        return benefico;
    }

    public void setBenefico(Boolean benefico) {
        this.benefico = benefico;
    }

    public Boolean getArte() {
        return arte;
    }

    public void setArte(Boolean arte) {
        this.arte = arte;
    }

    public Boolean getTurismo() {
        return turismo;
    }

    public void setTurismo(Boolean turismo) {
        this.turismo = turismo;
    }

    private Integer costeEntrada;
    private Integer aforo;
    private Integer maxEntradas;
    private Integer filas;
    private Integer columnas;
    private Boolean musica;
    private Boolean aireLibre;
    private Boolean deporte;
    private Boolean teatro;
    private Boolean gaming;
    private Boolean lectura;
    private Boolean formacion;
    private Boolean conferencia;
    private Boolean benefico;
    private Boolean arte;
    private Boolean turismo;
}
