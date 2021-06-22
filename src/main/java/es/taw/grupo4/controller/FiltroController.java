package es.taw.grupo4.controller;

import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.entity.Filtro;
import es.taw.grupo4.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("filtro")
@Controller
public class FiltroController {

    private FiltroRepository filrep;
    @Autowired
    public void setFilrep(FiltroRepository filrep) {
        this.filrep = filrep;
    }

    @GetMapping("/")
    public String inicio(HttpSession http, Model model){
        Usuario analista =  (Usuario)http.getAttribute("usuario");
        List<Filtro> listaFiltros = filrep.filtrosCreador(analista.getIdusuario());
        model.addAttribute("listaFiltros", listaFiltros);
        return "AnalistaEventos";
    }

    @GetMapping("/crearAnalisis")
    public String crearAnalisis(){
        return"CrearAnalisis";
    }

}
