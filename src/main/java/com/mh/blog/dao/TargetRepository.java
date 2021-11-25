package com.mh.blog.dao;

import com.mh.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TargetRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

    @Query(value = "set FOREIGN_KEY_CHECKS = 0",nativeQuery=true)
    void shutDownForeignKeyCheck();

    @Query(value = "set FOREIGN_KEY_CHECKS = 1",nativeQuery=true)
    void openForeignKeyCheck();
}
