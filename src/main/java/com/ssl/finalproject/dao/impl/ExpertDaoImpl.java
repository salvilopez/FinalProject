package com.ssl.finalproject.dao.impl;

import com.ssl.finalproject.dao.ExpertDao;
import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class ExpertDaoImpl implements ExpertDao {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Expert> findAllExperts() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Expert> criteriaQuery = criteriaBuilder.createQuery(Expert.class);
        Root<Expert> itemRoot = criteriaQuery.from(Expert.class);
        criteriaQuery.select(itemRoot);

        List<Expert> items = manager.createQuery(criteriaQuery).getResultList();
        manager.close();
        return items;
    }

    @Override
    public Optional<Expert> findExpertByID(Long id) {
        if(id!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("id"), id));
            Expert item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return Optional.of(item);
        }
        return Optional.empty();
    }
}
