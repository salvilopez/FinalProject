package com.ssl.finalproject.dao.impl;

import com.ssl.finalproject.dao.ExpertDao;
import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpertDaoImpl implements ExpertDao {


    @PersistenceContext
    private EntityManager manager;



    @Override
    public Expert findExpertByID(Long id) {
        if (id != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("id"), id));
            Expert item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return item;
        }
        return null;
    }

    @Override
    public List<Expert> findAllExperts(Integer paginacion, Integer limite) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
        Root<Expert> root = criteria.from(Expert.class);
        criteria.select(root);
        Query query = manager.createQuery(criteria);
        query.setMaxResults(limite);//size
        query.setFirstResult(paginacion);//pagination
        manager.close();
        return query.getResultList();
    }

    @Override
    public List<Expert> findAllByNombre(String nombre, Integer paginacion, Integer limite) {
        if (nombre != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("nombre"), nombre+'%'));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(paginacion);//pagination
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllByModalidad(String modalidad, Integer paginacion, Integer limite) {
        if (modalidad != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("modalidad"), modalidad+'%'));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(paginacion);//pagination
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllByEstado(String estado, Integer paginacion, Integer limite) {
        if (estado != null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("estado"), estado+'%'));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(paginacion);//pagination
            manager.close();
            return query.getResultList();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllExpertByTag(Long id, Integer pagination, Integer limite) {
if(id!=null){

    Tag tag= manager.find(Tag.class,id);
    System.out.println("----------------------------");
    System.out.println(tag);
    System.out.println("----------------------------");



//    System.out.println("-- find employees with tasks --");
//    CriteriaBuilder builder = manager.getCriteriaBuilder();
//    CriteriaQuery<Expert> query = builder.createQuery(Expert.class);
//    Root<Expert> employee = query.from(Expert.class);
//    employee.join("expert_tag", JoinType.INNER);
//    query.select(employee);
//    query.where(builder.equal(employee.get("id"), id));
//    TypedQuery<Expert> typedQuery = manager.createQuery(query);
//    typedQuery.setMaxResults(limite);
//    typedQuery.setFirstResult(pagination);
//    List<Expert> resultList = typedQuery.getResultList();
//    return resultList;
}
        return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllExpertByPuntuacion(Integer puntuacion, Integer paginacion, Integer limite) {



        if(puntuacion!=null){
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
            Root<Expert> root = criteria.from(Expert.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("puntuacion"), puntuacion));
            Query query = manager.createQuery(criteria);
            query.setMaxResults(limite);//size
            query.setFirstResult(paginacion);//pagination
            manager.close();
            return query.getResultList();
        }


         return new ArrayList<>();
    }
}



