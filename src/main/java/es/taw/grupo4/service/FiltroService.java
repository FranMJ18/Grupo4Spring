package es.taw.grupo4.service;

import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.FiltroDto;
import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.FiltroPK;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class FiltroService {

    private FiltroRepository firep;
    private UsuarioRepository usurep;

    @Autowired
    public void setUsurep(UsuarioRepository usurep) {
        this.usurep = usurep;
    }

    @Autowired
    public void setFirep(FiltroRepository firep) {
        this.firep = firep;
    }

    public Filtro econtrarById(Integer id){
        return this.firep.findById(id).get();
    }

    public List<Filtro> encontarByAnalista(Integer id){
        return firep.filtrosCreador(id);
    }

    public void guardarOEditar(FiltroDto fdto, Integer analista){
        Filtro filtro;
        if(fdto.getIdfiltro() != 0){
            filtro = this.firep.getById(fdto.getIdfiltro());
        }else {
            FiltroPK fk = new FiltroPK();
            fk.setAnalistaeventos(analista);
            filtro = new Filtro(fk);
        }
        Integer anyo = 0;
        Integer costeEntrada = 0;
        Integer usuario = 1;
        Integer edadInf = 0;
        Integer edadSup = 0;
        String nombre;

        if(fdto.getAnyo() != null){
            anyo = fdto.getAnyo();
        }

        if(fdto.getCoste_entrada() != null){
            costeEntrada = fdto.getCoste_entrada();
        }

        if(fdto.getUsuario() != null){
            usuario = fdto.getUsuario();
        }

        if(fdto.getEdad_lim_inf() != null){
            edadInf = fdto.getEdad_lim_inf();
        }

        if(fdto.getEdad_lim_sup() != null){
            edadSup = fdto.getEdad_lim_sup();
        }

        if(fdto.getNombre() == null){
            nombre = "Nuevo Filtro";
        }else{
            nombre = fdto.getNombre();
        }

        filtro.setCiudad(fdto.getCiudad());
        filtro.setSexo(fdto.getSexo());
        filtro.setAnyo(anyo);
        filtro.setNombre(nombre);
        filtro.setCosteEntrada(costeEntrada);
        Usuario usuarioFiltrado = this.usurep.getById(usuario);
        filtro.setCategoria(fdto.getCategoria());
        filtro.setUsuario1(usuarioFiltrado);
        filtro.setUsuario(this.usurep.getById(analista));
        filtro.setEdadLimInf(fdto.getEdad_lim_inf());
        filtro.setEdadLimSup(fdto.getEdad_lim_sup());

        this.firep.save(filtro);
    }
}
