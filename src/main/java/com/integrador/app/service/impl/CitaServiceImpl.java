package com.integrador.app.service.impl;

import com.integrador.app.dto.CitaDTO;
import com.integrador.app.entities.*;
import com.integrador.app.entities.response.CitaResponse;
import com.integrador.app.repository.*;
import com.integrador.app.service.ICitaService;
import com.integrador.app.util.CodeProvider;
import com.integrador.app.util.ConstantesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CitaServiceImpl implements ICitaService {

    private final ICitaRepository citaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IConsultorioRepository consultorioRepository;
    private final IDoctorRepository doctorRepository;
    private final IDiagnosticoRepository diagnosticoRepository;

    @Autowired
    public CitaServiceImpl(ICitaRepository citaRepository, IUsuarioRepository usuarioRepository, IConsultorioRepository consultorioRepository, IDoctorRepository doctorRepository, IDiagnosticoRepository diagnosticoRepository) {
        this.citaRepository = citaRepository;
        this.usuarioRepository = usuarioRepository;
        this.consultorioRepository = consultorioRepository;
        this.doctorRepository = doctorRepository;
        this.diagnosticoRepository = diagnosticoRepository;
    }

    @Override
    public CitaResponse agregar(CitaDTO c) {

        Optional<CitaEntity> optional = citaRepository.findByFecha(c.getFechaCreacion());

        if(optional.isPresent()){
            return null;
        }

        UsuarioEntity u = usuarioRepository.findById(c.getUsuario()).get();
        ConsultorioEntity consultorio = consultorioRepository.findById(c.getConsultorio()).get();
        DoctorEntity doctor = doctorRepository.findById(c.getDoctor()).get();
        DiagnosticoEntity diagnostico = diagnosticoRepository.findById(c.getDiagnostico()).get();

        CitaEntity cita = new CitaEntity();
        cita.setCode(CodeProvider.generateCode(ConstantesServicio.CITA,citaRepository.count()+1,ConstantesServicio.LENGTH_CODE));
        cita.setFecha(c.getFechaCreacion());
        cita.setUsuario(u);
        cita.setConsultorio(consultorio);
        cita.setDoctor(doctor);
        cita.setDiagnostico(diagnostico);

        CitaEntity citaNueva = citaRepository.save(cita);

        CitaResponse citaResponse = new CitaResponse();
        citaResponse.setId(citaNueva.getId());
        citaResponse.setCode(citaNueva.getCode());
        citaResponse.setFecha(citaNueva.getFecha());
        citaResponse.setUsuario(citaNueva.getUsuario().getNombre()+ " " + citaNueva.getUsuario().getApellido());
        citaResponse.setConsultorio(citaNueva.getConsultorio().getDescripcion());
        citaResponse.setDoctor(citaNueva.getDoctor().getNombre()+" "+citaNueva.getDoctor().getApellido());
        citaResponse.setDiagnostico(citaNueva.getDiagnostico().getDescripcion());
        return citaResponse;
    }

    @Override
    public CitaResponse actualizar(CitaDTO c) {

        Optional<CitaEntity> citaOptional = citaRepository.findByCode(c.getCode());
        if(citaOptional.isEmpty()){
            return null;
        }
/*
        UsuarioEntity u = usuarioRepository.findById(c.getUsuario()).get();
        ConsultorioEntity consultorio = consultorioRepository.findById(c.getConsultorio()).get();
        DoctorEntity doctor = doctorRepository.findById(c.getDoctor()).get();
        DiagnosticoEntity diagnostico = diagnosticoRepository.findById(c.getDiagnostico()).get();

 */

        CitaEntity cita = citaOptional.get();
        cita.setFecha(c.getFechaCreacion());
        /*
        cita.setUsuario(u);
        cita.setConsultorio(consultorio);
        cita.setDoctor(doctor);
        cita.setDiagnostico(diagnostico);
         */

        CitaEntity citaNueva = citaRepository.save(cita);

        CitaResponse citaResponse = new CitaResponse();
        citaResponse.setId(citaNueva.getId());
        citaResponse.setFecha(citaNueva.getFecha());
        citaResponse.setUsuario(citaNueva.getUsuario().getNombre()+ " " + citaNueva.getUsuario().getApellido());
        citaResponse.setConsultorio(citaNueva.getConsultorio().getDescripcion());
        citaResponse.setDoctor(citaNueva.getDoctor().getNombre()+" "+citaNueva.getDoctor().getApellido());
        citaResponse.setDiagnostico(citaNueva.getDiagnostico().getDescripcion());
        return citaResponse;
    }

    @Override
    public CitaResponse buscarPorCode(String code) {
        Optional<CitaEntity> optional = citaRepository.findByCode(code);
        if(optional.isEmpty()){
            return null;
        }
        CitaEntity cita = optional.get();
        CitaResponse citaResponse =  new CitaResponse();
        citaResponse.setId(cita.getId());
        citaResponse.setCode(cita.getCode());
        citaResponse.setFecha(cita.getFecha());
        citaResponse.setUsuario(cita.getUsuario().getNombre()+" "+cita.getUsuario().getApellido());
        citaResponse.setConsultorio(cita.getConsultorio().getDescripcion());
        citaResponse.setDiagnostico(cita.getDiagnostico().getDescripcion());
        citaResponse.setDoctor(cita.getDoctor().getNombre()+" "+cita.getDoctor().getApellido());
        return citaResponse;
    }

    @Override
    public CitaResponse buscarPorFecha(String fecha) {
        Optional<CitaEntity> optional = citaRepository.findByFecha(fecha);
        if(optional.isEmpty()){
            return null;
        }
        CitaEntity cita = optional.get();
        CitaResponse citaResponse =  new CitaResponse();
        citaResponse.setId(cita.getId());
        citaResponse.setCode(cita.getCode());
        citaResponse.setFecha(cita.getFecha());
        citaResponse.setUsuario(cita.getUsuario().getNombre()+" "+cita.getUsuario().getApellido());
        citaResponse.setConsultorio(cita.getConsultorio().getDescripcion());
        citaResponse.setDiagnostico(cita.getDiagnostico().getDescripcion());
        citaResponse.setDoctor(cita.getDoctor().getNombre()+" "+cita.getDoctor().getApellido());
        return citaResponse;
    }

    @Override
    public void eliminarPorCode(String code) {
        citaRepository.deleteByCode(code);
    }
}
