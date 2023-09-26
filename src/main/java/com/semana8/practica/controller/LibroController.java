package com.semana8.practica.controller;

import com.semana8.practica.model.Autor;
import com.semana8.practica.model.Libro;
import com.semana8.practica.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping()
    public ResponseEntity<List<Libro>> listarLibros(){
        return new ResponseEntity<>(libroService.listarLibros(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro){
        return new ResponseEntity<>(libroService.guardarLibro(libro), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibroPorId(@PathVariable Long id){
        return new ResponseEntity<>(libroService.buscarLibroPorId(id), HttpStatus.OK);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibroPorNombre(@RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.buscarLibroPorNombre(libro.getTitulo()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibroPorId(@PathVariable Long id, @RequestBody Libro libro){
        return new ResponseEntity<>(libroService.actualizarLibroPorId(id, libro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarLibroPorId(@PathVariable Long id){
        libroService.eliminarLibroPorId(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
