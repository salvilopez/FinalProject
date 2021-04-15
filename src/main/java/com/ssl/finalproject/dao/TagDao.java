package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Tag;
import java.util.List;

public interface TagDao {

    List<Tag> findAllByNombre(String nombre, Integer pagination,Integer limite);
    Tag findTagByID(Long id);
    List<Tag> findAll(Integer pagination,Integer limite );
}
