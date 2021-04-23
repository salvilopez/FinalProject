package com.ssl.finalproject.service;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ExpertService {





    Expert createExpert(Expert expert);

    Expert updateExpert(Expert expert);

    void deleteOneExpertById(Long id);

    void deleteAllExperts();
    List<Expert> findAllExpertByPuntuacion(Integer puntuacion , Integer paginacion, Integer limite);
    Optional<Expert> findOneExpertById(Long id);
    List<Expert> findAllExpert( Integer pagination,Integer limite);
    List<Expert> findAllByNombre(String nombre, Integer pagination,Integer limite);
    List<Expert>  findAllByModalidad(String modalidad, Integer pagination,Integer limite);
   List<Expert>  findAllByEstado(String estado, Integer pagination,Integer limite);

    List<Expert> findAllExpertByTag(String nombreetiqueta ,Integer pagination,Integer limite);
}
