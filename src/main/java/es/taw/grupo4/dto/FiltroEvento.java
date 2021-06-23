package es.taw.grupo4.dto;

public class FiltroEvento {
    private String nombre;
    private Integer min, max;
    private boolean disponible;
    private boolean musica;
    private boolean aireLibre;
    private boolean deporte;
    private boolean teatro;
    private boolean gaming;
    private boolean lectura;
    private boolean formacion;
    private boolean conferencia;
    private boolean benefico;
    private boolean arte;
    private boolean turismo;

    public boolean getFormacion() {
        return formacion;
    }

    public void setFormacion(boolean formacion) {
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

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean getMusica() {
        return musica;
    }

    public void setMusica(boolean musica) {
        this.musica = musica;
    }

    public boolean getAireLibre() {
        return aireLibre;
    }

    public void setAireLibre(boolean aireLibre) {
        this.aireLibre = aireLibre;
    }

    public boolean getDeporte() {
        return deporte;
    }

    public void setDeporte(boolean deporte) {
        this.deporte = deporte;
    }

    public boolean getTeatro() {
        return teatro;
    }

    public void setTeatro(boolean teatro) {
        this.teatro = teatro;
    }

    public boolean getGaming() {
        return gaming;
    }

    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    public boolean getLectura() {
        return lectura;
    }

    public void setLectura(boolean lectura) {
        this.lectura = lectura;
    }

    public boolean getConferencia() {
        return conferencia;
    }

    public void setConferencia(boolean conferencia) {
        this.conferencia = conferencia;
    }

    public boolean getBenefico() {
        return benefico;
    }

    public void setBenefico(boolean benefico) {
        this.benefico = benefico;
    }

    public boolean getArte() {
        return arte;
    }

    public void setArte(boolean arte) {
        this.arte = arte;
    }

    public boolean getTurismo() {
        return turismo;
    }

    public void setTurismo(boolean turismo) {
        this.turismo = turismo;
    }
}
