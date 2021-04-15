package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ExpertRepository  extends JpaRepository<Expert,Long> {

}
