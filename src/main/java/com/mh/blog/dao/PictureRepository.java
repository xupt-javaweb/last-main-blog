package com.mh.blog.dao;

import com.mh.blog.entity.Picture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture,Long> {
    List<Picture> findAll(Sort Sort);
}