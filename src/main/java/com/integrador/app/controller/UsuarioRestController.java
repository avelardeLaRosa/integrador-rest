package com.integrador.app.controller;

import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.entities.response.OutPutEntity;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @PostMapping
    public ResponseEntity<OutPutEntity<UsuarioDTO>> agregar(
            @RequestBody UsuarioDTO usuarioDTO
    ){
        OutPutEntity<UsuarioDTO> out = new OutPutEntity<>();
        out.success(Messages.CREATED.getCode(),Messages.CREATED.getMessage(),usuarioService.agregar(usuarioDTO));
        return new ResponseEntity<OutPutEntity<UsuarioDTO>>(out, out.getCode());
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> actualizar(
            @RequestBody UsuarioDTO usuarioDTO
    ){
        return new ResponseEntity<>(usuarioService.actualizar(usuarioDTO),HttpStatus.OK);
    }






}
