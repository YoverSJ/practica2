package com.semana8.practica.repository;

import com.semana8.practica.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    public List<Direccion> findByCalleContaining(String calle);

}
