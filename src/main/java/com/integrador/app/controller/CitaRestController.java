package com.integrador.app.controller;

import com.integrador.app.dto.*;
import com.integrador.app.entities.CitaEntity;
import com.integrador.app.entities.response.ApiReponse;
import com.integrador.app.entities.response.CitaResponse;
import com.integrador.app.excepciones.Messages;
import com.integrador.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("cita")
public class CitaRestController {

    private final ICitaService citaService;
    private final IUsuarioService usuarioService;
    private final IConsultorioService consultorioService;
    private final IDiagnosticoService diagnosticoService;
    private final IDoctorService doctorService;

    @Autowired
    public CitaRestController(ICitaService citaService, IUsuarioService usuarioService, IConsultorioService consultorioService, IDiagnosticoService diagnosticoService, IDoctorService doctorService) {
        this.citaService = citaService;
        this.usuarioService = usuarioService;
        this.consultorioService = consultorioService;
        this.diagnosticoService = diagnosticoService;
        this.doctorService = doctorService;
    }


    @GetMapping
    public ResponseEntity<ApiReponse<CitaResponse>> buscarPorCode(@RequestParam("code") String code){
        ApiReponse response = new ApiReponse();

        CitaResponse citaResponse = citaService.buscarPorCode(code);
        if(citaResponse==null){
            response.failed(Messages.CITA_NOT_FOUND.getCode(), Messages.CITA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.OK.getCode(), Messages.OK.getMessage(),citaResponse);
        return new ResponseEntity<>(response,response.getCode());
    }


    @PostMapping
    public ResponseEntity<ApiReponse<CitaResponse>> guardar(@RequestBody CitaDTO c){
        ApiReponse response = new ApiReponse();

        UsuarioDTO u = usuarioService.buscarPorId(c.getUsuario());
        if(u==null){
            response.failed(Messages.USER_NOT_FOUND.getCode(), Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        ConsultorioDTO consultorio = consultorioService.buscarPorId(c.getConsultorio());
        if(consultorio==null){
            response.failed(Messages.CONSULTORIO_NOT_FOUND.getCode(), Messages.CONSULTORIO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        DoctorDTO doctor = doctorService.buscarPorId(c.getDoctor());
        if(doctor==null){
            response.failed(Messages.DOCTOR_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        DiagnosticoDTO diagnostico = diagnosticoService.buscarPorId(c.getDiagnostico());
        if(diagnostico==null){
            response.failed(Messages.DIAGNOSTICO_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        CitaResponse citaResponse = citaService.agregar(c);
        if(citaResponse==null){
            response.failed(Messages.CITA_OCUPADA.getCode(), Messages.CITA_OCUPADA.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        response.success(Messages.CREATED.getCode(), Messages.CREATED.getMessage(),citaResponse);
        return new ResponseEntity<>(response,response.getCode());

    }

    @PutMapping
    public ResponseEntity<ApiReponse<CitaResponse>> actualizar(@RequestBody CitaDTO c){
        ApiReponse response = new ApiReponse();

        UsuarioDTO u = usuarioService.buscarPorId(c.getUsuario());
        if(u==null){
            response.failed(Messages.USER_NOT_FOUND.getCode(), Messages.USER_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        ConsultorioDTO consultorio = consultorioService.buscarPorId(c.getConsultorio());
        if(consultorio==null){
            response.failed(Messages.CONSULTORIO_NOT_FOUND.getCode(), Messages.CONSULTORIO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        DoctorDTO doctor = doctorService.buscarPorId(c.getDoctor());
        if(doctor==null){
            response.failed(Messages.DOCTOR_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        DiagnosticoDTO diagnostico = diagnosticoService.buscarPorId(c.getDiagnostico());
        if(diagnostico==null){
            response.failed(Messages.DIAGNOSTICO_NOT_FOUND.getCode(), Messages.DIAGNOSTICO_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        CitaResponse res = citaService.buscarPorFecha(c.getFechaCreacion());
        if(res!=null){
            response.failed(Messages.CITA_OCUPADA.getCode(), Messages.CITA_OCUPADA.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        CitaResponse citaResponse = citaService.actualizar(c);

        if(citaResponse==null){
            response.failed(Messages.CITA_NOT_FOUND.getCode(), Messages.CITA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }

        response.success(Messages.UPDATED.getCode(), Messages.UPDATED.getMessage(),citaResponse);
        return new ResponseEntity<>(response,response.getCode());

    }

    @DeleteMapping
    public ResponseEntity<ApiReponse<?>> eliminar(@RequestParam("code") String code){
        ApiReponse response = new ApiReponse();

        CitaResponse citaResponse = citaService.buscarPorCode(code);
        if(citaResponse==null){
            response.failed(Messages.CITA_NOT_FOUND.getCode(), Messages.CITA_NOT_FOUND.getMessage());
            return new ResponseEntity<>(response,response.getCode());
        }
        citaService.eliminarPorCode(code);

        response.success(Messages.DELETED.getCode(), Messages.DELETED.getMessage(),"Eliminacion Satisfactoria");
        return new ResponseEntity<>(response,response.getCode());

    }







}
