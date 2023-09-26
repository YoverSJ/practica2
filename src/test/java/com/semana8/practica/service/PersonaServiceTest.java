package com.semana8.practica.service;

import com.semana8.practica.model.Persona;
import com.semana8.practica.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonaServiceTest {

    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    PersonaService personaService;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        personaService = new PersonaService(personaRepository);

    }

    @Test
    void listarPersonas() {

        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona(1L, "Yover"));
        personas.add(new Persona(2L, "Camila"));
        personas.add(new Persona(3L, "Junior"));
        personas.add(new Persona(4L, "Juan"));

        Mockito.when(personaRepository.findAll()).thenReturn(personas);

        List<Persona> responsePersonas = personaService.listarPersonas();

        assertNotNull(responsePersonas);

    }

    @Test
    void buscarPersonaPorIdExitoso() {

        Long id = 1L;

        Persona persona = new Persona(1L, "Yover");

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(persona));

        Persona responsePersona = personaService.buscarPersonaPorId(id);

        assertNotNull(responsePersona);
        assertEquals(persona.getNombre(), responsePersona.getNombre());

    }

    @Test
    void buscarPersonaPorIdError() {

        Long id = 1L;

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            personaService.buscarPersonaPorId(id);
        });
    }

    @Test
    void buscarPersonaPorNombreExitoso() {

        String nombre = "Yover";

        List<Persona> personas = new ArrayList<>();

        personas.add(new Persona(1L, "Yover"));
        personas.add(new Persona(2L, "Yover Santiago"));

        Mockito.when(personaRepository.findByNombreContaining(nombre)).thenReturn(personas);

        List<Persona> responsePersonas = personaService.buscarPersonaPorNombre(nombre);

        assertNotNull(responsePersonas);

    }

    @Test
    void buscarPersonaPorNombreNull() {

        String nombre = "Camila";

        List<Persona> personas = new ArrayList<>();

        Mockito.when(personaRepository.findByNombreContaining(nombre)).thenReturn(personas);

        assertThrows(RuntimeException.class, () -> {
           personaService.buscarPersonaPorNombre(nombre);
        });

    }

    @Test
    void guardarPersona() {

        Persona persona = new Persona(null, "Yover");

        Persona personaRespuesta = new Persona(1L, "Yover");

        Mockito.when(personaRepository.save(persona)).thenReturn(personaRespuesta);

        Persona responsePersona = personaService.guardarPersona(persona);

        assertNotNull(responsePersona);
        assertEquals(persona.getNombre(), responsePersona.getNombre());

    }

    @Test
    void actualizarPersonaPorId() {

        Long id = 1L;

        Persona persona = new Persona(null, "Junior");

        Persona personaExistente = new Persona(1L, "Yover");

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(personaExistente));
        Mockito.when(personaRepository.save(personaExistente)).thenReturn(personaExistente);

        Persona responsePersona = personaService.actualizarPersonaPorId(id, persona);

        assertNotNull(responsePersona);
        assertEquals(persona.getNombre(), responsePersona.getNombre());

    }

    @Test
    void eliminarPersonaPorId() {

        Long id = 1L;

        Persona personaExistente = new Persona(1L, "Yover");

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(personaExistente));

        personaService.eliminarPersonaPorId(id);

    }
}