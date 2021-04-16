package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
