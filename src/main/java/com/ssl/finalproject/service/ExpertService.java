package com.ssl.finalproject.service;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;

import java.util.List;
import java.util.Optional;

public interface ExpertService {

    List<Expert> findAllExpert();

    Optional<Expert> findOneExpertById(Long id);

    Expert createExpert(Expert expert);

    Expert updateExpert(Expert expert);

    void deleteOneExpertById(Long id);

    void deleteAllExperts();
}
