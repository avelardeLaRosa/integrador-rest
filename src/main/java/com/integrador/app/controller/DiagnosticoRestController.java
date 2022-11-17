package com.integrador.app.controller;

import com.integrador.app.dto.ConsultorioDTO;
import com.integrador.app.dto.DiagnosticoDTO;
import com.integrador.app.entities.DiagnosticoEntity;
import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.IDiagnosticoService;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("diagnostico")
public class DiagnosticoRestController {

    private final IDiagnosticoService diagnosticoService;

    public DiagnosticoRestController(IDiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    @GetMapping
    public ResponseEntity<ApiReponse<DiagnosticoDTO>> listar(
            @RequestParam(value = "pageNum", defaultValue = ConstantesServicio.PAGE_NUMBER,required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = ConstantesServicio.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantesServicio.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = ConstantesServicio.DEFAULT_SORT_BY ,required = false) String sortDir
    ){
        ApiReponse apiReponse = new ApiReponse();
        Paginacion consultorios = diagnosticoService.obtenerDiagnosticos(pageNum, pageSize, orderBy,sortDir);
        if(consultorios == null){
            apiReponse.failed(Messages.DIAGNOSTICO_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(apiReponse,apiReponse.getCode());
        }
        apiReponse.success(Messages.OK.getCode(),Messages.OK.getMessage(),consultorios);
        return new ResponseEntity<>(apiReponse,apiReponse.getCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiReponse<DiagnosticoDTO>> obtenerDiagnostico(
            @PathVariable("id") int id
    ){
        ApiReponse reponses = new ApiReponse();
        DiagnosticoDTO diagnosticoDTO = diagnosticoService.buscarPorId(id);
        if(diagnosticoDTO==null){
            reponses.failed(Messages.DIAGNOSTICO_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(reponses,reponses.getCode());
        }
        reponses.success(Messages.OK.getCode(),Messages.OK.getMessage(),diagnosticoDTO);
        return new ResponseEntity<>(reponses,reponses.getCode());

    }


}
