package es.taw.grupo4.service;

import es.taw.grupo4.dao.EventoUsuarioRepository;
import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.dao.UsuarioRepository;
import es.taw.grupo4.dto.FiltroDto;
import es.taw.grupo4.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FiltroService {

    private FiltroRepository firep;
    private UsuarioRepository usurep;
    private EventoUsuarioRepository eurep;

    @Autowired
    public void setEurep(EventoUsuarioRepository eurep) {
        this.eurep = eurep;
    }

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

    public List<EventoUsuario> filtrarPorFiltro(FiltroDto fdto) throws ParseException {
        //List<EventoUsuario> resultados = new ArrayList<>();
        List<EventoUsuario> usuarios = new ArrayList<>();

        int anyo = fdto.getAnyo(); //Evento
        if(anyo == 0){
            anyo = 0000;
        }
        String anyoSup = anyo + "-12-31";
        String anyoInf = anyo + "-01-01";

        Date inf = new SimpleDateFormat("yyyy-MM-dd").parse(anyoInf);
        Date sup = new SimpleDateFormat("yyyy-MM-dd").parse(anyoSup);

        if(fdto.getEdad_lim_sup() > 0 || !fdto.getSexo().isEmpty() || !fdto.getCiudad().isEmpty() || fdto.getUsuario() != null || fdto.getAnyo() > 0 || fdto.getCoste_entrada() > 0 || !fdto.getCategoria().isEmpty()) {
            usuarios = this.eurep.findAll();
            if (fdto.getEdad_lim_inf() != 0) {
                usuarios.retainAll(this.eurep.findByMinEdad(fdto.getEdad_lim_inf()));
            } else if (fdto.getEdad_lim_sup() > 0) {
                usuarios.retainAll(this.eurep.findByMaxEdad(fdto.getEdad_lim_sup()));
            } else if (!fdto.getSexo().isEmpty()) {
                usuarios.retainAll(this.eurep.findBySexo(fdto.getSexo()));
            } else if (!fdto.getCiudad().isEmpty()) {
                usuarios.retainAll(this.eurep.findByCiudad(fdto.getCiudad()));
            } else if (fdto.getCoste_entrada() > 0) {
                usuarios.retainAll(this.eurep.findByCosteEntrada(fdto.getCoste_entrada()));
            } else if (!fdto.getCategoria().isEmpty()) {
                String cat = fdto.getCategoria();
                switch (cat) {
                    case ("musica"):
                        usuarios.retainAll(this.eurep.findByMusica());
                        break;
                    case ("aireLibre"):
                        usuarios.retainAll(this.eurep.findByAireLibre());
                        break;
                    case ("deporte"):
                        usuarios.retainAll(this.eurep.findByDeporte());
                        break;
                    case ("teatro"):
                        usuarios.retainAll(this.eurep.findByTeatro());
                        break;
                    case ("gaming"):
                        usuarios.retainAll(this.eurep.findByGaming());
                        break;
                    case ("lectura"):
                        usuarios.retainAll(this.eurep.findByLectura());
                        break;
                    case ("formacion"):
                        usuarios.retainAll(this.eurep.findByFormacion());
                        break;
                    case ("conferencia"):
                        usuarios.retainAll(this.eurep.findByConferencia());
                        break;
                    case ("beneficio"):
                        usuarios.retainAll(this.eurep.findByBenefico());
                        break;
                    case ("arte"):
                        usuarios.retainAll(this.eurep.findByArte());
                        break;
                    case ("turismo"):
                        usuarios.retainAll(this.eurep.findByTurismo());
                        break;
                }
            } else if (anyo > 0) {
                usuarios.retainAll(this.eurep.findByAnyo(inf, sup));
            }
        }
        return usuarios;
    }
}
