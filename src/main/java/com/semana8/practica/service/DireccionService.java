package com.semana8.practica.service;

import com.semana8.practica.model.Direccion;
import com.semana8.practica.repository.DireccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<Direccion> listarDirecciones(){
        return direccionRepository.findAll();
    }

    public Direccion buscarDireccionPorId(Long id){
        Optional<Direccion> direccion = direccionRepository.findById(id);

        if (direccion.isPresent()){
            return direccion.get();
        }else{
            throw new RuntimeException("No existe la Direccion");
        }
    }

    public List<Direccion> buscarDireccionPorCalle(String calle){
        List<Direccion> direcciones = direccionRepository.findByCalleContaining(calle);

        if (!direcciones.isEmpty()){
            return direcciones;
        }else{
            throw new RuntimeException("No existen Personas con el nombre " + calle);
        }
    }

    public Direccion guardarDireccion(Direccion direccion){
        return direccionRepository.save(direccion);
    }

    public Direccion actualizarDireccionPorId(Long id, Direccion direccion){
        Direccion direccionExistente = buscarDireccionPorId(id);

        direccionExistente.setCalle(direccion.getCalle());
        direccionExistente.setPersona(direccion.getPersona());

        return direccionRepository.save(direccionExistente);

    }

    public void eliminarDireccionPorId(Long id){

        Direccion direccionExistente = buscarDireccionPorId(id);

        direccionRepository.deleteById(direccionExistente.getId());
    }
}
