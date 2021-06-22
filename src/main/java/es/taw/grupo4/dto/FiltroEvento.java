package es.taw.grupo4.dto;

public class FiltroEvento {
    private String nombre;
    private Integer min, max;
    private Boolean disponible;
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

    public Boolean getFormacion() {
        return formacion;
    }

    public void setFormacion(Boolean formacion) {
        this.formacion = formacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
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
}
