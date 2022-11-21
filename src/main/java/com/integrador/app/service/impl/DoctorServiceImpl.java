package com.integrador.app.service.impl;

import com.integrador.app.dto.DoctorDTO;
import com.integrador.app.entities.ConsultorioEntity;
import com.integrador.app.entities.DoctorEntity;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.repository.IConsultorioRepository;
import com.integrador.app.repository.IDoctorRepository;
import com.integrador.app.service.IDoctorService;
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
public class DoctorServiceImpl implements IDoctorService {


    private final IDoctorRepository doctorRepository;

    private final IConsultorioRepository consultorioRepository;

    @Autowired
    public DoctorServiceImpl(IDoctorRepository doctorRepository, IConsultorioRepository consultorioRepository) {
        this.doctorRepository = doctorRepository;
        this.consultorioRepository = consultorioRepository;
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
        Optional<DoctorEntity> optional = doctorRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        DoctorEntity doctor = optional.get();
        DoctorDTO dDto = new DoctorDTO();
        dDto.setId(doctor.getId());
        dDto.setNombre(doctor.getNombre());
        dDto.setApellido(doctor.getApellido());
        dDto.setEspecialidad(doctor.getEspecialidad());
        dDto.setConsultorioDTO(doctor.getConsultorio().getDescripcion());
        return dDto;
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
    public Paginacion obtenerDoctorPorConsultorio(int pageNum, int pageSize, String orderBy, String sortDir, int id) {

        Optional<ConsultorioEntity> optional = consultorioRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        ConsultorioEntity consultorio = optional.get();

        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<DoctorEntity> doctores = doctorRepository.findAllByConsultorio_Id(pageable,consultorio.getId());
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
        doctorDTO.setConsultorioDTO(doctorEntity.getConsultorio().getDescripcion());
        return doctorDTO;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
