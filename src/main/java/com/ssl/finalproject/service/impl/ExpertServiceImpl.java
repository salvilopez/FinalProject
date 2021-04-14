package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.dao.ExpertDao;
import com.ssl.finalproject.dao.impl.ExpertDaoImpl;
import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.repository.ExpertRepository;
import com.ssl.finalproject.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {


    private final Logger log = LoggerFactory.getLogger(ExpertDaoImpl.class);
    private final ExpertRepository repository;
    private final ExpertDao expertDao;

    public ExpertServiceImpl(ExpertRepository repository, ExpertDao expertDao) {
        this.repository = repository;
        this.expertDao = expertDao;
    }


    @Override
    public List<Expert> findAllExpert() {
        log.info("findAllExpert");
        return expertDao.findAllExperts();
    }

    @Override
    public Optional<Expert> findOneExpertById(Long id) {
        log.info("findOneExpertById");
        if (id != null && repository.existsById(id))
            return expertDao.findExpertByID(id);
        return Optional.empty();
    }

    @Override
    public Expert createExpert(Expert expert) {
        log.info("createExpert");
        if (ObjectUtils.isEmpty(expert))
            return null;

        return repository.save(expert);
    }

    @Override
    public Expert updateExpert(Expert expert) {
        log.info("updateExpert");
        if ((ObjectUtils.isEmpty(expert)))
            return null;

        return repository.save(expert);
    }

    @Override
    public void deleteOneExpertById(Long id) {
        log.info("deleteOneExpertById");
        if (id != null)
            repository.deleteById(id);
    }

    @Override
    public void deleteAllExperts() {
        log.info("deleteAllExperts");
        repository.deleteAll();
    }

    @Override
    public List<Expert> findAllByNombre(String nombre) {
        log.info("findAllByNombre");
        if(nombre!=null) {
            return expertDao.findAllByNombre(nombre) ;
        }
    return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllByModalidad(String modalidad) {
        log.info("findAllByModalidad");
        if(modalidad!=null) {
            return expertDao.findAllByModalidad(modalidad);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Expert> findAllByEstado(String estado) {
        log.info("findAllByEstado");
        if(estado!=null) {
            return expertDao.findAllByEstado(estado);
        }
        return new ArrayList<>();
    }


}
