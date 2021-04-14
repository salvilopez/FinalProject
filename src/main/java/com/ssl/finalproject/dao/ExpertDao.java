package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ExpertDao {

    List<Expert> findAllExperts();

    Optional<Expert> findExpertByID(Long id);

    List<Expert> findAllByNombre(String nombre);
    List<Expert> findAllByModalidad(String modalidad);
    List<Expert> findAllByEstado(String estado);
    //List<Expert> findAllByLimite(String limite);
    //List<Expert> findAllByPagina(String name);
    //List<Expert> findAllByNombre(String name);
}
