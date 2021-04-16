package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface ExpertRepository  extends JpaRepository<Expert,Long> {

}
