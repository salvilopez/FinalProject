package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public List<Tag> findAllTag() {
        return null;
    }

    @Override
    public Optional<Tag> findOneTagById(Long id) {
        return Optional.empty();
    }

    @Override
    public Tag createTag(Tag tag) {
        return null;
    }

    @Override
    public Tag updateTag(Tag tag) {
        return null;
    }

    @Override
    public void deleteOneTagById(Long id) {

    }

    @Override
    public void deleteAllTags() {

    }
}
