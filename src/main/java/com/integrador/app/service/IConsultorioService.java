package com.integrador.app.service;

import com.integrador.app.dto.ConsultorioDTO;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.util.IGenericCrud;

public interface IConsultorioService extends IGenericCrud<ConsultorioDTO> {

    public Paginacion obtenerConsultorios(int pageNum, int pageSize, String orderBy, String sortDir);

}
