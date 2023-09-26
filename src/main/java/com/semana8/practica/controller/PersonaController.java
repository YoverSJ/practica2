package com.semana8.practica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.semana8.practica.model.Persona;
import com.semana8.practica.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping()
    public ResponseEntity<List<Persona>> listarPersonas(){
        return new ResponseEntity<>(personaService.listarPersonas(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona){
        return new ResponseEntity<>(personaService.guardarPersona(persona), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> buscarPersonaPorId(@PathVariable Long id){
        return new ResponseEntity<>(personaService.buscarPersonaPorId(id), HttpStatus.OK);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Persona>> buscarPersonaPorNombre(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.buscarPersonaPorNombre(persona.getNombre()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersonaPorId(@PathVariable Long id, @RequestBody Persona persona){
        return new ResponseEntity<>(personaService.actualizarPersonaPorId(id, persona), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarPersonaPorId(@PathVariable Long id){
        personaService.eliminarPersonaPorId(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
