package com.integrador.app.repository;

import com.integrador.app.dto.DiagnosticoDTO;
import com.integrador.app.entities.DiagnosticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiagnosticoRepository extends JpaRepository<DiagnosticoEntity,Integer> {
}
