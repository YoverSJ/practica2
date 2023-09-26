package com.semana8.practica.repository;

import com.semana8.practica.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    public List<Persona> findByNombreContaining(String nombre);

}
