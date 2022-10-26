package com.integrador.app.service;

import com.integrador.app.dto.DoctorDTO;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.util.IGenericCrud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorService extends IGenericCrud<DoctorDTO> {

    public Paginacion obtenerDoctores(int pageNum, int pageSize, String orderBy, String sortDir);

}
