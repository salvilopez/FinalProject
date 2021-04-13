package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.repository.TagRepository;
import com.ssl.finalproject.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);
    private final TagRepository repository;
    private final TagDao tagDao;

    public TagServiceImpl(TagRepository repository, TagDao tagDao) {
        this.repository = repository;
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findAllTag() {
        log.info("findAlltags");
        return tagDao.findAllTags();
    }

    @Override
    public Optional<Tag> findOneTagById(Long id) {
        log.info("findOneTagById");
        if (id != null && repository.existsById(id))
            return tagDao.findTagByID(id);
        return Optional.empty();
    }

    @Override
    public Tag createTag(Tag tag) {
        log.info("createTag");
        if (ObjectUtils.isEmpty(tag))
            return null;

        return repository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        log.info("updateTag");
        if ((ObjectUtils.isEmpty(tag)))
            return null;

        return repository.save(tag);
    }

    @Override
    public void deleteOneTagById(Long id) {
        log.info("deleteOneTagById");
        if (id != null)
            repository.deleteById(id);
    }



    @Override
    public void deleteAllTags() {
        log.info("deleteAllTags");
        repository.deleteAll();

    }

}
