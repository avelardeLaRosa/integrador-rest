package com.integrador.app.util;

public interface IGenericCrud<T> {

    T agregar(T t);
    T actualizar(T t);
    T buscarPorId(Integer id);
    void eliminar(Integer id);

}
