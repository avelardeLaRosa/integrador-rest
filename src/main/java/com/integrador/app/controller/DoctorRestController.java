package com.integrador.app.controller;

import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IDoctorService;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctor")
public class DoctorRestController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorRestController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<ApiReponse<Paginacion>> listarDoctores(
            @RequestParam(value = "pageNum", defaultValue = ConstantesServicio.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = ConstantesServicio.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantesServicio.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = ConstantesServicio.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiReponse response = new ApiReponse();
        Paginacion doctores = doctorService.obtenerDoctores(pageNum, pageSize, orderBy,sortDir);
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(), doctores);
        return new ResponseEntity<>(response, response.getCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiReponse<Paginacion>> listarDoctoresPorConsultorio(
            @RequestParam(value = "pageNum", defaultValue = ConstantesServicio.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = ConstantesServicio.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantesServicio.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = ConstantesServicio.DEFAULT_SORT_BY ,required = false) String sortDir,
            @PathVariable("id") int id
    ){
        ApiReponse response = new ApiReponse();
        Paginacion doctores = doctorService.obtenerDoctorPorConsultorio(pageNum, pageSize, orderBy,sortDir,id);
        if(doctores==null){
            response.failed(Messages.CONSULTORIO_NOT_FOUND.getCode(), Messages.CONSULTORIO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(), doctores);
        return new ResponseEntity<>(response, response.getCode());
    }


}
