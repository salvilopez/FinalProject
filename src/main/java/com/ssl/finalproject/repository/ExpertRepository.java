package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDate;

public interface ExpertRepository  extends JpaRepository<Expert,Long> {

}
