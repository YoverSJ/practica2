package com.semana8.practica.service;

import com.semana8.practica.model.Persona;
import com.semana8.practica.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }

    public Persona buscarPersonaPorId(Long id){
        Optional<Persona> persona = personaRepository.findById(id);

        if (persona.isPresent()){
            return persona.get();
        }else{
            throw new RuntimeException("No existe la Persona");
        }
    }

    public List<Persona> buscarPersonaPorNombre(String nombre){
        List<Persona> personas = personaRepository.findByNombreContaining(nombre);

        if (!personas.isEmpty()){
            return personas;
        }else{
            throw new RuntimeException("No existen Personas con el nombre " + nombre);
        }
    }

    public Persona guardarPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Persona actualizarPersonaPorId(Long id, Persona persona){
        Persona personaExistente = buscarPersonaPorId(id);

        personaExistente.setNombre(persona.getNombre());

        return personaRepository.save(personaExistente);

    }

    public void eliminarPersonaPorId(Long id){

        Persona personaExistente = buscarPersonaPorId(id);

        personaRepository.deleteById(personaExistente.getId());
    }

}
