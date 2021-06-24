package es.taw.grupo4.dto;

public class FiltroDto {
    private Integer idfiltro;
    //private Integer analistaeventos;
    private Integer usuario;
    private String nombre;
    private Integer edad_lim_inf;
    private Integer edad_lim_sup;
    private String sexo;
    private String ciudad;

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getIdfiltro() {
        return idfiltro;
    }

    public void setIdfiltro(Integer idfiltro) {
        this.idfiltro = idfiltro;
    }

    private Integer anyo;
    private Integer coste_entrada;
    private String categoria;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad_lim_inf() {
        return edad_lim_inf;
    }

    public void setEdad_lim_inf(Integer edad_lim_inf) {
        this.edad_lim_inf = edad_lim_inf;
    }

    public Integer getEdad_lim_sup() {
        return edad_lim_sup;
    }

    public void setEdad_lim_sup(Integer edad_lim_sup) {
        this.edad_lim_sup = edad_lim_sup;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getAnyo() {
        return anyo;
    }

    public void setAnyo(Integer anyo) {
        this.anyo = anyo;
    }

    public Integer getCoste_entrada() {
        return coste_entrada;
    }

    public void setCoste_entrada(Integer coste_entrada) {
        this.coste_entrada = coste_entrada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
