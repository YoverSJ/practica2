package com.semana8.practica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnore
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
