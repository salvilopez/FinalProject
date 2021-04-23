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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        query.setMaxResults(limite);//
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
            criteria.where(builder.equal(root.get("created_at"),fechaCreacion));
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

   /* @Override
    public List<Tag> findAllExpertwithFilter(String nombre, String creador, String fechaCreacion, Long id, Integer pagina, Integer limite) {


            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> root = criteria.from(Tag.class);
            criteria.select(root);
                if(nombre==null&&creador==null&&fechaCreacion==null&&id==null){
                    criteria.select(root);
                    Query query = manager.createQuery(criteria);
                    query.setMaxResults(limite);//size
                    query.setFirstResult(pagina);//pagination
                    manager.close();
                    return query.getResultList();
                }
            if(nombre!=null){
                criteria.where(builder.like(root.get("nombre"),nombre+'%'));
            }
            if(creador!=null){
                criteria.where(builder.like(root.get("creador"),creador+'%'));
            }
            if(fechaCreacion!=null){
                LocalDate localDate1 = LocalDate.parse(fechaCreacion, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                criteria.where(builder.equal(root.get("created_at"),localDate1));
            }
            if(id!=null){
                criteria.where(builder.equal(root.get("id"),id));
                Tag item = manager.createQuery(criteria).getSingleResult();
                manager.close();
                List<Tag> tagList= new ArrayList<>();
                tagList.add(item);
                return tagList;
            }

            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(pagina);//pagination
            manager.close();
            return query.getResultList();

    }*/



    }




