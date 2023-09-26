package com.semana8.practica.service;

import com.semana8.practica.model.Autor;
import com.semana8.practica.repository.AutorRepository;
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

class AutorServiceTest {

    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    AutorService autorService;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        autorService = new AutorService(autorRepository);

    }

    @Test
    void listarAutores() {

        List<Autor> autores = new ArrayList<>();

        autores.add(new Autor(1L, "Yover"));
        autores.add(new Autor(2L, "Camila"));

        Mockito.when(autorRepository.findAll()).thenReturn(autores);

        List<Autor> responseAutores = autorService.listarAutores();

        assertNotNull(responseAutores);

    }

    @Test
    void buscarAutorPorIdExitoso() {

        Long id = 1L;

        Autor autor = new Autor(1L, "Yover");

        Mockito.when(autorRepository.findById(id)).thenReturn(Optional.of(autor));

        Autor responseAutor = autorService.buscarAutorPorId(id);

        assertNotNull(responseAutor);
        assertEquals(autor.getNombre(), responseAutor.getNombre());

    }

    @Test
    void buscarAutorPorIdError() {

        Long id = 1L;

        Mockito.when(autorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            autorService.buscarAutorPorId(id);
        });
    }

    @Test
    void buscarAutorPorNombreExitoso() {

        String nombre = "Yover";

        List<Autor> autores = new ArrayList<>();

        autores.add(new Autor(1L, "Yover"));
        autores.add(new Autor(2L, "Yover Santiago"));

        Mockito.when(autorRepository.findByNombreContaining(nombre)).thenReturn(autores);

        List<Autor> responseAutores = autorService.buscarAutorPorNombre(nombre);

        assertNotNull(responseAutores);
    }

    @Test
    void buscarAutorPorNombreNull() {

        String nombre = "Camila";

        List<Autor> autores = new ArrayList<>();

        Mockito.when(autorRepository.findByNombreContaining(nombre)).thenReturn(autores);

        assertThrows(RuntimeException.class, () -> {
            autorService.buscarAutorPorNombre(nombre);
        });

    }

    @Test
    void guardarAutor() {

        Autor autor = new Autor(null, "Yover");

        Autor autorRespuesta = new Autor(1L, "Yover");

        Mockito.when(autorRepository.save(autor)).thenReturn(autorRespuesta);

        Autor responseAutor = autorService.guardarAutor(autor);

        assertNotNull(responseAutor);
        assertEquals(autor.getNombre(), responseAutor.getNombre());

    }

    @Test
    void actualizarAutorPorId() {

        Long id = 1L;

        Autor autor = new Autor(null, "Junior");

        Autor autorExistente = new Autor(1L, "Yover");

        Mockito.when(autorRepository.findById(id)).thenReturn(Optional.of(autorExistente));
        Mockito.when(autorRepository.save(autorExistente)).thenReturn(autorExistente);

        Autor responseAutor = autorService.actualizarAutorPorId(id, autor);

        assertNotNull(responseAutor);
        assertEquals(autor.getNombre(), responseAutor.getNombre());

    }

    @Test
    void eliminarAutorPorId() {

        Long id = 1L;

        Autor autorExistente = new Autor(1L, "Yover");

        Mockito.when(autorRepository.findById(id)).thenReturn(Optional.of(autorExistente));

        autorService.eliminarAutorPorId(id);

    }
}