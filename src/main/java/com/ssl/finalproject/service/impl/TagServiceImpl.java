package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.repository.ExpertRepository;
import com.ssl.finalproject.repository.TagRepository;
import com.ssl.finalproject.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);
    private final TagRepository repository;
    private final ExpertRepository expertRepository;
    private final TagDao tagDao;

    public TagServiceImpl(TagRepository repository, ExpertRepository expertRepository, TagDao tagDao) {
        this.repository = repository;
        this.expertRepository = expertRepository;
        this.tagDao = tagDao;
    }

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Tag> findAllByNombre(String nombre, Integer pagination, Integer limite) {
        log.info("findAllByNombre");
        return tagDao.findAllByNombre(nombre,pagination,limite);
    }

    @Override
    public List<Tag> findAll(Integer pagination, Integer limite) {
        log.info("findAll");
        return tagDao.findAll(pagination,limite);
    }

    @Override
    public Optional<Tag> findOneTagById(Long id) {
        log.info("findOneTagById");
        if (repository.existsById(id)){
            Tag tag =tagDao.findTagByID(id);
            if(tag==null){
                return Optional.empty();
            }else{
                return Optional.of(tag);
            }
        }
        return Optional.empty();
    }

    @Override
    public Tag createTag(Tag tag) {
        log.info("createTag");

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
     public List<Tag> findAllByCreador(String creador, Integer pagination,Integer limite){
        return  tagDao.findAllByCreador(creador,pagination,limite);
    }
    @Override
    public List<Tag> findAllByFechaCreacion(LocalDate fechaCreacion, Integer paginacion, Integer limite){
        return  tagDao.findAllByFechaCreacion(fechaCreacion,paginacion,limite);
    }

    @Override
    public void deleteAllTags() {
        log.info("deleteAllTags");
        repository.deleteAll();

    }
    @Transactional
    public void deleteTagByIdForAllExperts(Long tagId){
        Query queryNative = manager.createNativeQuery("delete from expert_tag where tag_id = "+ tagId);
        queryNative.executeUpdate();

        repository.deleteById(tagId);
    }
}
