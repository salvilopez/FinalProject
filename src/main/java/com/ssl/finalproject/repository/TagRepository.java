package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
