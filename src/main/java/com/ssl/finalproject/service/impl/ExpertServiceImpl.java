package com.ssl.finalproject.service.impl;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.service.ExpertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Override
    public List<Expert> findAllExpert() {
        return null;
    }

    @Override
    public Optional<Expert> findOneExpertById(Long id) {
        return Optional.empty();
    }

    @Override
    public Expert createExpert(Expert expert) {
        return null;
    }

    @Override
    public Expert updateExpert(Expert expert) {
        return null;
    }

    @Override
    public void deleteOneExpertById(Long id) {

    }

    @Override
    public void deleteAllExperts() {

    }
}
