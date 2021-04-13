package com.ssl.finalproject.dao.impl;

import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Tag> findAllTags() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> itemRoot = criteriaQuery.from(Tag.class);
        criteriaQuery.select(itemRoot);

        List<Tag> items = manager.createQuery(criteriaQuery).getResultList();
        manager.close();
        return items;
    }

    @Override
    public Optional<Tag> findTagByID(Long id) {
        if(id!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
            Root<Tag> root = criteria.from(Tag.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("id"), id));
            Tag item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return Optional.of(item);
        }
        return Optional.empty();
    }
}

