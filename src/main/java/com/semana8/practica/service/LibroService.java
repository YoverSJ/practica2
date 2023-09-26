package com.semana8.practica.service;

import com.semana8.practica.model.Libro;
import com.semana8.practica.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarLibros(){
        return libroRepository.findAll();
    }

    public Libro buscarLibroPorId(Long id){
        Optional<Libro> libro = libroRepository.findById(id);

        if (libro.isPresent()){
            return libro.get();
        }else{
            throw new RuntimeException("No existe la Libro");
        }
    }

    public List<Libro> buscarLibroPorNombre(String titulo){
        List<Libro> libros = libroRepository.findByTituloContaining(titulo);

        if (!libros.isEmpty()){
            return libros;
        }else{
            throw new RuntimeException("No existen Personas con el nombre " + titulo);
        }
    }

    public Libro guardarLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro actualizarLibroPorId(Long id, Libro libro){
        Libro libroExistente = buscarLibroPorId(id);

        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setAutores(libro.getAutores());

        return libroRepository.save(libroExistente);

    }

    public void eliminarLibroPorId(Long id){

        Libro libroExistente = buscarLibroPorId(id);

        libroRepository.deleteById(libroExistente.getId());
    }
}
