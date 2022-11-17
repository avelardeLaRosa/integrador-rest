package com.integrador.app.service;

import com.integrador.app.dto.DiagnosticoDTO;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.util.IGenericCrud;

public interface IDiagnosticoService extends IGenericCrud<DiagnosticoDTO> {

    public Paginacion obtenerDiagnosticos(int pageNum, int pageSize, String orderBy, String sortDir);
}
