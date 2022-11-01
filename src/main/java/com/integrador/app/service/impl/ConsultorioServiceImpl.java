package com.integrador.app.service.impl;

import com.integrador.app.dto.ConsultorioDTO;
import com.integrador.app.dto.DoctorDTO;
import com.integrador.app.entities.ConsultorioEntity;
import com.integrador.app.entities.DoctorEntity;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.repository.IConsultorioRepository;
import com.integrador.app.service.IConsultorioService;
import com.integrador.app.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultorioServiceImpl implements IConsultorioService {

    private final IConsultorioRepository consultorioRepository;

    @Autowired
    public ConsultorioServiceImpl(IConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }


    @Override
    public ConsultorioDTO agregar(ConsultorioDTO consultorioDTO) {
        return null;
    }

    @Override
    public ConsultorioDTO actualizar(ConsultorioDTO consultorioDTO) {
        return null;
    }

    @Override
    public ConsultorioDTO buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<ConsultorioDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ConsultorioEntity> consultorio = consultorioRepository.findAll(pageable);
        List<ConsultorioEntity> listaConsultorios = consultorio.getContent();
        List<ConsultorioDTO> list = ObjectMapperUtils.mapAll(listaConsultorios,ConsultorioDTO.class);
        return list;
    }

    @Override
    public Paginacion obtenerConsultorios(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<ConsultorioEntity> consultorios = consultorioRepository.findAll(pageable);
        List<ConsultorioEntity> listaConsultorios = consultorios.getContent();
        List<ConsultorioDTO> contenido = listaConsultorios.stream().map(paginacion -> mapListDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion = new Paginacion();
        paginacion.setPageNumber(consultorios.getNumber());
        paginacion.setPageSize(consultorios.getSize());
        paginacion.setClassBody(contenido);
        paginacion.setTotalElements(consultorios.getTotalElements());
        paginacion.setTotalPages(consultorios.getTotalPages());
        paginacion.setLastRow(consultorios.isLast());
        return paginacion;
    }

    @Override
    public void eliminar(Integer id) {

    }

    private ConsultorioDTO mapListDTO(ConsultorioEntity consultorio) {

        ConsultorioDTO consultorioDTO = new ConsultorioDTO();
        consultorioDTO.setId(consultorio.getId());
        consultorioDTO.setDescripcion(consultorio.getDescripcion());
        consultorioDTO.setContacto(consultorio.getContacto());
        consultorioDTO.setCorreo(consultorio.getCorreo());
        List<DoctorDTO> list = ObjectMapperUtils.mapAll(consultorio.getDoctores(), DoctorDTO.class);
        consultorioDTO.setDoctoresDTO(list);
        return consultorioDTO;
    }



    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }

}
