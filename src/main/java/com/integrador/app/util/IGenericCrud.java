package com.integrador.app.util;

import com.integrador.app.entities.response.ApiReponse;

import java.util.List;

public interface IGenericCrud<T> {

    T agregar(T t);
    T actualizar(T t);
    T buscarPorId(Integer id);
    List<T> obtener(int pageNum, int pageSize);
    void eliminar(Integer id);

}
