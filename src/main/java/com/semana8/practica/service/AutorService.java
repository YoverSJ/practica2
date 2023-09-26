package com.semana8.practica.service;

import com.semana8.practica.model.Autor;
import com.semana8.practica.model.Direccion;
import com.semana8.practica.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    public Autor buscarAutorPorId(Long id){
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()){
            return autor.get();
        }else{
            throw new RuntimeException("No existe la Autor");
        }
    }

    public Autor guardarAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public Autor actualizarAutorPorId(Long id, Autor autor){
        Autor autorExistente = buscarAutorPorId(id);

        autorExistente.setNombre(autor.getNombre());

        return autorRepository.save(autorExistente);

    }

    public void eliminarAutorPorId(Long id){

        Autor autorExistente = buscarAutorPorId(id);

        autorRepository.deleteById(autorExistente.getId());
    }
}
