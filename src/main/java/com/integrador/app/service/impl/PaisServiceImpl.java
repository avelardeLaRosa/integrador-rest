package com.integrador.app.service.impl;

import com.integrador.app.dto.PaisDTO;
import com.integrador.app.entities.PaisEntity;
import com.integrador.app.repository.IPaisRepository;
import com.integrador.app.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements IPaisService {

    private final IPaisRepository paisRepository;

    @Autowired
    public PaisServiceImpl(IPaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public PaisDTO agregar(PaisDTO paisDTO) {
        return null;
    }

    @Override
    public PaisDTO actualizar(PaisDTO paisDTO) {
        return null;
    }

    @Override
    public PaisDTO buscarPorId(Integer id) {
        Optional<PaisEntity> optionalPais = paisRepository.findById(id);
        if(optionalPais.isEmpty()){
            return null;
        }
        PaisEntity pais = optionalPais.get();
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setId(pais.getId());
        paisDTO.setDescripcion(pais.getDescripcion());
        return paisDTO;
    }

    @Override
    public List<PaisDTO> obtener(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }
}
