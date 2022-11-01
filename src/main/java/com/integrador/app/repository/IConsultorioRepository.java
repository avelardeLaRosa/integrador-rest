package com.integrador.app.repository;

import com.integrador.app.entities.ConsultorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultorioRepository extends JpaRepository<ConsultorioEntity, Integer> {
}
