package com.integrador.app.service.impl;

import com.integrador.app.dto.DiagnosticoDTO;
import com.integrador.app.dto.DoctorDTO;
import com.integrador.app.entities.DiagnosticoEntity;
import com.integrador.app.entities.DoctorEntity;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.repository.IDiagnosticoRepository;
import com.integrador.app.service.IDiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    private final IDiagnosticoRepository diagnosticoRepository;

    @Autowired
    public DiagnosticoServiceImpl(IDiagnosticoRepository diagnosticoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @Override
    public DiagnosticoDTO agregar(DiagnosticoDTO diagnosticoDTO) {
        return null;
    }

    @Override
    public DiagnosticoDTO actualizar(DiagnosticoDTO diagnosticoDTO) {
        return null;
    }

    @Override
    public DiagnosticoDTO buscarPorId(Integer id) {
        Optional<DiagnosticoEntity> optional = diagnosticoRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        DiagnosticoEntity diagnosticoEntity = optional.get();
        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();
        diagnosticoDTO.setId(diagnosticoEntity.getId());
        diagnosticoDTO.setDescripcion(diagnosticoEntity.getDescripcion());
        return diagnosticoDTO;
    }

    @Override
    public List<DiagnosticoDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<DiagnosticoEntity> diagnostico = diagnosticoRepository.findAll(pageable);
        List<DiagnosticoEntity> listaDoctores = diagnostico.getContent();
        return listaDoctores.stream().map(this::mapListDTO).collect(Collectors.toList());
    }

    @Override
    public Paginacion obtenerDiagnosticos(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DiagnosticoEntity> diagnosticos = diagnosticoRepository.findAll(pageable);
        List<DiagnosticoEntity> listaDiagnosticos = diagnosticos.getContent();
        List<DiagnosticoDTO> contenido = listaDiagnosticos.stream().map(paginacion -> mapListDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion = new Paginacion();
        paginacion.setPageNumber(diagnosticos.getNumber());
        paginacion.setPageSize(diagnosticos.getSize());
        paginacion.setClassBody(contenido);
        paginacion.setTotalElements(diagnosticos.getTotalElements());
        paginacion.setTotalPages(diagnosticos.getTotalPages());
        paginacion.setLastRow(diagnosticos.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(Integer id) {

    }

    private DiagnosticoDTO mapListDTO(DiagnosticoEntity diagnosticoEntity) {
        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();
        diagnosticoDTO.setId(diagnosticoEntity.getId());
        diagnosticoDTO.setDescripcion(diagnosticoEntity.getDescripcion());
        return diagnosticoDTO;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
