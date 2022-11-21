package com.integrador.app.repository;

import com.integrador.app.dto.ConsultorioDTO;
import com.integrador.app.entities.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity,Integer> {

    Page<DoctorEntity> findAllByConsultorio_Id(Pageable pageable, int c);

}
