package com.ssl.finalproject.service;

import com.ssl.finalproject.model.Tag;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface TagService {

    Optional<List<Tag>> findAllByNombre(String nombre, Integer pagination,Integer limite);
    List<Tag> findAll(Integer pagination,Integer limite );

    Optional<Tag> findOneTagById(Long id);

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteOneTagById(Long id);

    void deleteAllTags();

}
