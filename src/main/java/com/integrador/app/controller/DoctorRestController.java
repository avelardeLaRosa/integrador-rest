package com.integrador.app.controller;

import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IDoctorService;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
