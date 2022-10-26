package com.integrador.app.service;

import com.integrador.app.dto.UsuarioDTO;
import com.integrador.app.entities.UsuarioEntity;
import com.integrador.app.entities.request.UsuarioRequest;
import com.integrador.app.entities.response.Paginacion;
import com.integrador.app.util.IGenericCrud;

public interface IUsuarioService extends IGenericCrud<UsuarioDTO> {

    public Paginacion obtenerUsuarios(int pageNum, int pageSize, String orderBy, String sortDir);
    public UsuarioDTO agregar(UsuarioRequest usuarioRequest);

}
