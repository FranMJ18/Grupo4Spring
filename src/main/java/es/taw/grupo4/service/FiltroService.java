package es.taw.grupo4.service;

import es.taw.grupo4.dao.FiltroRepository;
import es.taw.grupo4.dto.FiltroDto;
import es.taw.grupo4.entity.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroService {

    private FiltroRepository firep;

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
}
