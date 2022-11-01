package com.integrador.app.service.impl;

import com.integrador.app.dto.DoctorDTO;
import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.DoctorEntity;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.repository.IDoctorRepository;
import com.integrador.app.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements IDoctorService {


    private final IDoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDTO agregar(DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO actualizar(DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<DoctorDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<DoctorEntity> doctores = doctorRepository.findAll(pageable);
        List<DoctorEntity> listaDoctores = doctores.getContent();
        return listaDoctores.stream().map(this::mapListDTO).collect(Collectors.toList());
    }



    @Override
    public Paginacion<DoctorDTO> obtenerDoctores(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DoctorEntity> doctores = doctorRepository.findAll(pageable);
        List<DoctorEntity> listaDoctores = doctores.getContent();
        List<DoctorDTO> contenido = listaDoctores.stream().map(paginacion -> mapListDTO(paginacion)).collect(Collectors.toList());

        Paginacion paginacion = new Paginacion();
        paginacion.setPageNumber(doctores.getNumber());
        paginacion.setPageSize(doctores.getSize());
        paginacion.setClassBody(contenido);
        paginacion.setTotalElements(doctores.getTotalElements());
        paginacion.setTotalPages(doctores.getTotalPages());
        paginacion.setLastRow(doctores.isLast());
        return paginacion;
    }


    @Override
    public void eliminar(Integer id) {

    }

    private DoctorDTO mapListDTO(DoctorEntity doctorEntity) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctorEntity.getId());
        doctorDTO.setNombre(doctorEntity.getNombre());
        doctorDTO.setEspecialidad(doctorEntity.getEspecialidad());
        doctorDTO.setSexo(doctorEntity.getSexo());
        doctorDTO.setConsultorioDTO(doctorEntity.getConsultorio().getDescripcion());
        return doctorDTO;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
