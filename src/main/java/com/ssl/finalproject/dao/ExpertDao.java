package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Expert;
import java.util.List;
import java.util.Optional;

public interface ExpertDao {

    Expert findExpertByID(Long id);
    List<Expert> findAllExperts( Integer paginacion,Integer limite);
    List<Expert> findAllByNombre(String nombre, Integer paginacion,Integer limite);
    List<Expert>  findAllByModalidad(String modalidad, Integer paginacion,Integer limite);
    List<Expert>  findAllByEstado(String estado, Integer paginacion,Integer limite);
    List<Expert> findAllExpertByTag(Long tagid , Integer pagination, Integer limite);
    List<Expert> findAllExpertByPuntuacion(Integer puntuacion , Integer paginacion, Integer limite);
}
