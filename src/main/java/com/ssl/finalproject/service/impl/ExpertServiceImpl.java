package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.dao.ExpertDao;
import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.dao.impl.ExpertDaoImpl;
import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
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
    private final TagDao tagDao;

    public ExpertServiceImpl(ExpertRepository repository, ExpertDao expertDao, TagDao tagDao) {
        this.repository = repository;
        this.expertDao = expertDao;
        this.tagDao = tagDao;
    }

    @Override
    public Optional<Expert> findOneExpertById(Long id) {
        log.info("findOneExpertById");
        if (repository.existsById(id)) {
            Expert expert = expertDao.findExpertByID(id);
            if (expert == null)
                return Optional.empty();

            return Optional.of(expert);
        }
        return Optional.empty();
    }
    @Override
    public Expert createExpert(Expert expert) {
        log.info("createExpert");
        if (expert!=null)
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
    public Optional<List<Expert>> findAllByNombre(String nombre, Integer pagination, Integer limite) {
        log.info("findAllByNombre");
        List<Expert>expertList=expertDao.findAllByNombre(nombre,pagination,limite);
        if(expertList.isEmpty())
            return Optional.empty();
        return Optional.of(expertList);
            }
    @Override
    public Optional<List<Expert>> findAllByModalidad(String modalidad, Integer pagination, Integer limite) {
        log.info("findAllByModalidad");
        List<Expert>expertList=expertDao.findAllByModalidad(modalidad,pagination,limite);
        if(expertList.isEmpty())
            return Optional.empty();
        return Optional.of(expertList);
    }
    @Override
    public Optional<List<Expert>> findAllByEstado(String estado, Integer pagination, Integer limite) {
        log.info("findAllByEstado");
        List<Expert>expertList=expertDao.findAllByModalidad(estado,pagination,limite);
        if(expertList.isEmpty())
            return Optional.empty();
        return Optional.of(expertList);
    }


    /////////////////////////////////////////////////////////////////////
    @Override
    public Optional<List<Expert>> findAllExpertByTag(Long id, Integer pagination, Integer limite) {
        log.info("findAllExpertByTag");

        System.out.println("------------------------------------------------------------------------------");


      return Optional.of(tagDao.findTagByID(id).getExpertList());
    }

    /////////////////////////////////////////////////////////////////////
    @Override
    public List<Expert> findAllExpert(Integer pagination, Integer limite) {
        log.info("findAllExpert");
        return expertDao.findAllExperts(pagination,limite);
    }

}
