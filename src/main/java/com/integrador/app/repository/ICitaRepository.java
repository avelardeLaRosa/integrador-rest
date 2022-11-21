package com.integrador.app.repository;

import com.integrador.app.entities.CitaEntity;
import com.integrador.app.entities.response.CitaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ICitaRepository extends JpaRepository<CitaEntity,Integer> {

    Optional<CitaEntity> findByCode(String code);

    @Query(
            "SELECT c FROM CitaEntity c WHERE c.fecha = :fecha"
    )
    Optional<CitaEntity> findByFecha(String fecha);

    @Transactional
    void deleteByCode(String code);
}
