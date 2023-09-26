package com.semana8.practica.repository;

import com.semana8.practica.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    public List<Autor> findByNombreContaining(String nombre);
}
