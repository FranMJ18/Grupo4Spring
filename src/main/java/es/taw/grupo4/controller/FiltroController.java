package es.taw.grupo4.controller;

import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.dto.FiltroDto;
import es.taw.grupo4.dto.UsuarioDto;
import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.Usuario;
import es.taw.grupo4.service.FiltroService;
import es.taw.grupo4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ArrayType;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("filtro")
@Controller
public class FiltroController {

    private UsuarioService usuarioService;
    private FiltroService fs;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private FiltroRepository filrep;
    @Autowired
    public void setFilrep(FiltroRepository filrep) {
        this.filrep = filrep;
    }
    @Autowired
    public void setFs(FiltroService fs) {
        this.fs = fs;
    }

    @GetMapping("/")
    public String inicio(HttpSession http, Model model){
        UsuarioDto analista = (UsuarioDto)http.getAttribute("usuario");
        List<FiltroDto> listaFiltrosdto = new ArrayList<>();
        List<Filtro> listaFiltros = fs.encontarByAnalista(analista.getId());
        for(Filtro fd : listaFiltros){
            listaFiltrosdto.add(fd.getDto());
        }
        model.addAttribute("listaFiltros", listaFiltrosdto);
        return "AnalistaEventos";
    }

    @GetMapping("/crearAnalisis/{id}")
    public String crearAnalisis(@PathVariable("id") Integer id, Model model){
        //FiltroService fs = new FiltroService();
        FiltroDto fdto;
        Filtro filtro;
        try{
             filtro = fs.econtrarById(id);
             fdto = filtro.getDto();
        }catch(Exception e){
            filtro = null;
            fdto = new FiltroDto();
            fdto.setIdfiltro(0);
            fdto.setSexo("");
            fdto.setCategoria("");
        }
        String[] sexo = new String[]{"","Hombre", "Mujer", "Otro"};

        model.addAttribute("sexo", sexo);
        model.addAttribute("fdto", fdto);

        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("");
        categorias.add("gaming");
        categorias.add("teatro");
        categorias.add("deporte");
        categorias.add("aireLibre");
        categorias.add("musica");
        categorias.add("lectura");
        categorias.add("formacion");
        categorias.add("conferencia");
        categorias.add("benefico");
        categorias.add("arte");
        categorias.add("turismo");

        model.addAttribute("categorias", categorias);

        return"CrearAnalisis";
    }

    @PostMapping("/guardarFiltro")
    public String guardarAnalisis(Model model, @ModelAttribute("fdto") FiltroDto fdto, HttpSession session){
        UsuarioDto current = (UsuarioDto) session.getAttribute("usuario");
        this.fs.guardarOEditar(fdto, current.getId());
        return inicio(session, model);
    }

    @GetMapping("/mostrarUsuarios/{id}")
    public String mostrarUsuarios(@PathVariable("id") Integer id){
        Filtro filtro = this.filrep.getById(id);
        FiltroDto fdto = filtro.getDto();
        return null; // TODO
    }

}
