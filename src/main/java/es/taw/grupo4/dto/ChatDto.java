package es.taw.grupo4.dto;

public class ChatDto {
    private Integer idchat;
    private UsuarioDto usuario1;
    private UsuarioDto usuario2;

    public Integer getIdchat() {
        return idchat;
    }

    public void setIdchat(Integer idchat) {
        this.idchat = idchat;
    }

    public UsuarioDto getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioDto usuario1) {
        this.usuario1 = usuario1;
    }

    public UsuarioDto getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(UsuarioDto usuario2) {
        this.usuario2 = usuario2;
    }
}
