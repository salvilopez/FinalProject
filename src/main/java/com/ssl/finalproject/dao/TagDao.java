package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Tag;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface TagDao {

    List<Tag> findAllByNombre(String nombre, Integer pagination,Integer limite);
    List<Tag> findAllByCreador(String creador, Integer pagination,Integer limite);
    List<Tag> findAllByFechaCreacion(LocalDate fechaCreacion, Integer pagination, Integer limite);
    Tag findTagByID(Long id);
    List<Tag> findAll(Integer pagination,Integer limite );
    void deleteTagsFromAllExpert(Long idTag );
}
