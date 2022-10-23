package com.integrador.app.service.impl;

import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.repository.IUsuarioRepository;
import com.integrador.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;

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
            throw new RuntimeException("El correo ya fue ingresado : " + usuarioDTO.getCorreo());
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

        UsuarioEntity nuevoUser = usuarioRepository.findById(usuarioDTO.getId()).get();

        nuevoUser.setNombre(usuarioDTO.getNombre());
        nuevoUser.setApellido(usuarioDTO.getApellido());
        nuevoUser.setEdad(usuarioDTO.getEdad());
        nuevoUser.setSexo(usuarioDTO.getSexo());
        nuevoUser.setCorreo(usuarioDTO.getCorreo());
        nuevoUser.setTelefono(usuarioDTO.getTelefono());

        UsuarioEntity user = usuarioRepository.save(nuevoUser);

        UsuarioDTO uDto = new UsuarioDTO();
        uDto.setNombre(user.getNombre());
        uDto.setApellido(user.getApellido());
        uDto.setEdad(user.getEdad());
        uDto.setSexo(user.getSexo());
        uDto.setCorreo(user.getCorreo());
        uDto.setTelefono(user.getTelefono());
        return uDto;
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro Usuario con id: " + id));
        UsuarioDTO uDto = maperDTO(usuario);
        return uDto;
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

}
