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

    Optional<Expert> findOneExpertById(Long id);
    List<Expert> findAllExpert( Integer pagination,Integer limite);
    Optional<List<Expert>> findAllByNombre(String nombre, Integer pagination,Integer limite);
    Optional<List<Expert>>  findAllByModalidad(String modalidad, Integer pagination,Integer limite);
    Optional<List<Expert>>  findAllByEstado(String estado, Integer pagination,Integer limite);

    Optional<List<Expert>> findAllExpertByTag(Long id ,Integer pagination,Integer limite);
}
