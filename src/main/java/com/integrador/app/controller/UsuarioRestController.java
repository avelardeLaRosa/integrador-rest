package com.integrador.app.controller;

import com.integrador.app.dto.PaisDTO;
import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.PaisEntity;
import com.integrador.app.entities.request.UsuarioRequest;
import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IPaisService;
import com.integrador.app.service.IUsuarioService;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    private final IPaisService paisService;

    @Autowired
    public UsuarioRestController(IUsuarioService usuarioService, IPaisService paisService) {
        this.usuarioService = usuarioService;
        this.paisService = paisService;
    }


    @GetMapping("/get")
    public ResponseEntity<ApiReponse<Paginacion>> listarUsuarios(
            @RequestParam(value = "pageNum", defaultValue = ConstantesServicio.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = ConstantesServicio.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantesServicio.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = ConstantesServicio.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiReponse response = new ApiReponse();
        Paginacion usuarios = usuarioService.obtenerUsuarios(pageNum, pageSize, orderBy,sortDir);
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(), usuarios);
        return new ResponseEntity<>(response, response.getCode());
    }


    @PostMapping
    public ResponseEntity<ApiReponse<UsuarioDTO>> agregar(
            @RequestBody UsuarioRequest usuarioRequest
            ){
        ApiReponse<UsuarioDTO> response = new ApiReponse<>();

        PaisDTO paisDTO = paisService.buscarPorId(usuarioRequest.getIdPais());
        if(paisDTO == null){
            response.failed(Messages.PAIS_NOT_FOUND.getCode(), Messages.PAIS_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        UsuarioDTO usuario = usuarioService.agregar(usuarioRequest);

        if(usuario == null){
            response.failed(Messages.EMAIL_EXISTS.getCode(), Messages.EMAIL_EXISTS.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }


        response.success(Messages.CREATED.getCode(),Messages.CREATED.getMessage(),usuario);
        return new ResponseEntity<>(response,response.getCode());
    }

    @PutMapping
    public ResponseEntity<ApiReponse<UsuarioDTO>> actualizar(
            @RequestBody UsuarioDTO usuarioDTO
    ){

        UsuarioDTO uDto = usuarioService.actualizar(usuarioDTO);
        ApiReponse<UsuarioDTO> response = new ApiReponse<>();

        if(uDto == null){
            response.error(Messages.USER_NOT_FOUND.getCode(),Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.UPDATED.getCode(),Messages.UPDATED.getMessage(), response.getData());
        return new ResponseEntity<>(response,response.getCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiReponse<String>> eliminar(
            @PathVariable("id") int id
    ){
        UsuarioDTO usuarioDTO = usuarioService.buscarPorId(id);
        ApiReponse<String> response = new ApiReponse<>();
        if(usuarioDTO==null){
            response.error(Messages.USER_NOT_FOUND.getCode(),Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        usuarioService.eliminar(usuarioDTO.getId());
        response.success(Messages.DELETED.getCode(),Messages.DELETED.getMessage());
        return new ResponseEntity<>(response,response.getCode());
    }










}
