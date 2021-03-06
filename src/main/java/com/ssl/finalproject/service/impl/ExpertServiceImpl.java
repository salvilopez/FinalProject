package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.dao.ExpertDao;
import com.ssl.finalproject.dao.TagDao;
import com.ssl.finalproject.dao.impl.ExpertDaoImpl;
import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.repository.ExpertRepository;
import com.ssl.finalproject.repository.TagRepository;
import com.ssl.finalproject.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpertServiceImpl implements ExpertService {


    private final Logger log = LoggerFactory.getLogger(ExpertDaoImpl.class);
    private final ExpertRepository repository;
    private final TagRepository tagRepository;
    private final ExpertDao expertDao;
    private final TagDao tagDao;

    public ExpertServiceImpl(ExpertRepository repository, TagRepository tagRepository, ExpertDao expertDao, TagDao tagDao) {
        this.repository = repository;
        this.tagRepository = tagRepository;
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
        expert.setId(null);
        return repository.save(expert);
    }
    @Override
    public Expert updateExpert(Expert expert) {

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
    public List<Expert> findAllExpertByPuntuacion(Integer puntuacion, Integer paginacion, Integer limite) {
        log.info("findAllExpertByPuntuacion");
        return expertDao.findAllExpertByPuntuacion(puntuacion,paginacion,limite);
    }

    @Override
    public List<Expert>findAllByNombre(String nombre, Integer pagination, Integer limite) {
        log.info("findAllByNombre");
      return expertDao.findAllByNombre(nombre,pagination,limite);

            }
    @Override
    public List<Expert> findAllByModalidad(String modalidad, Integer pagination, Integer limite) {
        log.info("findAllByModalidad");
       return expertDao.findAllByModalidad(modalidad,pagination,limite);

    }
    @Override
    public List<Expert> findAllByEstado(String estado, Integer pagination, Integer limite) {
        log.info("findAllByEstado");
        return expertDao.findAllByEstado(estado,pagination,limite);

    }


    /////////////////////////////////////////////////////////////////////
    @Override
    public List<Expert> findAllExpertByTag(String nombreetiqueta, Integer pagination, Integer limite) {
        log.info("findAllExpertByTag");
        List<Tag>  tagList =tagDao.findTagByNombre(nombreetiqueta);
    List<Expert>expertList= new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
           expertList.addAll(expertDao.findAllExpertByTag(tagList.get(i).getId(),pagination,limite));
        }
        //Creamos un objeto HashSet
        HashSet hs = new HashSet();

        //Lo cargamos con los valores del array, esto hace quite los repetidos
        hs.addAll(expertList);

        //Limpiamos el array
        expertList.clear();

        //Agregamos los valores sin repetir
        expertList.addAll(hs);

        return expertList;

    }

    @Override
    public List<Expert> findAllExpert(Integer pagination, Integer limite) {
        log.info("findAllExpert");
        return expertDao.findAllExperts(pagination,limite);
    }

}
