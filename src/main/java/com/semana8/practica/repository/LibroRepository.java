package com.semana8.practica.repository;

import com.semana8.practica.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    public List<Libro> findByTituloContaining(String titulo);

}
