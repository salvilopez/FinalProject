package com.ssl.finalproject.service;

import com.ssl.finalproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public interface TagService {

    List<Tag> findAllByNombre(String nombre, Integer pagination,Integer limite);
    List<Tag> findAll(Integer pagination,Integer limite );
    List<Tag> findAllByCreador(String creador, Integer pagination,Integer limite);
    List<Tag> findAllByFechaCreacion(LocalDate fechaCreacion, Integer pagination, Integer limite);
    Optional<Tag> findOneTagById(Long id);

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteOneTagById(Long id);

    void deleteAllTags();
     void deleteTagByIdForAllExperts(Long tagId);
   // List<Tag> findAllExpertwithFilter(String nombre,String creador, String fechaCreacion,Long id,Integer pagina,Integer limite);
}
