package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    List<Tag> findAllTags();

    Optional<Tag> findTagByID(Long id);
}
