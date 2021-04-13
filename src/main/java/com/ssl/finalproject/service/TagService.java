package com.ssl.finalproject.service;

import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> findAllTag();

    Optional<Tag> findOneTagById(Long id);

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteOneTagById(Long id);

    void deleteAllTags();
}
