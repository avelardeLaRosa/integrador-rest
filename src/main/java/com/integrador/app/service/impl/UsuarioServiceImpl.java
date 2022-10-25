package com.integrador.app.service.impl;

import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.repository.IUsuarioRepository;
import com.integrador.app.service.IUsuarioService;
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

    private ModelMapper modelMapper;

    @Autowired
    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UsuarioDTO agregar(UsuarioDTO usuarioDTO) {


        Optional<UsuarioEntity> optional = usuarioRepository.findByCorreo(usuarioDTO.getCorreo());
        if(optional.isPresent()){
            return null;
        }

        UsuarioEntity u = new UsuarioEntity();
        u.setNombre(usuarioDTO.getNombre());
        u.setApellido(usuarioDTO.getApellido());
        u.setEdad(usuarioDTO.getEdad());
        u.setSexo(usuarioDTO.getSexo());
        u.setCorreo(usuarioDTO.getCorreo());
        u.setTelefono(usuarioDTO.getTelefono());

        UsuarioEntity user = usuarioRepository.save(u);

        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setId(user.getId());
        uDto.setNombre(user.getNombre());
        uDto.setApellido(user.getApellido());
        uDto.setEdad(user.getEdad());
        uDto.setSexo(user.getSexo());
        uDto.setCorreo(user.getCorreo());
        uDto.setTelefono(user.getTelefono());
        return uDto;

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
    public Paginacion obtenerUsuarios(int pageNum, int pageSize, String orderBy, String sortDir) {
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
        return usuarioDTO;
    }

    public Sort ordenarPor(String ordernarPor, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordernarPor).ascending() : Sort.by(ordernarPor).descending();
        return sort;
    }


}
