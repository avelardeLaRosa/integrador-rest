package com.integrador.app.repository;

import com.integrador.app.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity,Integer> {
}
