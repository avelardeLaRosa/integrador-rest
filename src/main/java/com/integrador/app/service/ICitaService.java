package com.integrador.app.service;

import com.integrador.app.dto.CitaDTO;
import com.integrador.app.entities.response.CitaResponse;

public interface ICitaService {

    CitaResponse agregar(CitaDTO c);
    CitaResponse actualizar(CitaDTO c);
    CitaResponse buscarPorCode(String code);
    CitaResponse buscarPorFecha(String fecha);
    void eliminarPorCode(String code);
}
