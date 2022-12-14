package com.integrador.app.service.impl;

import com.integrador.app.dto.CitaDTO;
import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.CitaEntity;
import com.integrador.app.entities.PaisEntity;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.entities.request.UsuarioRequest;
import com.integrador.app.entities.response.CitaResponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.repository.IPaisRepository;
import com.integrador.app.repository.IUsuarioRepository;
import com.integrador.app.service.IUsuarioService;
import com.integrador.app.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    private final IPaisRepository paisRepository;

    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository, IPaisRepository paisRepository) {
        this.usuarioRepository = usuarioRepository;
        this.paisRepository = paisRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UsuarioDTO agregar(UsuarioRequest usuarioRequest) {

        Optional<UsuarioEntity> optional = usuarioRepository.findByCorreo(usuarioRequest.getCorreo());
        if(optional.isPresent()){
            return null;
        }

        PaisEntity pais = paisRepository.findById(usuarioRequest.getIdPais()).get();

        UsuarioEntity u = new UsuarioEntity();
        u.setNombre(usuarioRequest.getNombre());
        u.setApellido(usuarioRequest.getApellido());
        u.setEdad(usuarioRequest.getEdad());
        u.setSexo(usuarioRequest.getSexo());
        u.setCorreo(usuarioRequest.getCorreo());
        u.setTelefono(usuarioRequest.getTelefono());
        u.setPais(pais);


        UsuarioEntity user = usuarioRepository.save(u);

        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setId(user.getId());
        uDto.setNombre(user.getNombre());
        uDto.setApellido(user.getApellido());
        uDto.setEdad(user.getEdad());
        uDto.setSexo(user.getSexo());
        uDto.setCorreo(user.getCorreo());
        uDto.setTelefono(user.getTelefono());
        uDto.setPais(user.getPais().getDescripcion());
        return uDto;

    }

    @Override
    public UsuarioDTO buscarPorCorreo(String correo) {
        Optional<UsuarioEntity> optional = usuarioRepository.findByCorreo(correo);
        if(optional.isEmpty()){
            return null;
        }
        UsuarioEntity usuario = optional.get();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEdad(usuario.getEdad());
        usuarioDTO.setSexo(usuario.getSexo());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setPais(usuario.getPais().getDescripcion());
        usuarioDTO.setCita(usuario.getCitas().stream().map(c -> {
            CitaResponse response = new CitaResponse();
            response.setId(c.getId());
            response.setCode(c.getCode());
            response.setFecha(c.getFecha());
            response.setConsultorio(c.getConsultorio().getDescripcion());
            response.setDoctor(c.getDoctor().getNombre()+" "+c.getDoctor().getApellido());
            response.setDiagnostico(c.getDiagnostico().getDescripcion());
            return response;
        }).collect(Collectors.toList()));
        return usuarioDTO;
    }


    @Override
    public UsuarioDTO agregar(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {

        Optional<UsuarioEntity> optional = usuarioRepository.findById(usuarioDTO.getId());

        UsuarioEntity nuevoUser = new UsuarioEntity();
        UsuarioDTO uDto = new UsuarioDTO();
        if(optional.isEmpty()){
            return null;
        }

        nuevoUser = optional.get();
        nuevoUser.setNombre(usuarioDTO.getNombre());
        nuevoUser.setApellido(usuarioDTO.getApellido());
        nuevoUser.setEdad(usuarioDTO.getEdad());
        nuevoUser.setSexo(usuarioDTO.getSexo());
        nuevoUser.setCorreo(usuarioDTO.getCorreo());
        nuevoUser.setTelefono(usuarioDTO.getTelefono());


        UsuarioEntity user = usuarioRepository.save(nuevoUser);


        uDto.setNombre(user.getNombre());
        uDto.setApellido(user.getApellido());
        uDto.setEdad(user.getEdad());
        uDto.setSexo(user.getSexo());
        uDto.setCorreo(user.getCorreo());
        uDto.setTelefono(user.getTelefono());
        return  uDto;
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
       //UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro usuario"));
        Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        UsuarioEntity usuario = optional.get();
        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setId(usuario.getId());
        uDto.setNombre(usuario.getNombre());
        uDto.setApellido(usuario.getApellido());
        uDto.setEdad(usuario.getEdad());
        uDto.setSexo(usuario.getSexo());
        uDto.setCorreo(usuario.getCorreo());
        uDto.setTelefono(usuario.getTelefono());
        return uDto;
    }

    @Override
    public List<UsuarioDTO> obtener(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<UsuarioEntity> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioEntity> listaUsuarios = usuarios.getContent();
        return listaUsuarios.stream().map(this::mapListDTO).collect(Collectors.toList());
    }

    @Override
    public Paginacion<UsuarioDTO> obtenerUsuarios(int pageNum, int pageSize, String orderBy, String sortDir) {
        Sort sort = ordenarPor(orderBy,sortDir);
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<UsuarioEntity> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioEntity> listaUsuarios = usuarios.getContent();
        List<UsuarioDTO> contenido = listaUsuarios.stream().map(paginacion -> mapListDTO(paginacion)).collect(Collectors.toList());;

        Paginacion paginacion = new Paginacion();

        paginacion.setPageNumber(usuarios.getNumber());
        paginacion.setPageSize(usuarios.getSize());
        paginacion.setClassBody(contenido);
        paginacion.setTotalElements(usuarios.getTotalElements());
        paginacion.setTotalPages(usuarios.getTotalPages());
        paginacion.setLastRow(usuarios.isLast());
        return paginacion;
    }




    @Override
    public void eliminar(Integer id) {

        usuarioRepository.deleteById(id);
    }



    public UsuarioEntity maperClass(UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = modelMapper.map(usuarioDTO, UsuarioEntity.class);
        return usuario;
    }

    public UsuarioDTO maperDTO(UsuarioEntity usuarioEntity){
        UsuarioDTO usuarioDTO = modelMapper.map(usuarioEntity, UsuarioDTO.class);
        return usuarioDTO;
    }

    private UsuarioDTO mapListDTO(UsuarioEntity usuarioEntity) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioEntity.getId());
        usuarioDTO.setNombre(usuarioEntity.getNombre());
        usuarioDTO.setApellido(usuarioEntity.getApellido());
        usuarioDTO.setEdad(usuarioEntity.getEdad());
        usuarioDTO.setSexo(usuarioEntity.getSexo());
        usuarioDTO.setCorreo(usuarioEntity.getCorreo());
        usuarioDTO.setTelefono(usuarioEntity.getTelefono());
        usuarioDTO.setPais(usuarioEntity.getPais().getDescripcion());
        return usuarioDTO;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
