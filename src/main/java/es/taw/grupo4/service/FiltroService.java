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
            filtro = this.firep.findByPK(analista, fdto.getIdfiltro()).get(0);
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

        if(fdto.getNombre().isEmpty()){
            nombre = "Nuevo Filtro";
        }else{
            nombre = fdto.getNombre();
        }

        filtro.setCiudad(fdto.getCiudad());
        filtro.setSexo(fdto.getSexo());
        filtro.setAnyo(anyo);
        filtro.setNombre(nombre);
        filtro.setCosteEntrada(costeEntrada);
        //Usuario usuarioFiltrado = this.usurep.getById(usuario);
        filtro.setCategoria(fdto.getCategoria());
        //filtro.setUsuario1(usuarioFiltrado);
        filtro.setUsuario(this.usurep.getById(analista));
        filtro.setEdadLimInf(edadInf);
        filtro.setEdadLimSup(edadSup);

        if(fdto.getIdfiltro() != 0){
            this.firep.save(filtro);
        }else{
            this.firep.crearFiltro(analista, filtro.getNombre(), filtro.getEdadLimInf(), filtro.getEdadLimSup(), filtro.getSexo(), filtro.getCiudad(), filtro.getAnyo(), filtro.getCosteEntrada(), filtro.getCategoria(), 1);
        }

    }

    public List<EventoUsuario> filtrarPorFiltro(FiltroDto fdto) throws ParseException {
        List<EventoUsuario> usuarios = new ArrayList<>();
        List<EventoUsuario> auxiliar = new ArrayList<>();

        int anyo = fdto.getAnyo(); //Evento
        if (anyo == 0) {
            anyo = 0000;
        }
        String anyoSup = anyo + "-12-31";
        String anyoInf = anyo + "-01-01";

        Date inf = new SimpleDateFormat("yyyy-MM-dd").parse(anyoInf);
        Date sup = new SimpleDateFormat("yyyy-MM-dd").parse(anyoSup);

        Integer ex1 = fdto.getEdad_lim_inf();
        Integer ex2 = fdto.getEdad_lim_sup();
        String ex3 = fdto.getSexo();
        String ex4 = fdto.getCiudad();
        Integer ex5 = fdto.getAnyo();
        Integer ex6 = fdto.getCoste_entrada();
        String ex7 = fdto.getCategoria();

        auxiliar = this.eurep.findAll();
        Integer max = auxiliar.size();

        if (fdto.getEdad_lim_inf() > 0 || fdto.getEdad_lim_sup() > 0 || !fdto.getSexo().isEmpty() || !fdto.getCiudad().isEmpty() || fdto.getAnyo() > 0 || fdto.getCoste_entrada() > 0 || !fdto.getCategoria().isEmpty()) {
            usuarios = this.eurep.findAll();

            if (fdto.getEdad_lim_inf() != 0) {
                usuarios.retainAll(this.eurep.findByMinEdad(fdto.getEdad_lim_inf()));
                if (usuarios != null && usuarios.size() != 0) {
                    auxiliar.retainAll(usuarios);

                }
            }
                if (fdto.getEdad_lim_sup() != 0) {
                    usuarios = this.eurep.findAll();
                    usuarios.retainAll(this.eurep.findByMaxEdad(fdto.getEdad_lim_sup()));
                    if (usuarios != null && usuarios.size() != 0) {
                        auxiliar.retainAll(usuarios);
                    }
                }
                if (!fdto.getSexo().isEmpty()) {
                    usuarios = this.eurep.findAll();
                    usuarios.retainAll(this.eurep.findBySexo(fdto.getSexo()));
                    if (usuarios != null && usuarios.size() != 0) {
                        auxiliar.retainAll(usuarios);
                    }
                }
                if (!fdto.getCiudad().isEmpty()) {
                    usuarios = this.eurep.findAll();
                    usuarios.retainAll(this.eurep.findByCiudad(fdto.getCiudad()));
                    if (usuarios != null && usuarios.size() != 0) {
                        auxiliar.retainAll(usuarios);
                    }
                }
                if (fdto.getCoste_entrada() > 0) {
                    usuarios = this.eurep.findAll();
                    usuarios.retainAll(this.eurep.findByCosteEntrada(fdto.getCoste_entrada()));
                    if (usuarios != null && usuarios.size() != 0) {
                        auxiliar.retainAll(usuarios);
                    }
                }
                if (!fdto.getCategoria().isEmpty()) {
                    usuarios = this.eurep.findAll();
                    String cat = fdto.getCategoria();
                    switch (cat) {
                        case ("musica"):
                            usuarios.retainAll(this.eurep.findByMusica());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("aireLibre"):
                            usuarios.retainAll(this.eurep.findByAireLibre());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("deporte"):
                            usuarios.retainAll(this.eurep.findByDeporte());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("teatro"):
                            usuarios.retainAll(this.eurep.findByTeatro());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("gaming"):
                            usuarios.retainAll(this.eurep.findByGaming());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("lectura"):
                            usuarios.retainAll(this.eurep.findByLectura());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("formacion"):
                            usuarios.retainAll(this.eurep.findByFormacion());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("conferencia"):
                            usuarios.retainAll(this.eurep.findByConferencia());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("benefico"):
                            usuarios.retainAll(this.eurep.findByBenefico());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("arte"):
                            usuarios.retainAll(this.eurep.findByArte());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                        case ("turismo"):
                            usuarios.retainAll(this.eurep.findByTurismo());
                            if (usuarios != null && usuarios.size() != 0) {
                                auxiliar.retainAll(usuarios);
                            }
                            break;
                    }
                }
                if (anyo > 0) {
                    usuarios = this.eurep.findAll();
                    usuarios.retainAll(this.eurep.findByAnyo(inf, sup));
                    if (usuarios != null && usuarios.size() != 0) {
                        auxiliar.retainAll(usuarios);
                    }
                }

                if(auxiliar.size() == max){ //Voy a suponer que no hay forma de filtrar todos con una combinacion de filtros
                    auxiliar.clear();
                }
            }

        return auxiliar;
    }

    public void borrarFiltro(Filtro filtro){
        this.firep.delete(filtro);
    }

    public List<Filtro> findByPK(FiltroPK fk){
        return this.firep.findByPK(fk.getAnalistaeventos(), fk.getIdfiltro());
    }

}
