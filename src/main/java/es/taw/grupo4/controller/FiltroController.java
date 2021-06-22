package es.taw.grupo4.controller;

import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.dto.FiltroDto;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("filtro")
@Controller
public class FiltroController {

    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private FiltroRepository filrep;
    @Autowired
    public void setFilrep(FiltroRepository filrep) {
        this.filrep = filrep;
    }

    @GetMapping("/")
    public String inicio(HttpSession http, Model model){
        UsuarioDto analista = (UsuarioDto)http.getAttribute("usuario");
        List<FiltroDto> listaFiltrosdto = new ArrayList<>();
        List<Filtro> listaFiltros = filrep.filtrosCreador(analista.getId());
        for(Filtro fd : listaFiltros){
            FiltroDto fdt = new FiltroDto();
            //fdt.setAnalistaeventos(fd.getFiltroPK().getAnalistaeventos());
            fdt.setIdfiltro(fd.getFiltroPK().getIdfiltro());
            fdt.setAnyo(fd.getAnyo());
            fdt.setCategoria(fd.getCategoria());
            fdt.setCiudad(fd.getCiudad());
            fdt.setCoste_entrada(fd.getCosteEntrada());
            fdt.setNombre(fd.getNombre());
            fdt.setSexo(fd.getSexo());
            fdt.setEdad_lim_inf(fd.getEdadLimInf());
            fdt.setEdad_lim_sup(fd.getEdadLimSup());

            listaFiltrosdto.add(fdt);
        }
        model.addAttribute("listaFiltros", listaFiltrosdto);
        return "AnalistaEventos";
    }

    @GetMapping("/crearAnalisis")
    public String crearAnalisis(){
        return"CrearAnalisis";
    }

}
