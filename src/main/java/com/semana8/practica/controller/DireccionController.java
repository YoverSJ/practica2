package com.semana8.practica.controller;

import com.semana8.practica.model.Direccion;
import com.semana8.practica.model.Persona;
import com.semana8.practica.service.DireccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping()
    public ResponseEntity<List<Direccion>> listarDirecciones(){
        return new ResponseEntity<>(direccionService.listarDirecciones(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Direccion> guardarDireccion(@RequestBody Direccion direccion){
        return new ResponseEntity<>(direccionService.guardarDireccion(direccion), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarDireccionPorId(@PathVariable Long id){
        return new ResponseEntity<>(direccionService.buscarDireccionPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccionPorId(@PathVariable Long id, @RequestBody Direccion direccion){
        return new ResponseEntity<>(direccionService.actualizarDireccionPorId(id, direccion), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarDireccionPorId(@PathVariable Long id){
        direccionService.eliminarDireccionPorId(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
