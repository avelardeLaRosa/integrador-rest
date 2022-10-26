package com.integrador.app.repository;

import com.integrador.app.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepository extends JpaRepository<PaisEntity,Integer> {
}
