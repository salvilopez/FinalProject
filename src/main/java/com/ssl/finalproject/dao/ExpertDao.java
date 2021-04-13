package com.ssl.finalproject.dao;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ExpertDao {

    List<Expert> findAllExperts();

    Optional<Expert> findExpertByID(Long id);
}
