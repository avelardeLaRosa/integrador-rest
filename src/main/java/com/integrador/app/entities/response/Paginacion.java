package com.integrador.app.entities.response;

import com.integrador.app.dto.UsuarioDTO;
import lombok.Data;

import java.util.List;

@Data
public class Paginacion<T> {

    private int pageNumber;
    private int pageSize;
    private List<T> classBody;
    private long totalElements;
    private int totalPages;
    private boolean lastRow;
}
