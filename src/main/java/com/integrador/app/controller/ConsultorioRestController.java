package com.integrador.app.controller;

import com.integrador.app.dto.ConsultorioDTO;
import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IConsultorioService;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consultorio")
public class ConsultorioRestController {

    private final IConsultorioService consultorioService;

    @Autowired
    public ConsultorioRestController(IConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }

    @GetMapping
    public ResponseEntity<ApiReponse<ConsultorioDTO>> listar(
            @RequestParam(value = "pageNum", defaultValue = ConstantesServicio.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = ConstantesServicio.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantesServicio.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = ConstantesServicio.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiReponse apiReponse = new ApiReponse();
        Paginacion consultorios = consultorioService.obtenerConsultorios(pageNum, pageSize, orderBy,sortDir);
        if(consultorios == null){
            apiReponse.failed(Messages.CONSULTORIO_NOT_FOUND.getCode(), Messages.CONSULTORIO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(apiReponse,apiReponse.getCode());
        }
        apiReponse.success(Messages.OK.getCode(),Messages.OK.getMessage(),consultorios);
        return new ResponseEntity<>(apiReponse,apiReponse.getCode());
    }


}
