package com.ssl.finalproject.dao.impl;

import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.model.Tag;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.bind.annotation.XmlAnyElement;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Tag> findAllByNombre(String nombre, Integer pagination, Integer limite) {

    if(nombre!=null) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.select(root);
        criteria.where(builder.like(root.get("nombre"),nombre+'%'));
        Query query = manager.createQuery(criteria);
        query.setMaxResults(limite);//size
        query.setFirstResult(pagination);//pagination
        manager.close();
        return query.getResultList();
    }
        return new ArrayList<>();
    }

    @Override
    public List<Tag> findAllByCreador(String creador, Integer pagination, Integer limite) {
        if(creador!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> root = criteria.from(Tag.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("creador"),creador+'%'));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(pagination);//pagination
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Tag> findAllByFechaCreacion(LocalDate fechaCreacion, Integer pagination, Integer limite) {
        if(fechaCreacion!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> root = criteria.from(Tag.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("fechaCreacion"),fechaCreacion));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(pagination);//pagination
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }


    @Override
    public Tag findTagByID(Long id) {
        if(id!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> root = criteria.from(Tag.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("id"), id));
            Tag item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return item;
        }
        return null;
    }

    @Override
    public List<Tag> findAll(Integer pagination, Integer limite) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.select(root);
        Query query = manager.createQuery(criteria);
        query.setMaxResults(limite);//size
        query.setFirstResult(pagination);//pagination
        manager.close();
        return query.getResultList();
    }

    @Override
    public void deleteTagsFromAllExpert(Long idTag) {
        if(idTag!=null){


            manager.createNativeQuery("delete from expert_tag where tag_id ="+idTag);

        }


    }


}

