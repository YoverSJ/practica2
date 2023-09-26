package com.semana8.practica.controller;

import com.semana8.practica.model.Autor;
import com.semana8.practica.model.Direccion;
import com.semana8.practica.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping()
    public ResponseEntity<List<Autor>> listarAutores(){
        return new ResponseEntity<>(autorService.listarAutores(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Autor> guardarAutor(@RequestBody Autor autor){
        return new ResponseEntity<>(autorService.guardarAutor(autor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id){
        return new ResponseEntity<>(autorService.buscarAutorPorId(id), HttpStatus.OK);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Autor>> buscarAutorPorNombre(@RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.buscarAutorPorNombre(autor.getNombre()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutorPorId(@PathVariable Long id, @RequestBody Autor autor){
        return new ResponseEntity<>(autorService.actualizarAutorPorId(id, autor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarAutorPorId(@PathVariable Long id){
        autorService.eliminarAutorPorId(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
